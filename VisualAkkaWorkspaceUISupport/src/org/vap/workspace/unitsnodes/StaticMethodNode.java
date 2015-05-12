/* 
 */
package org.vap.workspace.unitsnodes;

import java.awt.Image;
import java.awt.datatransfer.Transferable;
import java.io.IOException;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.ImageUtilities;
import org.vap.ast.AstCoreModelAdapter;
import org.vap.core.codegen.ast.AstMethod;
import org.vap.core.model.macro.UserCodeBlock;
import org.vap.core.model.micro.Method;

/**
 *
 * @author Oleg Bantysh
 */
public class StaticMethodNode extends AbstractNode{
    
    private Method m = null;
    
    /**
     *
     * @param method
     */
    public StaticMethodNode(AstMethod method) {
        super(Children.LEAF);   
        this.setDisplayName(method.name);
        m = AstCoreModelAdapter.toModelMethod(method);
    }
    
    /**
     *
     * @return
     * @throws IOException
     */
    @Override
    public Transferable drag() throws IOException {
        return UserCodeBlock.formStaticMethod(m);
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
