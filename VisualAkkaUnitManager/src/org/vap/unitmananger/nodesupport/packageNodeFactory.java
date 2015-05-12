/* 
 */
package org.vap.unitmananger.nodesupport;

import java.util.List;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import org.openide.util.Lookup;
import org.vap.core.unitmanager.UnitManager;

/**
 *
 * @author Oleg Bantysh
 */
public class packageNodeFactory extends ChildFactory<String>{

    /**
     *
     * @param list
     * @return
     */
    @Override
    protected boolean createKeys(List<String> list) {
        UnitManager um = (UnitManager) Lookup.getDefault().lookup(UnitManager.class);
        for(String s: um.getModuleList()){
            list.add(s);
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
        UnitManager um = (UnitManager) Lookup.getDefault().lookup(UnitManager.class);
        return new moduleNode(um.findModule(key));
    }
    
    
    
}
