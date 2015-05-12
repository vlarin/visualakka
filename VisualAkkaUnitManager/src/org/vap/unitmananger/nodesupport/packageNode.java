/* 
 */
package org.vap.unitmananger.nodesupport;

import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;

/**
 *
 * @author Oleg Bantysh
 */
public class packageNode extends AbstractNode{

    /**
     *
     * @param packName
     */
    public packageNode(String packName){
        super(Children.create(new moduleNodeFactory(packName), false));
        this.setDisplayName(packName);
    }
    
}
