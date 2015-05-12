/* 
 */
package org.vap.workspace.unitsnodes;

import java.awt.Image;
import org.netbeans.api.project.Project;
import org.openide.filesystems.FileObject;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.ImageUtilities;

/**
 *
 * @author Oleg Bantysh
 */
public class PackageNode extends AbstractNode {

    FileObject packFolder;

    /**
     *
     * @param packFolder
     * @param proj
     */
    public PackageNode(FileObject packFolder, Project proj) {
        super(Children.create(new ModuleNodeFactory(packFolder, proj), true));
        this.packFolder = packFolder;
    }

    /**
     *
     * @param type
     * @return
     */
    @Override
    public Image getIcon(int type) {
        return ImageUtilities.loadImage("org/vap/workspace/resources/PackageIcon.png");
    }

    /**
     *
     * @param i
     * @return
     */
    @Override
    public Image getOpenedIcon(int i) {
        return getIcon(i);
    }

}
