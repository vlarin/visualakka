/* 
 */
package org.vap.filetypesupport.nodesupport;

import java.util.List;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import org.vap.core.model.macro.VFLayer;
import org.vap.core.model.macro.Workspace;

/**
 *
 * @author Oleg Bantysh
 */
public class layerNodeFactory extends ChildFactory<String>{
    
    private Workspace ws;
    
    /**
     *
     * @param ws
     */
    public layerNodeFactory(Workspace ws){
        this.ws = ws;
    }

    /**
     *
     * @param list
     * @return
     */
    @Override
    protected boolean createKeys(List<String> list) {
        if(ws != null && ws.getLayers() != null && ws.getLayers().size()>0){
            for(VFLayer l : ws.getLayers()){
                list.add(l.methodName);
            }
        }else{
            list.add("");
        }
        return true;
    }

    /**
     *
     * @param key
     * @return
     */
    @Override
    protected Node createNodeForKey(String key) {
        return new layerNode(ws.findMethod(key));
    }
    
    
    
}
