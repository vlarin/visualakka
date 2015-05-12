/* 
 */
package org.vap.unitmananger.nodesupport;

import java.awt.datatransfer.Transferable;
import java.io.IOException;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.vap.core.model.macro.ConcreticisedMethod;
import org.vap.core.model.micro.Method;

/**
 *
 * @author Oleg Bantysh
 */
public class methodNode extends AbstractNode{
    private Method method = null;
    private String moduleID = "";

    /**
     *
     * @param method
     * @param moduleID
     */
    public methodNode(Method method, String moduleID){
        super(Children.LEAF);
        this.method = method;
        this.moduleID = moduleID;
        this.setDisplayName(method.getName());
    }

    /**
     *
     * @return
     * @throws IOException
     */
    @Override
    public Transferable drag() throws IOException {
        return ConcreticisedMethod.formCM(method, moduleID);
    }

}
