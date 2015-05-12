/* 
 */
package org.vap.workspace.palettesupport;

import java.util.List;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.lookup.Lookups;
import org.vap.core.model.macro.State;
import org.vap.core.model.macro.Workspace;

/**
 *
 * @author Oleg Bantysh
 */
public class StatesNodeFactory extends ChildFactory<State>{
    
    private Workspace ws;

    /**
     *
     * @param ws
     */
    public StatesNodeFactory(Workspace ws){
        this.ws = ws;
    }

    /**
     *
     * @param list
     * @return
     */
    @Override
    protected boolean createKeys(List<State> list) {
        list.addAll(ws.getStates());
        return true;
    }

    /**
     *
     * @param key
     * @return
     */
    @Override
    protected Node createNodeForKey(State key) {
        AbstractNode node = new StateNode(key);
        return node;
    }
    
    
    
}
