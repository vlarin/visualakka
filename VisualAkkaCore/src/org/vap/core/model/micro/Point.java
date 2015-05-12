/* 
 */
package org.vap.core.model.micro;

/**
 *
 * @author Oleg Bantysh
 */
    public class Point{

    /**
     *
     */
    public int x;

    /**
     *
     */
    public int y;

    /**
     *
     * @param x
     * @param y
     */
    public Point(int x, int y){
            this.x = x;
            this.y = y;
        }

    /**
     *
     */
    public Point(){
            
        }

    /**
     *
     * @param p
     */
    public Point(java.awt.Point p){
//            this.x = p.x/17;
//            this.y = p.y/17;
            this.x = p.x;
            this.y = p.y;
        }
    }
