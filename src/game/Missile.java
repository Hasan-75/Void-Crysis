/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

/**
 *
 * @author Hasan
 */
public class Missile extends Sprite {
    
    int speed = 10;
    public Missile(int x, int y){
        super(x, y);
        makeMissile();
    }

    private void makeMissile() {
        loadImage("images/missileb.png");
        this.setImageDimensions(20, 50);
       // getImageDimensions();
    }
    
    public void missileMove(){
        y-=speed;
        if(y<-height){
            visible = false;
        }
    }
}
