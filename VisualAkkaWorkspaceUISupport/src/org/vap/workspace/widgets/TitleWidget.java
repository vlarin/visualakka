/* 
 */
package org.vap.workspace.widgets;

import org.netbeans.api.visual.widget.LabelWidget;
import org.netbeans.api.visual.widget.Scene;
import org.netbeans.api.visual.widget.Widget;

/**
 *
 * @author Oleg Bantysh
 */
public class TitleWidget extends LabelWidget{
    
    public Widget parent;

    public TitleWidget(Scene scene, Widget parent, String name) {
        super(scene, name);
        this.parent = parent;
    }
    
}
