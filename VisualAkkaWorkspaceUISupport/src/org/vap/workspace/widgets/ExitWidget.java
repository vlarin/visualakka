/* 
 */
package org.vap.workspace.widgets;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import org.netbeans.api.visual.action.ActionFactory;
import org.netbeans.api.visual.action.MoveProvider;
import org.netbeans.api.visual.action.MoveStrategy;
import org.netbeans.api.visual.border.BorderFactory;
import org.netbeans.api.visual.layout.LayoutFactory;
import org.netbeans.api.visual.vmd.VMDFactory;
import org.netbeans.api.visual.widget.LabelWidget;
import org.netbeans.api.visual.widget.Scene;
import org.netbeans.api.visual.widget.SeparatorWidget;
import org.netbeans.api.visual.widget.Widget;
import org.vap.core.model.macro.Exit;
import org.vap.core.model.micro.Argument;

/**
 *
 * @author Oleg Bantysh
 */
public class ExitWidget extends Widget {

    private Widget parameterField;
    private LabelWidget name;
    private LabelWidget type;
    
    private Point currentLoc;

    public ExitWidget(Scene scene, Exit e, MoveProvider provider) {
        super(scene);
        setOpaque(true);
        setLayout(LayoutFactory.createVerticalFlowLayout(LayoutFactory.SerialAlignment.JUSTIFY, 4));
        TitleWidget nameWidget = new TitleWidget(this.getScene(), this, e.getRefRes().getName());
        nameWidget.setBorder(BorderFactory.createEmptyBorder(5, 2));
        nameWidget.setAlignment(LabelWidget.Alignment.CENTER);
        addChild(nameWidget);
        addChild(new SeparatorWidget (scene, SeparatorWidget.Orientation.HORIZONTAL));
        parameterField = new Widget(this.getScene());
        parameterField.setLayout(LayoutFactory.createVerticalFlowLayout(LayoutFactory.SerialAlignment.LEFT_TOP, 4));
        parameterField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 10));
        addChild(parameterField);
        setBorder(VMDFactory.createVMDNodeBorder(new Color(122, 9, 9), 2, new Color(250, 63, 63),
                new Color(250, 63, 63), new Color(250, 63, 63), new Color(250, 63, 63),
                new Color(250, 63, 63)));
        nameWidget.getActions().addAction(ActionFactory.createMoveAction(new MoveStrategy() {

            @Override
            public Point locationSuggested(Widget widget, Point point, Point point1) {

                Point pdif;
                if (currentLoc == null) {
                    pdif = new Point(point1.x - point.x, point1.y - point.y);
                } else {
                    pdif = new Point(point1.x - currentLoc.x, point1.y - currentLoc.y);
                }

                Point prevLoc = ExitWidget.this.getPreferredLocation();
                Point suggLoc = new Point(prevLoc.x + pdif.x,
                        prevLoc.y + pdif.y);
                ExitWidget.this.setPreferredLocation(suggLoc);
                currentLoc = point1;
                return point1;
            }
//        }, ActionFactory.createDefaultMoveProvider()));
        }, provider));
    }

    public Widget InitPin(Exit e) {
        Argument a = e.getArg();
        Widget w = new Widget(this.getScene());
        w.setLayout(LayoutFactory.createHorizontalFlowLayout(LayoutFactory.SerialAlignment.LEFT_TOP, 1));
        Widget im = new TargetPinWidget(this.getScene(), e.getCmID(), e.getRefRes().getName());
        w.addChild(im); // add image sub-widget
        name = new LabelWidget(this.getScene(), "Exit:");
        name.setFont(new Font("Arial", Font.BOLD, 13));
        type = new LabelWidget(this.getScene(), ": (" +e.getRefRes().getType()+")");
        type.setForeground(Color.GRAY);
        w.addChild(name); // add label sub-widget
        w.addChild(type);
        //w.setBorder(BorderFactory.createLineBorder (2));
        parameterField.addChild(w);
        return im;
    }
    
        public void ChangeName(String newName){
        name.setLabel(newName);
    }
}
