/* 
 */
package org.vap.workspace.unitsnodes;

import java.util.ArrayDeque;
import java.util.Enumeration;
import java.util.List;
import org.netbeans.api.project.FileOwnerQuery;
import org.netbeans.api.project.Project;
import org.openide.filesystems.FileObject;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import org.openide.util.Lookup;
import org.vap.core.model.macro.Workspace;
import org.vap.core.model.micro.Module;
import org.vap.core.unitmanager.UnitManager;
import org.vap.fileutils.XMLToWorkspace;

/**
 *
 * @author Oleg Bantysh
 */
public class ModuleNodeFactory extends ChildFactory<FileObject> {

    private FileObject packName;
    private Project proj;

    /**
     *
     * @param packageName
     * @param proj
     */
    public ModuleNodeFactory(FileObject packageName, Project proj) {
        this.packName = packageName;
        this.proj = proj;
    }

    /**
     *
     * @param list
     * @return
     */
    @Override
    protected boolean createKeys(List<FileObject> list) {
        for (Enumeration<? extends FileObject> e = packName.getData(true); e.hasMoreElements();) {
            FileObject temp = e.nextElement();
            if (temp.getMIMEType().equals("text/x-vau")&&temp.getParent().equals(packName)) {
                list.add(temp);
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
        Workspace w = XMLToWorkspace.parse(key);
        w.setRefModuleName(key.getName());
        String base = key.getPath();
        
        if (proj != null) {
            String pd = proj.getProjectDirectory().getPath();
            base = base.replace(pd + "/src/", "");
        }
        
        String pn = key.getNameExt();
        base = base.replace("/" + pn, "");
        base = base.replace("/", ".");
        w.setRefModulePackage(base);
        
        Module m = w.formModule();
        UnitManager um = (UnitManager) Lookup.getDefault().lookup(UnitManager.class);
        um.addModule(m);
        
        ModuleNode result = new ModuleNode(m);
        result.setDisplayName(key.getName());
        return result;
    }

}
