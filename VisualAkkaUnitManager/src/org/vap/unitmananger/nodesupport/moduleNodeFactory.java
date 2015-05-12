/* 
 */
package org.vap.unitmananger.nodesupport;

import java.util.List;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import org.openide.util.Lookup;
import org.vap.core.model.micro.Method;
import org.vap.core.model.micro.Module;
import org.vap.core.unitmanager.UnitManager;

/**
 *
 * @author Oleg Bantysh
 */
public class moduleNodeFactory extends ChildFactory<String>{
    private String pack = "";

    /**
     *
     * @param pack
     */
    public moduleNodeFactory(String pack){
        this.pack = pack;
    }

    /**
     *
     * @param list
     * @return
     */
    @Override
    protected boolean createKeys(List<String> list) {
        UnitManager um = (UnitManager) Lookup.getDefault().lookup(UnitManager.class);
        for(Module m: um.getPackageModules(pack)){
            list.add(m.toString());
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
