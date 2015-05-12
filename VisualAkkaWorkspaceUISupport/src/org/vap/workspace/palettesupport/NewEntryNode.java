/* 
 */
package org.vap.workspace.palettesupport;

import java.awt.datatransfer.Transferable;
import java.io.IOException;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.vap.workspace.transferhandlers.EntryButton;

/**
 *
 * @author Oleg Bantysh
 */
public class NewEntryNode extends AbstractNode{

    /**
     *
     */
    public NewEntryNode() {
        super(Children.LEAF);
        setDisplayName("Entry");
    }
    
    /**
     *
     * @return
     * @throws IOException
     */
    @Override
    public Transferable drag() throws IOException {
        return new EntryButton();
    }
    
}
