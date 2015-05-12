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
public class Exit extends WorkspaceObject implements Cloneable{
    private Argument arg;
    private Result refRes;
    
    @Override
    public String toString(){
        return getCmID() + ";" + refRes.getName() + ";0";
    }

    /**
     * @return the arg
     */
    public Argument getArg() {
        return arg;
    }

    /**
     * @param arg the arg to set
     */
    public void setArg(Argument arg) {
        this.arg = arg;
    }

    /**
     * @return the refRes
     */
    public Result getRefRes() {
        return refRes;
    }

    /**
     * @param refRes the refRes to set
     */
    public void setRefRes(Result refRes) {
        this.refRes = refRes;
    }

    
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
