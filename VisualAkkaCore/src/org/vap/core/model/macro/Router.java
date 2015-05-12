/* 
 */
package org.vap.core.model.macro;

/**
 *
 * @author Oleg Bantysh
 */
public class Router {

    /**
     * @return the maxNumberRetries
     */
    public int getMaxMBCapacity() {
        return maxMBCapacity;
    }

    /**
     * @param maxMBCapacity the maxNumberRetries to set
     */
    public void setMaxMBCapacity(int maxMBCapacity) {
        this.maxMBCapacity = maxMBCapacity;
    }

    /**
     *
     */
    public static enum RouterLogic {

        /**
         *
         */
        RoundRobin,

        /**
         *
         */
        Random,

        /**
         *
         */
        SmallestMailbox,

        /**
         *
         */
        Broadcast,

        /**
         *
         */
        ScatterGatherFirstCompleted,

        /**
         *
         */
        ConsistentHashing
    };
    private RouterLogic logic = RouterLogic.RoundRobin;
    private boolean isStretched = false;
    private int minRoutes;
    private int maxRoutes;
    private int maxMBCapacity = 100;

    /**
     * @return the logic
     */
    public RouterLogic getLogic() {
        return logic;
    }

    /**
     *
     * @return
     */
    public String getLogicClass() {
        return "akka.routing." + logic.name() + "RoutingLogic";
    }
    
    /**
     *
     * @return
     */
    public String getLogicPool() {        
        return "akka.routing." + logic.name() + "Pool";
    }

    /**
     * @param logic the logic to set
     */
    public void setLogic(RouterLogic logic) {
        this.logic = logic;
    }

    /**
     * @return the minRoutes
     */
    public int getMinRoutes() {
        return minRoutes;
    }

    /**
     * @param minRoutes the minRoutes to set
     */
    public void setMinRoutes(int minRoutes) {
        this.minRoutes = minRoutes;
    }

    /**
     * @return the maxRoutes
     */
    public int getMaxRoutes() {
        if(!isStretched)return minRoutes;
        return maxRoutes;
    }

    /**
     * @param maxRoutes the maxRoutes to set
     */
    public void setMaxRoutes(int maxRoutes) {
        this.maxRoutes = maxRoutes;
    }

    /**
     * @return the isStretched
     */
    public boolean isIsStretched() {
        return isStretched;
    }

    /**
     * @param isStretched the isStretched to set
     */
    public void setIsStretched(boolean isStretched) {
        this.isStretched = isStretched;
    }

}
