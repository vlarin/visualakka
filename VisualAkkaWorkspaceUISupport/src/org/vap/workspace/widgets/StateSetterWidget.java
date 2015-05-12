/* 
 */
package org.vap.workspace.widgets;

import java.awt.Color;
import org.netbeans.api.visual.border.BorderFactory;
import org.netbeans.api.visual.layout.LayoutFactory;
import org.netbeans.api.visual.vmd.VMDFactory;
import org.netbeans.api.visual.widget.LabelWidget;
import org.netbeans.api.visual.widget.Scene;
import org.netbeans.api.visual.widget.Widget;
import org.vap.core.model.macro.StateSetter;

/**
 *
 * @author Oleg Bantysh
 */
public class StateSetterWidget extends Widget{
    private Widget sourceWidget;
    private Widget targetWidget;

    public StateSetterWidget(Scene scene, StateSetter ss) {
        super(scene);
        setOpaque(true);
        setLayout(LayoutFactory.createHorizontalFlowLayout(LayoutFactory.SerialAlignment.LEFT_TOP, 1));
        targetWidget = new TargetPinWidget(this.getScene(), ss.getCmID(), ss.getRefState().getName());
        this.addChild(targetWidget);
        this.addChild(new LabelWidget(this.getScene(), ss.getRefState().getName()));   
        //setBorder(BorderFactory.createRoundedBorder(0, 0, Color.yellow, Color.gray));
        setBorder(BorderFactory.createCompositeBorder(VMDFactory.createVMDNodeBorder(Color.gray, 2, Color.yellow,
                Color.yellow, Color.yellow, Color.yellow, Color.yellow), BorderFactory.createEmptyBorder(5, 5, 5, 10)));
    }
    
    public Widget getTargetWidget(){
        return targetWidget;
    }
    
}
