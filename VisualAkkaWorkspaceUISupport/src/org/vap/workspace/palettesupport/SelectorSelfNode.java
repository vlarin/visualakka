/*
 */

package org.vap.workspace.palettesupport;

import java.awt.datatransfer.Transferable;
import java.io.IOException;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.vap.workspace.transferhandlers.SelfButton;

/**
 *
 * @author Oleg Bantysh
 */
public class SelectorSelfNode extends AbstractNode{

    public SelectorSelfNode() {
        super(Children.LEAF);
        setDisplayName("Self");
    }
    
    /**
     *
     * @return
     * @throws IOException
     */
    @Override
    public Transferable drag() throws IOException {
        return new SelfButton();
    }

}
