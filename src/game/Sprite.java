/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
/**
 *
 * @author Hasan
 */


class Sprite {

    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected boolean visible;
    protected Image image;

    public Sprite(int x, int y) {

        this.x = x;
        this.y = y;
        visible = true;
    }

    protected void loadImage(String imageName) {

        ImageIcon ii = new ImageIcon(imageName);
        image = ii.getImage();
    }
    protected void setAllProperties(int x, int y, int w, int h){
        this.x = x;
        this.y = y;
        width = w;
        height = h;
    }
    protected void setImageDimensions(int w, int h) {

        width = w;
        height = h;
    }

    protected void setImageDimensions(Dimension d) {

        width = d.width;
        height = d.height;
    }

    public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }
    
    public Rectangle getArea(){
        return new Rectangle(x, y, width, height);
    }
    
    public Dimension getDefaultDimension(){
        return new Dimension(70, 60);
    }
}