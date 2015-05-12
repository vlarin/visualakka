/* 
 */
package org.vap.workspace.palettesupport;

import java.awt.datatransfer.Transferable;
import java.io.IOException;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.vap.workspace.transferhandlers.UCBButton;

/**
 *
 * @author Oleg Bantysh
 */
public class NewUCBNode extends AbstractNode{

    /**
     *
     */
    public NewUCBNode() {
        super(Children.LEAF);
        setDisplayName("User code block");
    }
    
    /**
     *
     * @return
     * @throws IOException
     */
    @Override
    public Transferable drag() throws IOException {
        return new UCBButton();
    }
    
}
