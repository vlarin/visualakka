/* 
 */
package org.vap.workspace.widgets;

import org.netbeans.api.visual.border.BorderFactory;
import org.netbeans.api.visual.layout.LayoutFactory;
import org.netbeans.api.visual.widget.LabelWidget;
import org.netbeans.api.visual.widget.Widget;
import org.vap.core.model.micro.Module;

/**
 *
 * @author Oleg Bantysh
 */
public class VFlowWigetFactory {
    public static void buildWidget(Widget target, Module m, String concreticisedMethod){
        if(m.hasRepresentation()){
            
        }else{
            buildDefaults(target, m, concreticisedMethod);
        }
        
    }
    private static void buildDefaults(Widget target, Module m, String concreticisedMethod){
        target.setLayout(LayoutFactory.createVerticalFlowLayout());
        LabelWidget name = new LabelWidget(target.getScene(), m.getName());
        name.setBorder(BorderFactory.createLineBorder (5));
        target.addChild(name);
    }
    
}
