/* 
 */
package org.vap.workspace.widgets;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import org.netbeans.api.visual.widget.ConnectionWidget;
import org.netbeans.api.visual.widget.Scene;
import org.netbeans.api.visual.widget.Widget;

/**
 *
 * @author Oleg Bantysh
 */
public class WorkspaceConnectionWidget extends ConnectionWidget {
    private Shape shape = new Rectangle();
    private List<Rectangle> selectableArea = new ArrayList();
    private boolean isHovering = false;
    private Font defaultFont;

    public WorkspaceConnectionWidget(Scene scene) {
        super(scene);
    }

    @Override
    protected void paintWidget() {
        Point2D source = this.getFirstControlPoint();
        Point2D target = this.getLastControlPoint();
        Graphics2D g2 = this.getGraphics();
        double distance = source.distance(target);
        double tangent = Math.max(-50, Math.min(50, distance));
        Point2D startTangent = new Point2D.Double(source.getX() + tangent, source.getY());
        Point2D endTangent = new Point2D.Double(target.getX() - tangent, target.getY() - (tangent * 0.5f));
        selectableArea = new ArrayList();
        
        for(int i = 0; i<(int)distance; i+=5){
            Point2D base = t(source, startTangent, target, endTangent, (float)(i/distance));
            selectableArea.add(new Rectangle((int)base.getX() - 10, (int)base.getY() - 10, 20, 20));
        }

        shape = new CubicCurve2D.Double(source.getX(), source.getY(),
                startTangent.getX(), startTangent.getY(), endTangent.getX(), endTangent.getY(), target.getX(), target.getY());
        if(isHovering){
            g2.setColor(new Color(65, 105, 225, 150));
            g2.setStroke(new BasicStroke(5));
        }else{
            g2.setColor(new Color(65, 105, 225, 100));
            g2.setStroke(new BasicStroke(3));
        }     
        g2.draw(shape);
    }  
    
    @Override
    public boolean isHitAt (Point localLocation) {
        boolean result = false;
        for(Rectangle r: selectableArea){
            if(r.contains(localLocation)){
                result = true;
            }
        }
        return result;
    }
    
    private static Point2D t (Point2D start, Point2D startT, 
            Point2D end, Point2D endT, float t)
        {

            double ax = start.getX(), ay = start.getY(), dx = end.getX(), dy = end.getY();
            double bx = startT.getX(), by = startT.getY(), cx = endT.getX(), cy = endT.getY();
            double px_t = (B0(t) * ax) + (B1(t) * bx) + (B2(t) * cx) + (B3(t) * dx);
            double py_t = (B0(t) * ay) + (B1(t) * by) + (B2(t) * cy) + (B3(t) * dy);

            return new Point2D.Double(px_t, py_t);
        }
    
    
        private static float B0(float t)
        {
            return (1 - t) * (1 - t) * (1 - t);
        }


        private static float B1(float t)
        {
            return 3 * t * (1 - t) * (1 - t);
        }


        private static float B2(float t)
        {
            return 3 * t * t * (1 - t);
        }


        private static float B3(float t)
        {
            return t * t * t;
        }
        
        public void Hower(){
            isHovering = true;
            List<Widget> children = this.getChildren();
            for(Widget w: children){
                //w.setBackground(new Color(65, 105, 225, 150));
                defaultFont = w.getFont();
                w.setFont(new Font(defaultFont.getName(), Font.BOLD, defaultFont.getSize()+1));
                w.revalidate();
            }
        }
        
        public void Unhower(){
            isHovering = false;
            List<Widget> children = this.getChildren();
            for(Widget w: children){
                w.setFont(defaultFont);
                w.revalidate();
            }
        }
}
