/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Hasan
 */
public class Spaceship extends Sprite{
    Image ss, sso;
    
    public Spaceship(int x, int y){
        super(x, y);
        addImg();
    }

    private void addImg() {
        ImageIcon sp = new ImageIcon("images/ship.png");
        ImageIcon sp2 = new ImageIcon("images/shipOn.png");
        ss = sp.getImage();
        sso = sp2.getImage();
    }
    
    Image getShip(){
        return ss;
    }
    
    Image getShipOn(){
        return sso;
    }
}
