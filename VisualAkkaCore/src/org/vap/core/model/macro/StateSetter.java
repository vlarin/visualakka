/* 
 */
package org.vap.core.model.macro;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import static org.vap.core.model.macro.ConcreticisedMethod.DATA_FLAVOR;
import org.vap.core.model.micro.Point;

/**
 *
 * @author Oleg Bantysh
 */
public class StateSetter extends WorkspaceObject implements Transferable, Cloneable {

    //public static final DataFlavor DATA_FLAVOR = new DataFlavor(StateSetter.class, "STS");

    private State refState;
    
    /**
     *
     */
    public StateSetter(){}
    
    /**
     *
     * @param st
     * @param CMID
     * @param loc
     */
    public StateSetter(State st, String CMID, Point loc){
        this.refState = st;
        this.cmID = CMID;
        this.loc = loc;
        checkID(cmID);
    }

    /**
     * @return the refState
     */
    public State getRefState() {
        return refState;
    }

    /**
     * @param refState the refState to set
     */
    public void setRefState(State refState) {
        this.refState = refState;
    }
    
    /**
     *
     * @param sts
     * @return
     */
    public static StateSetter formSTS(State sts){
        return new StateSetter(sts, ""+getCurrIDInc(), null);
    }

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[]{DATA_FLAVOR};
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return flavor == DATA_FLAVOR;
    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        if (flavor == DATA_FLAVOR) {
            return this;
        } else {
            throw new UnsupportedFlavorException(flavor);
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }

}
