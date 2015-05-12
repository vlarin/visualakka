/* 
 */
package org.vap.core.model.macro;

import org.vap.core.model.micro.Point;

/**
 *
 * @author Oleg Bantysh
 */
public class WorkspaceObject {
    private static int IDCOUNT = 0;

    /**
     *
     */
    protected String cmID;

    /**
     *
     */
    protected Point loc;
    /**
     * @return the cmID
     */
    public String getCmID() {
        return cmID;
    }

    /**
     * @param cmID the cmID to set
     */
    public void setCmID(String cmID) {
        this.cmID = cmID;
        checkID(cmID);
    }
    
    /**
     *
     * @param cmID
     */
    public static void checkID(String cmID){
        if (Integer.parseInt(cmID) > IDCOUNT){
            IDCOUNT = Integer.parseInt(cmID);
        }
    }
    
    /**
     *
     * @return
     */
    public static int getCurrIDInc(){
        IDCOUNT++;
        return IDCOUNT;
    }
    
        /**
     * @return the loc
     */
    public Point getLoc() {
        return loc;
    }

    /**
     * @param loc the loc to set
     */
    public void setLoc(Point loc) {
        this.loc = loc;
    }
    
}
