/* 
 */
package org.vap.workspace.unitsnodes;

import java.awt.Image;
import java.awt.datatransfer.Transferable;
import java.io.IOException;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.ImageUtilities;
import org.vap.core.model.macro.ConcreticisedMethod;
import org.vap.core.model.micro.Method;

/**
 *
 * @author Oleg Bantysh
 */
public class MethodNode extends AbstractNode {

    private Method method = null;
    private String moduleID = "";

    /**
     *
     * @param method
     * @param moduleID
     */
    public MethodNode(Method method, String moduleID) {
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

    /**
     *
     * @param type
     * @return
     */
    @Override
    public Image getIcon(int type) {
        return ImageUtilities.loadImage("org/vap/workspace/resources/Function-16.png");
    }

    /**
     *
     * @param i
     * @return
     */
    @Override
    public Image getOpenedIcon(int i) {
        return getIcon(i);
    }

}
