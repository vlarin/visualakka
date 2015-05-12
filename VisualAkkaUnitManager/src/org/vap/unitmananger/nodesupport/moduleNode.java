/* 
 */
package org.vap.unitmananger.nodesupport;

import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.vap.core.model.micro.Module;

/**
 *
 * @author Oleg Bantysh
 */
public class moduleNode extends AbstractNode{
    private Module m;

    /**
     *
     * @param m
     */
    public moduleNode(Module m){
        super(Children.create(new methodNodeFactory(m), true));
        //this.setDisplayName(m.getName());
        this.setDisplayName(m.toString());
    }
    
}
