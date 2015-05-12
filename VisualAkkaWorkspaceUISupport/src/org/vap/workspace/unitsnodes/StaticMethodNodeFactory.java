/* 
 */
package org.vap.workspace.unitsnodes;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.netbeans.api.project.Project;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.vap.ast.AstSingleton;
import org.vap.core.codegen.ast.AstMethod;

/**
 *
 * @author Oleg Bantysh
 */
public class StaticMethodNodeFactory extends ChildFactory<AstMethod> {

    /**
     *
     * @param list
     * @return
     */
    @Override
    protected boolean createKeys(List<AstMethod> list) {

        ArrayList<AstMethod> methods = new ArrayList<AstMethod>();
        try{
            methods = AstSingleton.getInstance().getMethods();
        }catch(Exception e){
            Exceptions.printStackTrace(e);
        }
        if (methods != null) {
            for (AstMethod m : methods) {
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
    protected Node createNodeForKey(AstMethod key) {
        return new StaticMethodNode(key);
    }

}
