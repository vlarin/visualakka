/* 
 */
package org.vap.workspace;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Oleg Bantysh
 */
import java.awt.BorderLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import org.netbeans.api.visual.widget.Scene;
import org.netbeans.spi.navigator.NavigatorPanel;
import org.openide.util.Lookup;
import org.openide.util.Lookup.Result;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.util.Utilities;

/**
 *
 * @author Олег
 */
@NavigatorPanel.Registration(mimeType = "text/x-vau", displayName = "Workspace Content")
public class WSNavigatorPanel extends JPanel implements NavigatorPanel, LookupListener {

    /**
     *
     */
    public JScrollPane pane;

    /**
     *
     */
    public WSNavigatorPanel() {
        setLayout(new BorderLayout());
        pane = new JScrollPane();
        add(pane, BorderLayout.CENTER);
        validate();
    }
    Result<WorkspaceScene> scenesInLookup;

    /**
     *
     * @param lkp
     */
    @Override
    public void panelActivated(Lookup lkp) {
        scenesInLookup = lkp.lookupResult(WorkspaceScene.class);
        scenesInLookup.addLookupListener(this);
        resultChanged(new LookupEvent(scenesInLookup));
    }

    /**
     *
     */
    @Override
    public void panelDeactivated() {
        scenesInLookup.removeLookupListener(this);
    }

    /**
     *
     * @param le
     */
    @Override
    public void resultChanged(LookupEvent le) {
        if (!scenesInLookup.allInstances().isEmpty()) {
            Scene s = scenesInLookup.allInstances().iterator().next();
            if (s != null) {
                //s.validate();
                
                WorkspaceScene ws =((WorkspaceScene)s);
                ws.satteliteView = this;
                if(ws.isInitialized){
                    pane.setViewportView(ws.createSatelliteView());
                }else{
                    pane.setViewportView(new JLabel("<no visual tab opened>"));
                }
                //pane.setViewportView(s.createSatelliteView());
                //s.validate();
                //validate();
            }
        } else {
            pane.setViewportView(new JLabel("<no file selected>"));
        }
    }

    /**
     *
     * @return
     */
    @Override
    public Lookup getLookup() {
        return Lookup.EMPTY;
    }

    /**
     *
     * @return
     */
    @Override
    public String getDisplayName() {
        return "Workspace Content";
    }

    /**
     *
     * @return
     */
    @Override
    public String getDisplayHint() {
        return "Workspace Content";
    }

    /**
     *
     * @return
     */
    @Override
    public JComponent getComponent() {
        return this;
    }

}
