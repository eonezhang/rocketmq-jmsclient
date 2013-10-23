package com.rocketmq.community.jms.jndi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.*;
import javax.naming.spi.NamingManager;
import java.io.Serializable;
import java.util.*;


@SuppressWarnings("unchecked")
public class MQInitialContext implements Context, Serializable {
    private static final Logger LOG = LoggerFactory.getLogger(MQInitialContext.class);
    public static final String SEPARATOR = "/";
    protected static final NameParser NAME_PARSER = new NameParserImpl();
    private static final long serialVersionUID = -5754338187296859149L;

    protected final Hashtable<String, Object> environment; // environment for this context
    protected final Map<String, Object> bindings; // bindings at my level
    protected final Map<String, Object> treeBindings; // all bindings under me

    private boolean frozen;
    private String nameInNamespace = "";

    public MQInitialContext() {
        environment = new Hashtable<String, Object>();
        bindings = new HashMap<String, Object>();
        treeBindings = new HashMap<String, Object>();
    }

    public MQInitialContext(Hashtable env) {
        if (env == null) {
            this.environment = new Hashtable<String, Object>();
        } else {
            this.environment = new Hashtable<String, Object>(env);
        }
        this.bindings = Collections.EMPTY_MAP;
        this.treeBindings = Collections.EMPTY_MAP;
    }

    public MQInitialContext(Hashtable environment, Map<String, Object> bindings) {
        if (environment == null) {
            this.environment = new Hashtable<String, Object>();
        } else {
            this.environment = new Hashtable<String, Object>(environment);
        }
        this.bindings = new HashMap<String, Object>();
        treeBindings = new HashMap<String, Object>();
        if (bindings != null) {
            for (Map.Entry<String, Object> binding : bindings.entrySet()) {
                try {
                    internalBind(binding.getKey(), binding.getValue());
                } catch (Throwable e) {
                    LOG.error("Failed to bind " + binding.getKey() + "=" + binding.getValue(), e);
                }
            }
        }
        frozen = true;
    }

    public MQInitialContext(Hashtable environment, Map bindings, String nameInNamespace) {
        this(environment, bindings);
        this.nameInNamespace = nameInNamespace;
    }

    protected MQInitialContext(MQInitialContext clone, Hashtable env) {
        this.bindings = clone.bindings;
        this.treeBindings = clone.treeBindings;
        this.environment = new Hashtable<String, Object>(env);
    }

    protected MQInitialContext(MQInitialContext clone, Hashtable<String, Object> env, String nameInNamespace) {
        this(clone, env);
        this.nameInNamespace = nameInNamespace;
    }

    public void freeze() {
        frozen = true;
    }

    boolean isFrozen() {
        return frozen;
    }

    protected Map<String, Object> internalBind(String name, Object value) throws NamingException {
        assert name != null && name.length() > 0;
        assert !frozen;

        Map<String, Object> newBindings = new HashMap<String, Object>();
        int pos = name.indexOf('/');
        if (pos == -1) {
            if (treeBindings.put(name, value) != null) {
                throw new NamingException("Something already bound at " + name);
            }
            bindings.put(name, value);
            newBindings.put(name, value);
        } else {
            String segment = name.substring(0, pos);
            assert segment != null;
            assert !segment.equals("");
            Object o = treeBindings.get(segment);
            if (o == null) {
                o = newContext();
                treeBindings.put(segment, o);
                bindings.put(segment, o);
                newBindings.put(segment, o);
            } else if (!(o instanceof MQInitialContext)) {
                throw new NamingException("Something already bound where a subcontext should go");
            }
            MQInitialContext MQInitialContext = (MQInitialContext)o;
            String remainder = name.substring(pos + 1);
            Map<String, Object> subBindings = MQInitialContext.internalBind(remainder, value);
            for (Iterator iterator = subBindings.entrySet().iterator(); iterator.hasNext();) {
                Map.Entry entry = (Map.Entry)iterator.next();
                String subName = segment + "/" + (String)entry.getKey();
                Object bound = entry.getValue();
                treeBindings.put(subName, bound);
                newBindings.put(subName, bound);
            }
        }
        return newBindings;
    }

    protected MQInitialContext newContext() {
        return new MQInitialContext();
    }

    public Object addToEnvironment(String propName, Object propVal) throws NamingException {
        return environment.put(propName, propVal);
    }

    public Hashtable<String, Object> getEnvironment() throws NamingException {
        return (Hashtable<String, Object>)environment.clone();
    }

    public Object removeFromEnvironment(String propName) throws NamingException {
        return environment.remove(propName);
    }

    public Object lookup(String name) throws NamingException {
        if (name.length() == 0) {
            return this;
        }
        Object result = treeBindings.get(name);
        if (result == null) {
            result = bindings.get(name);
        }
        if (result == null) {
            int pos = name.indexOf(':');
            if (pos > 0) {
                String scheme = name.substring(0, pos);
                Context ctx = NamingManager.getURLContext(scheme, environment);
                if (ctx == null) {
                    throw new NamingException("scheme " + scheme + " not recognized");
                }
                return ctx.lookup(name);
            } else {
                // Split out the first name of the path
                // and look for it in the bindings map.
                CompositeName path = new CompositeName(name);

                if (path.size() == 0) {
                    return this;
                } else {
                    String first = path.get(0);
                    Object obj = bindings.get(first);
                    if (obj == null) {
                        throw new NameNotFoundException(name);
                    } else if (obj instanceof Context && path.size() > 1) {
                        Context subContext = (Context)obj;
                        obj = subContext.lookup(path.getSuffix(1));
                    }
                    return obj;
                }
            }
        }
        if (result instanceof LinkRef) {
            LinkRef ref = (LinkRef)result;
            result = lookup(ref.getLinkName());
        }
        if (result instanceof Reference) {
            try {
                result = NamingManager.getObjectInstance(result, null, null, this.environment);
            } catch (NamingException e) {
                throw e;
            } catch (Exception e) {
                throw (NamingException)new NamingException("could not look up : " + name).initCause(e);
            }
        }
        if (result instanceof MQInitialContext) {
            String prefix = getNameInNamespace();
            if (prefix.length() > 0) {
                prefix = prefix + SEPARATOR;
            }
            result = new MQInitialContext((MQInitialContext)result, environment, prefix + name);
        }
        return result;
    }

    public Object lookup(Name name) throws NamingException {
        return lookup(name.toString());
    }

    public Object lookupLink(String name) throws NamingException {
        return lookup(name);
    }

    public Name composeName(Name name, Name prefix) throws NamingException {
        Name result = (Name)prefix.clone();
        result.addAll(name);
        return result;
    }

    public String composeName(String name, String prefix) throws NamingException {
        CompositeName result = new CompositeName(prefix);
        result.addAll(new CompositeName(name));
        return result.toString();
    }

    public NamingEnumeration list(String name) throws NamingException {
        Object o = lookup(name);
        if (o == this) {
            return new ListEnumeration();
        } else if (o instanceof Context) {
            return ((Context)o).list("");
        } else {
            throw new NotContextException();
        }
    }

    public NamingEnumeration listBindings(String name) throws NamingException {
        Object o = lookup(name);
        if (o == this) {
            return new ListBindingEnumeration();
        } else if (o instanceof Context) {
            return ((Context)o).listBindings("");
        } else {
            throw new NotContextException();
        }
    }

    public Object lookupLink(Name name) throws NamingException {
        return lookupLink(name.toString());
    }

    public NamingEnumeration list(Name name) throws NamingException {
        return list(name.toString());
    }

    public NamingEnumeration listBindings(Name name) throws NamingException {
        return listBindings(name.toString());
    }

    public void bind(Name name, Object obj) throws NamingException {
        throw new OperationNotSupportedException();
    }

    public void bind(String name, Object obj) throws NamingException {
        throw new OperationNotSupportedException();
    }

    public void close() throws NamingException {
        // ignore
    }

    public Context createSubcontext(Name name) throws NamingException {
        throw new OperationNotSupportedException();
    }

    public Context createSubcontext(String name) throws NamingException {
        throw new OperationNotSupportedException();
    }

    public void destroySubcontext(Name name) throws NamingException {
        throw new OperationNotSupportedException();
    }

    public void destroySubcontext(String name) throws NamingException {
        throw new OperationNotSupportedException();
    }

    public String getNameInNamespace() throws NamingException {
        return nameInNamespace;
    }

    public NameParser getNameParser(Name name) throws NamingException {
        return NAME_PARSER;
    }

    public NameParser getNameParser(String name) throws NamingException {
        return NAME_PARSER;
    }

    public void rebind(Name name, Object obj) throws NamingException {
        throw new OperationNotSupportedException();
    }

    public void rebind(String name, Object obj) throws NamingException {
        throw new OperationNotSupportedException();
    }

    public void rename(Name oldName, Name newName) throws NamingException {
        throw new OperationNotSupportedException();
    }

    public void rename(String oldName, String newName) throws NamingException {
        throw new OperationNotSupportedException();
    }

    public void unbind(Name name) throws NamingException {
        throw new OperationNotSupportedException();
    }

    public void unbind(String name) throws NamingException {
        throw new OperationNotSupportedException();
    }

    private abstract class LocalNamingEnumeration implements NamingEnumeration {
        private final Iterator i = bindings.entrySet().iterator();

        public boolean hasMore() throws NamingException {
            return i.hasNext();
        }

        public boolean hasMoreElements() {
            return i.hasNext();
        }

        protected Map.Entry getNext() {
            return (Map.Entry)i.next();
        }

        public void close() throws NamingException {
        }
    }

    private class ListEnumeration extends LocalNamingEnumeration {
        ListEnumeration() {
        }

        public Object next() throws NamingException {
            return nextElement();
        }

        public Object nextElement() {
            Map.Entry entry = getNext();
            return new NameClassPair((String)entry.getKey(), entry.getValue().getClass().getName());
        }
    }

    private class ListBindingEnumeration extends LocalNamingEnumeration {
        ListBindingEnumeration() {
        }

        public Object next() throws NamingException {
            return nextElement();
        }

        public Object nextElement() {
            Map.Entry entry = getNext();
            return new Binding((String)entry.getKey(), entry.getValue());
        }
    }
}
