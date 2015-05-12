/* 
 */
package org.vap.filetypesupport.nodesupport;

import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.vap.core.model.macro.VFLayer;

/**
 *
 * @author Oleg Bantysh
 */
public class layerNode extends AbstractNode{
    
    /**
     *
     * @param layer
     */
    public layerNode(VFLayer layer){
        super(Children.LEAF);
        this.setDisplayName(layer.methodName);
    }
    
}
