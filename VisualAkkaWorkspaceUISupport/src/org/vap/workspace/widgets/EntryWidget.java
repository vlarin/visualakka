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
import org.vap.core.model.macro.Entry;

/**
 *
 * @author Oleg Bantysh
 */
public class EntryWidget extends Widget {

    private Widget exitsField;
    private LabelWidget name;
    private LabelWidget type;
    
    private Point currentLoc;

    public EntryWidget(Scene scene, Entry e, MoveProvider provider) {
        super(scene);
        setOpaque(true);
        setLayout(LayoutFactory.createVerticalFlowLayout(LayoutFactory.SerialAlignment.JUSTIFY, 4));
        TitleWidget nameWidget = new TitleWidget(this.getScene(), this, e.getRefArg().getName());
        nameWidget.setBorder(BorderFactory.createEmptyBorder(5));
        nameWidget.setAlignment(LabelWidget.Alignment.CENTER);
        addChild(nameWidget);
        addChild(new SeparatorWidget (scene, SeparatorWidget.Orientation.HORIZONTAL));
        exitsField = new Widget(this.getScene());
        exitsField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 5));
        addChild(exitsField);
        setBorder(VMDFactory.createVMDNodeBorder(new Color(2,82,30), 2, new Color(65,224,121),
                new Color(105,255,158), new Color(65,224,121), new Color(65,224,121),
                new Color(65,224,121)));
        //setBorder(BorderFactory.createLineBorder (5));
        nameWidget.getActions().addAction(ActionFactory.createMoveAction(new MoveStrategy() {

            @Override
            public Point locationSuggested(Widget widget, Point point, Point point1) {

                Point pdif;
                if (currentLoc == null) {
                    pdif = new Point(point1.x - point.x, point1.y - point.y);
                } else {
                    pdif = new Point(point1.x - currentLoc.x, point1.y - currentLoc.y);
                }

                Point prevLoc = EntryWidget.this.getPreferredLocation();
                Point suggLoc = new Point(prevLoc.x + pdif.x,
                        prevLoc.y + pdif.y);
                EntryWidget.this.setPreferredLocation(suggLoc);
                currentLoc = point1;
                return point1;
            }
//        }, ActionFactory.createDefaultMoveProvider()));
        }, provider));
    }

    public Widget InitPin(Entry e) {
        Widget w = new Widget(this.getScene());
        Widget im = new SourcePinWidget(this.getScene(), e.getCmID(), e.getRefArg().getName());
        w.setLayout(LayoutFactory.createHorizontalFlowLayout(LayoutFactory.SerialAlignment.RIGHT_BOTTOM, 4));
        name = new LabelWidget(this.getScene(), "Entry");
        name.setFont(new Font("Arial", Font.BOLD , 13));
        type = new LabelWidget(this.getScene(), ": (" +e.getRefArg().getType()+")");
        type.setForeground(Color.GRAY);
        w.addChild(name); // add label sub-widget
        w.addChild(type);
        w.addChild(im); // add image sub-widget
        exitsField.addChild(w);
        return im;
    }
    
    public void ChangeName(String newName){
        name.setLabel(newName);
    }

}
