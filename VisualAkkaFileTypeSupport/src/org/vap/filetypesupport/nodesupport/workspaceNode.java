/* 
 */
package org.vap.filetypesupport.nodesupport;

import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.vap.core.model.macro.Workspace;
import org.vap.filetypesupport.VisualAkkaUnitDataObject;

/**
 *
 * @author Oleg Bantysh
 */
public class workspaceNode extends AbstractNode{

    /**
     *
     * @param obj
     */
    public workspaceNode(VisualAkkaUnitDataObject obj){
        super(Children.create(new layerNodeFactory(obj.ws.ws), true));
        this.setDisplayName(obj.getName());
    }
    
}
