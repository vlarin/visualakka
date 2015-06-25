/*
 */

package org.vap.workspace.palettesupport;

import java.awt.datatransfer.Transferable;
import java.io.IOException;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.vap.workspace.transferhandlers.AllButton;

/**
 *
 * @author Oleg Bantysh
 */
public class SelectorAllNode extends AbstractNode{
    public SelectorAllNode() {
        super(Children.LEAF);
        setDisplayName("All");
    }
    
    /**
     *
     * @return
     * @throws IOException
     */
    @Override
    public Transferable drag() throws IOException {
        return new AllButton();
    }

}
