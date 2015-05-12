package org.vap.core;

import akka.actor.ActorRef;
import akka.actor.OneForOneStrategy;
import akka.actor.SupervisorStrategy;
import akka.actor.SupervisorStrategy.Directive;
import akka.actor.UntypedActor;
import akka.japi.Function;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static akka.actor.SupervisorStrategy.resume;
import static akka.actor.SupervisorStrategy.restart;
import static akka.actor.SupervisorStrategy.stop;
import static akka.actor.SupervisorStrategy.escalate;
import akka.actor.Terminated;
import akka.util.Timeout;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.vap.core.exceptions.AVEInternaException;
import scala.concurrent.CanAwait;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

/**
 *
 * @author Vladislav Larin
 */
public abstract class VisualActor extends UntypedActor {

    public static final String ESCALATE = "Escalate";
    public static final String STOP = "Stop";
    public static final String RESTART = "Restart";
    public static final String RESUME = "Resume";

    private List<MethodGroup> methods;
    private Map<String, Node> nodes;
    private Map<String, Method> dynamicHandlers;

    private String state;
    private boolean initialized;
    private boolean postInitialized;

    protected int instanceId;
    protected Future<ActorRef> parentFuture;
    protected ActorRef parent;

    private SupervisorStrategy strategy;

    @Override
    public SupervisorStrategy supervisorStrategy() {
        return strategy;
    }

    public VisualActor() {
        initialized = false;
    }

    public ActorRef getSelfRouted(Message sender) throws AVEInternaException {
        return (sender.isTargetRouted() == true) ? getContext().parent() : getSelf();
    }

    public ActorRef getParent() {
        if (parent == null) {
            try {
                parent = parentFuture.result(Duration.Inf(), new CanAwait() {
                });
                getContext().watch(parent);
            } catch (Exception ex) {
                Logger.getLogger(VisualActor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return parent;
    }
    
    public String getPath() {
        return getSelf().path().toSerializationFormat();
    }

    protected void initSupervisor(final String supervising,
            int nRetries, String maxDuration) {

        strategy = new OneForOneStrategy(nRetries == -1 ? Integer.MAX_VALUE : nRetries,
                ("-1".equals(maxDuration) ? Duration.Inf() : Duration.create(maxDuration)), new Function<Throwable, Directive>() {
                    @Override
                    public Directive apply(Throwable t) {
                        switch (supervising) {
                            case ESCALATE:
                                return escalate();
                            case RESTART:
                                return restart();
                            case RESUME:
                                return resume();
                            case STOP:
                                return stop();
                            default:
                                return escalate();
                        }
                    }
                });
    }

    protected void init(String parentPath, List<List<String>> methods,
            List<Node> nodes, String defaultState) {
        this.methods = new LinkedList<>();
        this.nodes = new HashMap<>();
        this.dynamicHandlers = new HashMap<>();

        this.parentFuture = getContext().actorSelection(parentPath).resolveOne(Timeout.apply(Integer.MAX_VALUE));

        this.state = defaultState;

        Class d = this.getClass();
        for (List<String> method : methods) {
            this.methods.add(new MethodGroup(this, method));

            try {
                dynamicHandlers.put(method.get(0), d.getMethod(method.get(0).replace(' ', '_'),
                        Collections.nCopies(method.size(),
                                Message.class).toArray(new Class[]{})));
            } catch (NoSuchMethodException e) {
                //Log internal code gen error
                System.err.println("Code generator malfunction. Invalid method!");
            }
        }

        for (Node node : nodes) {
            this.nodes.put(node.getName(), node);
        }

        initialized = true;
    }

    @Override
    public final void onReceive(Object o) throws Exception {

        if (!postInitialized) {
            postInitialize();
            postInitialized = true;
        }

        if (o instanceof Terminated) {
            Terminated t = (Terminated) o;
            handleTerminated(t);
            return;
        }

        if (!(o instanceof Message)) {
            if (!tryHandleUnrecognized(o)) {
                unhandled(o);
            }
            return;
        }

        if (!initialized) {
            throw new AVEInternaException("Visual actor isn't initialized!");
        }

        Message msg = (Message) o;

        if (msg.isCallback()) {
            handleCallback(msg);
        } else {

            boolean isGroup = false;
            for (MethodGroup meth : methods) {
                if (meth.getArgs().contains(msg.getTargetNode())) {
                    isGroup = true;
                    meth.pushMessageInGroup(msg);

                    if (meth.isGroupReady()) {
                        List<Message> args = Arrays.asList(meth.extractAvaitingMessages());

                        preMethodStart(args);
                        startMethod(args);
                        postMethodStart(args);
                    }
                }
            }

            if (!isGroup) {
                List<Message> args = new LinkedList<>();
                args.add(msg);
                preMethodStart(args);
                startMethod(args);
                postMethodStart(args);
            }
        }
    }

    private void startMethod(List<Message> args) throws AVEInternaException {
        if (args == null || args.size() < 1) {
            throw new AVEInternaException("Args collection is invalid!");
        }
        if (!dynamicHandlers.containsKey(args.get(0).getTargetNode())) {
            Logger.getLogger(VisualActor.class.getName()).log(Level.SEVERE, null, args.get(0));
            throw new AVEInternaException("Derived class doesn't contain method definition for " + args.get(0).getTargetNode() + " message!");
        }
        try {
            dynamicHandlers.get(args.get(0).getTargetNode().replace(' ', '_')).invoke(this, args.toArray());
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(VisualActor.class.getName()).log(Level.SEVERE, null, ex);
            throw new AVEInternaException("Method handler has invalid format! CAUSED BY:" + ex.getMessage());
        }
    }

    /**
     * @return the nodes collection
     */
    public Map<String, ?> getNodes() {
        return nodes;
    }

    public Node getNode(String node) throws AVEInternaException {
        if (nodes.containsKey(node)) {
            return nodes.get(node);
        } else {
            throw new AVEInternaException("Requested node doesn't exists!");
        }
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Handles callbacks behaviour. DO NOT override by yourself, generated
     * automatically by the editor.
     *
     * @param callback Callback message with the results of child execution
     * @throws org.vap.core.exceptions.AVEInternaException Throws when message
     * has invalid format
     */
    protected abstract void handleCallback(Message callback) throws AVEInternaException;

    protected void handleTerminated(Terminated msg) {
        if (msg.actor().equals(parent)) {
            getContext().unwatch(parent);
            parent = null;
            parentFuture = getContext().actorSelection(parent.path()).resolveOne(Timeout.apply(Integer.MAX_VALUE));
        }
    }

    /**
     * Creates actor instances on demand
     */
    protected abstract void postInitialize();

    /**
     * Handles unrecognized message.
     *
     * @param msg Some message that doesn't instance of Message class
     * @return TRUE if message could be accounted as recognized, FALSE otherwise
     */
    protected boolean tryHandleUnrecognized(Object msg) {
        return false;
    }

    /**
     * User overridable hook. Can be used to maintain custom in-code logic.
     * Starts BEFORE method call!
     *
     * @param arguments list of message arguments, packed in the messages
     */
    protected void preMethodStart(List<Message> arguments) {
    }

    /**
     * User overridable hook. Can be used to maintain custom in-code logic.
     * Starts AFTER method call!
     *
     * @param arguments list of message arguments, packed in the messages
     */
    protected void postMethodStart(List<Message> arguments) {
    }
}
