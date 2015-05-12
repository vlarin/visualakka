/* 
 */
package org.vap.workspace.propertynodes;

import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.vap.core.model.macro.StateSetter;
import org.vap.workspace.WorkspaceScene;

/**
 *
 * @author Oleg Bantysh
 */
public class stsetterNode extends AbstractNode{

    public StateSetter sts;
    private WorkspaceScene ws;

    public stsetterNode(StateSetter sts, WorkspaceScene ws) {
        super(Children.LEAF);
        this.sts = sts;
        this.setDisplayName("State setter");
        this.ws = ws;
    }
    
}
