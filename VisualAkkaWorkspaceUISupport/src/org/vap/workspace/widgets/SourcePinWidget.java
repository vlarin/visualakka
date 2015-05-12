/* 
 */
package org.vap.workspace.widgets;

import org.netbeans.api.visual.widget.ImageWidget;
import org.netbeans.api.visual.widget.Scene;
import org.openide.util.Utilities;

/**
 *
 * @author Oleg Bantysh
 */
public class SourcePinWidget extends ImageWidget{
    public String CMID;
    public String name;
    public SourcePinWidget(Scene scene, String CMID, String name) {
        super(scene, Utilities.loadImage ("org/vap/workspace/resources/variablePublic.gif"));
        this.CMID = CMID;
        this.name = name;
    }
    
}
