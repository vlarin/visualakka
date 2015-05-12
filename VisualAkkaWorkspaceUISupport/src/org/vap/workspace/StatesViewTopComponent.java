/* 
 */
package org.vap.workspace;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ProjectUtils;
import org.netbeans.api.settings.ConvertAsProperties;
import org.netbeans.api.visual.graph.GraphScene;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.explorer.ExplorerManager;
import org.openide.explorer.ExplorerUtils;
import org.openide.explorer.view.BeanTreeView;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.util.NbBundle;
import org.openide.util.Utilities;
import org.openide.windows.TopComponent;
import org.vap.core.model.macro.State;
import org.vap.workspace.palettesupport.StatesNodeFactory;

/**
 *
 * @author Oleg Bantysh
 */
@ConvertAsProperties(
        dtd = "-//org.vap.workspace//StatesView//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "StatesViewTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE", 
        persistenceType = TopComponent.PERSISTENCE_ALWAYS
)
@TopComponent.Registration(mode = "commonpalette", openAtStartup = false)
@ActionID(category = "Window", id = "org.vap.workspace.StatesViewTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_StatesViewAction",
        preferredID = "StatesViewTopComponent"
)
@NbBundle.Messages({
    "CTL_StatesViewAction=StatesView",
    "CTL_StatesViewTopComponent=States",
    "HINT_StatesViewTopComponent=This is a States window"
})
public class StatesViewTopComponent extends TopComponent implements ExplorerManager.Provider, LookupListener {

    private static Lookup.Result<WorkspaceScene> lookupResults;
    private static LookupListener lookupListener;
    private transient ExplorerManager explorerManager = new ExplorerManager();
    private BeanTreeView hierarchyView;
    private javax.swing.JScrollPane viewPane;
    private JToolBar tb;
    private JPanel pane;
    private JButton rembtn;
    private WorkspaceScene currWorkspace;
    Lookup.Result<State> stateResult;
    Lookup lkp;

//    public StatesViewTopComponent(Lookup lookup) {
//        lkp = lookup;
//        viewPane = new BeanTreeView();
//
//        //javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
////        this.setLayout(layout);
////        layout.setHorizontalGroup(
////                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
////                .addComponent(viewPane, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
////        );
////        layout.setVerticalGroup(
////                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
////                .addComponent(viewPane, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
////        );
//        this.setLayout(new BorderLayout());
//        this.add(viewPane, BorderLayout.CENTER);
//        hierarchyView = new BeanTreeView();
//
//        setName(Bundle.CTL_StatesViewTopComponent());
//        activate();
//        associateLookup(ExplorerUtils.createLookup(explorerManager, getActionMap()));
//        explorerManager.setRootContext(new AbstractNode(Children.LEAF));
//        explorerManager.getRootContext().setDisplayName("<No workspace active>");
////        buildToolbar();
////        this.add(tb, BorderLayout.SOUTH);
//        buildPanelbar();
//        this.add(pane, BorderLayout.SOUTH);
//    }

    /**
     *
     */
    
    public StatesViewTopComponent() {
        viewPane = new BeanTreeView();

        //javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
//        this.setLayout(layout);
//        layout.setHorizontalGroup(
//                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                .addComponent(viewPane, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
//        );
//        layout.setVerticalGroup(
//                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                .addComponent(viewPane, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
//        );
        this.setLayout(new BorderLayout());
        this.add(viewPane, BorderLayout.CENTER);
        hierarchyView = new BeanTreeView();

        setName(Bundle.CTL_StatesViewTopComponent());
        activate();
        associateLookup(ExplorerUtils.createLookup(explorerManager, getActionMap()));
        explorerManager.setRootContext(new AbstractNode(Children.LEAF));
        explorerManager.getRootContext().setDisplayName("<No workspace active>");
//        buildToolbar();
//        this.add(tb, BorderLayout.SOUTH);
        buildPanelbar();
        this.add(pane, BorderLayout.SOUTH);
    }

    /**
     *
     * @return
     */
    @Override
    public ExplorerManager getExplorerManager() {
        return explorerManager;
    }

    /**
     *
     */
    @Override
    public void componentOpened() {
        stateResult = Utilities.actionsGlobalContext().lookupResult(State.class);
        stateResult.addLookupListener(this);
    }

    /**
     *
     */
    @Override
    public void componentClosed() {
        stateResult.removeLookupListener(this);
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }

    /**
     *
     */
    public void activate() {
        if (lookupResults == null) {
            // Monitor the existance of Projects in the global context lookup
            lookupResults = Utilities.actionsGlobalContext().lookupResult(WorkspaceScene.class);
            // Create the listener on the lookupResults
            lookupListener = new LookupListener() {
                // Update window title when the Project changes
                @Override
                public void resultChanged(LookupEvent ignored) {
                    String projectName;
                    Collection<? extends WorkspaceScene> projects = lookupResults.allInstances();
                    if (projects.isEmpty()) {
//                        projectName = "<No active workspace>";
//                        UpdateWindow(projectName);
                    } else if (projects.size() >= 1) {
                        currWorkspace = projects.iterator().next();
                        UpdateWindow(currWorkspace);
                    }

                }
            };
            // Activate the listener
            lookupResults.addLookupListener(lookupListener);
            lookupListener.resultChanged(null);
        }
    }
    
//    public void activateLocal() {
//        if (lookupResults == null) {
//            // Monitor the existance of Projects in the global context lookup
//            lookupResults = lkp.lookupResult(WorkspaceScene.class);
//            // Create the listener on the lookupResults
//            lookupListener = new LookupListener() {
//                // Update window title when the Project changes
//                @Override
//                public void resultChanged(LookupEvent ignored) {
//                    String projectName;
//                    Collection<? extends WorkspaceScene> projects = lookupResults.allInstances();
//                    if (projects.isEmpty()) {
////                        projectName = "<No active workspace>";
////                        UpdateWindow(projectName);
//                    } else if (projects.size() == 1) {
//                        currWorkspace = projects.iterator().next();
//                        UpdateWindow(currWorkspace);
//                    }
//
//                }
//            };
//            // Activate the listener
//            lookupResults.addLookupListener(lookupListener);
//            lookupListener.resultChanged(null);
//        }
//    }

    void UpdateWindow(final String projectName) {
        explorerManager.getRootContext().setDisplayName(projectName);
    }

    void UpdateWindow(final WorkspaceScene ws) {
        String projectName = ws.ws.getRefModuleName();
        explorerManager.setRootContext(new AbstractNode(Children.create(new StatesNodeFactory(ws.ws), true)));
        explorerManager.getRootContext().setDisplayName(projectName);

    }

    void buildToolbar() {
        tb = new JToolBar();
        JButton addbtn = new JButton("Add");
        rembtn = new JButton("Delete");
        tb.add(addbtn);
        tb.add(rembtn);
    }

    void buildPanelbar() {
        pane = new JPanel();
        JButton addbtn = new JButton("Add");
        addbtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String s = (String) JOptionPane.showInputDialog(
                        null,
                        "Input state name",
                        "New state",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        null,
                        "New state");
                currWorkspace.ws.addState(new State(s));
                StatesViewTopComponent.this.UpdateWindow(currWorkspace);
            }
        });
        rembtn = new JButton("Delete");
        pane.add(addbtn);
        pane.add(rembtn);
        rembtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (State state : stateResult.allInstances()) {
                    currWorkspace.ws.removeState(state);
                }
                StatesViewTopComponent.this.UpdateWindow(currWorkspace);
            }
        });
    }

    /**
     *
     * @param le
     */
    @Override
    public void resultChanged(LookupEvent le) {
        if (stateResult.allInstances().isEmpty()) {
            rembtn.setEnabled(false);
        } else {
            rembtn.setEnabled(true);
            for (State state : stateResult.allInstances()) {
                if (state.getName().equals("Default")) {
                    rembtn.setEnabled(false);
                }
            }
        }
    }

}
