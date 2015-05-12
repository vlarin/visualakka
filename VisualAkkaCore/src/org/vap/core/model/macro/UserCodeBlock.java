/* 
 */
package org.vap.core.model.macro;

import static org.vap.core.model.macro.WorkspaceObject.checkID;
import org.vap.core.model.micro.Method;
import org.vap.core.model.micro.Point;

/**
 *
 * @author Oleg Bantysh
 */
public class UserCodeBlock extends ConcreticisedMethod{
    
    /**
     *
     */
    public UserCodeBlock() {
        super();
        type = CMType.UCB;
    }

    /**
     *
     * @param cmID
     * @param moduleID
     * @param methodName
     * @param loc
     */
    public UserCodeBlock(String cmID, String moduleID, Method methodName, Point loc) {
        super(cmID, moduleID, methodName, loc);
        type = CMType.UCB;
    }
    
    /**
     *
     * @param m
     * @return
     */
    public static UserCodeBlock formUCB(Method m){
        return new UserCodeBlock("" + getCurrIDInc(), "System.UserCodeBlocks", m, null);
    }
    
    /**
     *
     * @param m
     * @return
     */
    public static UserCodeBlock formStaticMethod(Method m){
        UserCodeBlock usb = new UserCodeBlock("" + getCurrIDInc(), "System.StaticMethod", m, null);
        usb.type = CMType.StaticMethod;
        return usb;
    }
    
//    /**
//     *
//     * @param m
//     * @return
//     */
//    public static UserCodeBlock toUCB(ConcreticisedMethod m){
//        CMType type = m.type;
//        UserCodeBlock usb = new UserCodeBlock(m.getCmID(), m.getModuleID(), m.getRefMeth(), m.getLoc());
//        usb.type = type;
//        return usb;       
//    }
    
}
