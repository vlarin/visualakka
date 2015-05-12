/* 
 */
package org.vap.core.model.macro;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import static org.vap.core.model.macro.ConcreticisedMethod.DATA_FLAVOR;
import org.vap.core.model.micro.Argument;
import org.vap.core.model.micro.Point;
import org.vap.core.model.micro.Result;

/**
 *
 * @author Oleg Bantysh
 */
public class Entry extends WorkspaceObject implements Cloneable{
    //TODO: Argument properties
    private Result out;
    private Argument refArg;
    
    @Override
    public String toString(){
        return getCmID() + ";" + refArg.getName() + ";1";
    }


    /**
     * @return the out
     */
    public Result getOut() {
        return out;
    }

    /**
     * @param out the out to set
     */
    public void setOut(Result out) {
        this.out = out;
    }

    /**
     * @return the refArg
     */
    public Argument getRefArg() {
        return refArg;
    }

    /**
     * @param refArg the refArg to set
     */
    public void setRefArg(Argument refArg) {
        this.refArg = refArg;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
