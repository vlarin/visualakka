/* 
 */
package org.vap.workspace.propertynodes;

import java.util.List;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import org.vap.core.model.macro.ConcreticisedMethod;
import org.vap.core.model.macro.Entry;
import org.vap.core.model.macro.Exit;
import org.vap.core.model.macro.Workspace;
import org.vap.workspace.WorkspaceScene;

/**
 *
 * @author Oleg Bantysh
 */
public class PropertyNodeFactory extends ChildFactory<String>{ 
    private Node n;
    public PropertyNodeFactory(Node n){
        this.n = n;
    }

    @Override
    protected boolean createKeys(List list) {
        list.add(new String(""));
        return true;
    }

    @Override
    protected Node createNodeForKey(String key) {
        return n;
    }
    
}
