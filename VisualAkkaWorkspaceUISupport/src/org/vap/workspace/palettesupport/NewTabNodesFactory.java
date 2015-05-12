/* 
 */
package org.vap.workspace.palettesupport;

import java.util.List;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;

/**
 *
 * @author Oleg Bantysh
 */
public class NewTabNodesFactory extends ChildFactory<Integer>{
    
    /**
     *
     * @param list
     * @return
     */
    @Override
    protected boolean createKeys(List<Integer> list) {
        list.add(0);
        list.add(1);
        list.add(2);
        return true;
    }

    /**
     *
     * @param key
     * @return
     */
    @Override
    protected Node createNodeForKey(Integer key) {
        if(key==0){
            return new NewEntryNode();
        }else if(key == 1){
            return new NewExitNode();
        }else if(key == 2){
            return new NewUCBNode();
        }else{
            return null;
        }
    }
}
