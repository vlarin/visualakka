/* 
 */
package org.vap.workspace.unitsnodes;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ProjectUtils;
import org.openide.*;
import org.openide.filesystems.FileObject;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Children;
import org.openide.nodes.Node;

/**
 *
 * @author Oleg Bantysh
 */
public class PackageNodeFactory extends ChildFactory<FileObject> {

    private Project proj;

    /**
     *
     * @param proj
     */
    public PackageNodeFactory(Project proj) {
        this.proj = proj;
    }

    /**
     *
     * @param list
     * @return
     */
    @Override
    protected boolean createKeys(List<FileObject> list) {
        FileObject fo = proj.getProjectDirectory();
        
        //Awful approach
        //TODO: find a way to remove this workaround
        //list.add(new StaticMethodFileObject());   
        //------
        
        for (Enumeration<? extends FileObject> e = fo.getFolders(true); e.hasMoreElements();) {
            FileObject f = e.nextElement();
            if(f.getPath().contains("/src/")){
                list.add(f);
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
    protected Node createNodeForKey(FileObject key) {
        //Awful approach
        //TODO: find a way to remove this workaround
        if(key instanceof StaticMethodFileObject){
            AbstractNode node = new AbstractNode(Children.create(new StaticMethodNodeFactory(), true));
            node.setDisplayName("Static methods");
            node.setIconBaseWithExtension("org/vap/workspace/resources/vfl16.png");
            return node;
        }  
        //------
        Node result = new PackageNode(key, proj);
        String base = key.getPath();

        if (proj != null) {
            String pd = proj.getProjectDirectory().getPath();
            base = base.replace(pd + "/src/", "");
        }

        base = base.replace("/", ".");
        result.setDisplayName(base);
        return result;
    }

}
