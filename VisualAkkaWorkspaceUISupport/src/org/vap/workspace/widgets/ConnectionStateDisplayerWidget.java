/* 
 */
package org.vap.workspace.widgets;

import java.awt.Graphics2D;
import org.netbeans.api.visual.widget.LabelWidget;
import org.netbeans.api.visual.widget.Scene;

/**
 *
 * @author Oleg Bantysh
 */
public class ConnectionStateDisplayerWidget extends LabelWidget{
    
    private float angle = 0;

    public ConnectionStateDisplayerWidget(Scene scene, String label) {
        super(scene, label);
    }

    @Override
    protected void paintWidget() {
        super.paintWidget(); //To change body of generated methods, choose Tools | Templates
        Graphics2D g = getGraphics();
        
        
    }
    
    
    
}
