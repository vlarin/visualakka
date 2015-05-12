/* 
 */
package org.vap.workspace.unitsnodes;

import java.awt.Image;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.ImageUtilities;
import org.vap.core.model.micro.Module;

/**
 *
 * @author Oleg Bantysh
 */
public class ModuleNode extends AbstractNode {

    /**
     *
     * @param m
     */
    public ModuleNode(Module m) {
        super(Children.create(new MethodNodeFactory(m), true));
    }

    /**
     *
     * @param type
     * @return
     */
    @Override
    public Image getIcon(int type) {
        return ImageUtilities.loadImage("org/vap/workspace/resources/vfl16.png");
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
