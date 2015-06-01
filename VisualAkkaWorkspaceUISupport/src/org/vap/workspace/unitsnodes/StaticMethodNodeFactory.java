/* 
 */
package org.vap.workspace.unitsnodes;

import com.sun.source.tree.MethodTree;
import java.util.ArrayList;
import java.util.List;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.vap.core.codegen.AbstractCodeParser;
//import org.vap.ast.AstSingleton;
//import org.vap.core.codegen.ast.MethodTree;

/**
 *
 * @author Oleg Bantysh
 */
public class StaticMethodNodeFactory extends ChildFactory<MethodTree> {

    /**
     *
     * @param list
     * @return
     */
    @Override
    protected boolean createKeys(List<MethodTree> list) {

        ArrayList<MethodTree> methods = new ArrayList<MethodTree>();
        try{
            AbstractCodeParser gen = (AbstractCodeParser) Lookup.getDefault().lookup(AbstractCodeParser.class);
            methods = gen.getMethods();
        }catch(Exception e){
            Exceptions.printStackTrace(e);
        }
        if (methods != null) {
            for (MethodTree m : methods) {
                list.add(m);
            }
        }
        return true;
    }

    /**
     *
     * @param key
     * @return
     */
    @Override
    protected Node createNodeForKey(MethodTree key) {
        return new StaticMethodNode(key);
    }

}
