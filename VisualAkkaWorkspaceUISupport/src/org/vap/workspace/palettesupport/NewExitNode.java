/* 
 */
package org.vap.workspace.palettesupport;

import java.awt.datatransfer.Transferable;
import java.io.IOException;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.vap.workspace.transferhandlers.ExitButton;

/**
 *
 * @author Oleg Bantysh
 */
public class NewExitNode extends AbstractNode{

    /**
     *
     */
    public NewExitNode() {
        super(Children.LEAF);
        setDisplayName("Exit");
    }
    
    /**
     *
     * @return
     * @throws IOException
     */
    @Override
    public Transferable drag() throws IOException {
        return new ExitButton();
    }
    
}
