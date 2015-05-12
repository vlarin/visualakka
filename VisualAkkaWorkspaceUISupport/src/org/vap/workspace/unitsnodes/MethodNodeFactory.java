/* 
 */
package org.vap.workspace.unitsnodes;

import java.util.List;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import org.vap.core.model.micro.Method;
import org.vap.core.model.micro.Module;

/**
 *
 * @author Oleg Bantysh
 */
public class MethodNodeFactory extends ChildFactory<String>{
    private Module m = null;

    /**
     *
     * @param m
     */
    public MethodNodeFactory(Module m){
        this.m = m;
    }

    /**
     *
     * @param list
     * @return
     */
    @Override
    protected boolean createKeys(List<String> list) {
        for(Method meth: m.getMethods()){
            list.add(meth.getName());
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
        return new MethodNode(m.getMethodByName(key),m.toString());
    }
    
}
