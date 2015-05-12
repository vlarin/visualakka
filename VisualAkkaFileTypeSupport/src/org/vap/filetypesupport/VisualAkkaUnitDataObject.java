/* 
 */
package org.vap.filetypesupport;

import java.io.IOException;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import org.netbeans.api.project.FileOwnerQuery;
import org.netbeans.api.project.Project;
import org.netbeans.core.spi.multiview.MultiViewElement;
import org.netbeans.core.spi.multiview.text.MultiViewEditorElement;
import org.netbeans.spi.actions.AbstractSavable;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.filesystems.MIMEResolver;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectExistsException;
import org.openide.loaders.MultiDataObject;
import org.openide.loaders.MultiFileLoader;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.NbBundle.Messages;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.util.lookup.ProxyLookup;
import org.openide.windows.TopComponent;
//import org.vap.ast.AstSingleton;
import org.vap.core.codeexecuter.AbstractGenerator;
import org.vap.core.deployer.AbstractDeployer;
import org.vap.core.filetype.WorkspaceFile;
import org.vap.core.model.macro.Workspace;
import org.vap.fileutils.WorkspaceToXML;
import org.vap.fileutils.XMLToWorkspace;
import org.vap.fileutils.WorkspaceParser;
import org.vap.workspace.WorkspaceScene;
import org.xml.sax.SAXException;

/**
 *
 * @author Олег
 */
@Messages({
    "LBL_VisualAkkaUnit_LOADER=Files of VisualAkkaUnit",
    "LBL_VAUCreate_Description=Create visual actor representation for Akka"
        
})
@MIMEResolver.ExtensionRegistration(
        displayName = "#LBL_VisualAkkaUnit_LOADER",
        mimeType = "text/x-vau",
        extension = {"vau", "VAU"}
)
@DataObject.Registration(
        mimeType = "text/x-vau",
        iconBase = "org/vap/filetypesupport/vfl16.png",
        displayName = "#LBL_VisualAkkaUnit_LOADER",
        position = 300
)
@ActionReferences({
    @ActionReference(
            path = "Loaders/text/x-vau/Actions",
            id = @ActionID(category = "System", id = "org.openide.actions.OpenAction"),
            position = 100,
            separatorAfter = 200
    ),
    @ActionReference(
            path = "Loaders/text/x-vau/Actions",
            id = @ActionID(category = "Edit", id = "org.openide.actions.CutAction"),
            position = 300
    ),
    @ActionReference(
            path = "Loaders/text/x-vau/Actions",
            id = @ActionID(category = "Edit", id = "org.openide.actions.CopyAction"),
            position = 400,
            separatorAfter = 500
    ),
    @ActionReference(
            path = "Loaders/text/x-vau/Actions",
            id = @ActionID(category = "Edit", id = "org.openide.actions.DeleteAction"),
            position = 600
    ),
    @ActionReference(
            path = "Loaders/text/x-vau/Actions",
            id = @ActionID(category = "System", id = "org.openide.actions.RenameAction"),
            position = 700,
            separatorAfter = 800
    ),
    @ActionReference(
            path = "Loaders/text/x-vau/Actions",
            id = @ActionID(category = "System", id = "org.openide.actions.SaveAsTemplateAction"),
            position = 900,
            separatorAfter = 1000
    ),
    @ActionReference(
            path = "Loaders/text/x-vau/Actions",
            id = @ActionID(category = "System", id = "org.openide.actions.FileSystemAction"),
            position = 1100,
            separatorAfter = 1200
    ),
    @ActionReference(
            path = "Loaders/text/x-vau/Actions",
            id = @ActionID(category = "System", id = "org.openide.actions.ToolsAction"),
            position = 1300
    ),
    @ActionReference(
            path = "Loaders/text/x-vau/Actions",
            id = @ActionID(category = "System", id = "org.openide.actions.PropertiesAction"),
            position = 1400
    )
})
public class VisualAkkaUnitDataObject extends MultiDataObject implements WorkspaceFile {

    //public Workspace ws;

    /**
     *
     */
        public WorkspaceScene ws;
    private Lookup lookup;

    /**
     *
     */
    public InstanceContent ic = new InstanceContent();

    /**
     *
     * @param pf
     * @param loader
     * @throws DataObjectExistsException
     * @throws IOException
     */
    public VisualAkkaUnitDataObject(FileObject pf, MultiFileLoader loader) throws DataObjectExistsException, IOException {
        super(pf, loader);
        lookup = new ProxyLookup(getCookieSet().getLookup(), new AbstractLookup(ic));
        registerEditor("text/x-vau", true);
        initWorkspace();
        ic.add(ws);
        ic.add(ic);
    }

    /**
     *
     * @return
     */
    @Override
    public Lookup getLookup() {
        return lookup;
    }

    /**
     *
     * @return
     */
    @Override
    protected int associateLookup() {
        return 1;
    }

    /**
     *
     * @return
     */
    @Override
    protected Node createNodeDelegate() {
        //return new workspaceNode(this);
        return super.createNodeDelegate(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param lkp
     * @return
     */
    @MultiViewElement.Registration(
            displayName = "#LBL_VisualAkkaUnit_EDITOR",
            iconBase = "org/vap/filetypesupport/vfl16.png",
            mimeType = "text/x-vau",
            persistenceType = TopComponent.PERSISTENCE_ONLY_OPENED,
            preferredID = "VisualAkkaUnit",
            position = 1000
    )
    @Messages("LBL_VisualAkkaUnit_EDITOR=Source")
    public static MultiViewElement createEditor(Lookup lkp) {
        //return new VisualAkkaUnitSourceEditor(lkp);
        return new MultiViewEditorElement(lkp);
    }

    private void initWorkspace() {
//        String path = FileOwnerQuery.getOwner(this.getPrimaryFile()).getProjectDirectory().getPath();
//        if(path!=null&&!path.isEmpty()){
//            AstSingleton.getInstance().update(new File(path));
//        }       
        //Workspace w = XMLToWorkspace.parse(this.getPrimaryFile());
        Workspace w = new Workspace();
            try {
                w = WorkspaceParser.Parse(FileUtil.toFile(this.getPrimaryFile()));
                if(w==null){
                    w = XMLToWorkspace.parse(this.getPrimaryFile());
                }
            } catch (ParserConfigurationException ex) {
                Exceptions.printStackTrace(ex);
            } catch (SAXException ex) {
                Exceptions.printStackTrace(ex);
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            }
        w.setRefModuleName(this.getName());
//        w.fixObjectReferences();
        Project p = FileOwnerQuery.getOwner(this.getPrimaryFile());
        String base = this.getPrimaryFile().getPath();

        if (p != null) {
            String pd = p.getProjectDirectory().getPath();
//            try{
//                AstSingleton.getInstance().update(new File(pd));
//            }catch(Exception e){
//                e.printStackTrace();
//            }                    
            base = base.replace(pd + "/src/", "");
        }
        String pn = this.getPrimaryFile().getNameExt();
        base = base.replace("/" + pn, "");
        base = base.replace("/", ".");
        w.setRefModulePackage(base);
        ws = new WorkspaceScene(w, this);
        ws.validate();
    }

    /**
     *
     */
    @Override
    public void open() {
        initWorkspace();
        ic.add(ws);
    }

    @Override
    public void save() throws IOException {
        String path = FileOwnerQuery.getOwner(this.getPrimaryFile()).getProjectDirectory().getPath();
        try {
            Workspace w = lookup.lookup(WorkspaceScene.class).ws;

            //Generating source code!
            AbstractGenerator gen = (AbstractGenerator) Lookup.getDefault().lookup(AbstractGenerator.class);
            
            gen.javaGen(w, path);
            

            WorkspaceToXML.parse(this.getPrimaryFile(), w);

        } catch (JAXBException ex) {
            Exceptions.printStackTrace(ex);
        }
//        try{
//            //AstSingleton.getInstance().update(new File(path));
//        }catch(Exception e){
//            Exceptions.printStackTrace(e);
//        }
        
    }

    @Override
    public void compile() {
        String path = FileOwnerQuery.getOwner(this.getPrimaryFile()).getProjectDirectory().getPath();
        AbstractDeployer ad = (AbstractDeployer) Lookup.getDefault().lookup(AbstractDeployer.class);
        ad.compile(path);
    }

    @Override
    public void compileAndRun() {
        String path = FileOwnerQuery.getOwner(this.getPrimaryFile()).getProjectDirectory().getPath();
        AbstractDeployer ad = (AbstractDeployer) Lookup.getDefault().lookup(AbstractDeployer.class);
        ad.complileAndRun(path, this.getName());
    }

    @Override
    public void makeDirty() {
        System.out.println("Founded changes!");
        if (getLookup().lookup(VAUSavable.class) == null) {
            ic.add(new VAUSavable(this));
            System.out.println("Added savable note!");
        }
    }
}

class VAUSavable extends AbstractSavable {

    VisualAkkaUnitDataObject owner;

    VAUSavable(VisualAkkaUnitDataObject owner) {
        this.owner = owner;

        register();
    }

    @Override
    protected String findDisplayName() {
        //TODO: Check for NULLs
        return "Visual unit: " + owner.ws.ws.getRefModulePackage() + '.' + owner.ws.ws.getRefModuleName();
    }

    @Override
    protected void handleSave() throws IOException {
        owner.save();

        owner.ic.remove(this);
        unregister();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof VAUSavable) {
            VAUSavable m = (VAUSavable) obj;
            return owner == m.owner;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return owner.hashCode();
    }
}
