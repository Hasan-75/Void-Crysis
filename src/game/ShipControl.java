/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;


import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.Timer;


/**
 *
 * @author Hasan
 */
public class ShipControl implements KeyListener, MouseListener{
    Image spaceship;
    Spaceship ship;
    Timer timer;
    Stage stage;
    ArrayList<Missile> missiles;
    ArrayList<Aliens> aliens;
    int delay = 10;
    int mx, my;
    int numOfAliens = 10+(int)(Math.random()*25);
    private int[][] pos = new int[numOfAliens][2];
    /*private final int[][] pos = {
        {27,28}, {113,26}, {442,59}, {447,59}, {652,59},
        {278,17}, {418,23}, {333,13}, {592,17}, {726,17},
        {846,32}, {927,21}, {962,29}, {963,30}
    };*/

    
    public ShipControl(Stage stage){
        this.stage = stage;
        spaceship = stage.spaceship;
        ship = stage.sps;
        timer = new Timer(delay, stage);
        this.stage.setFocusable(true);
        initAliens();
        timer.start();
        this.stage.addMouseListener(this);
        this.stage.addKeyListener(this);
        missiles = new ArrayList<>();
    }
    
    public void initAliens(){
        aliens = new ArrayList<>();
        initPos();
        for (int[] p : pos) {
            aliens.add(new Aliens(p[0], p[1]));
        }
        for (Aliens a : aliens) {
            a.mt.go(a);
        }
    }
    
    void setMissileProperties(int x, int y) {
        this.mx = x;
        this.my = y;
    }
    
    public ArrayList<Missile> getMissiles() {
        return missiles;
    }
    
    public ArrayList<Aliens> getAliens() {
        return aliens;
    }
    
    public void shoot(){
        missiles.add(new Missile(stage.x+50, stage.y-35));
        
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == VK_UP){
            stage.dy = -3;
            stage.spaceship = stage.sps.getShipOn();
        }
        else if(e.getKeyCode() == VK_DOWN){
            stage.dy = 3;
            stage.spaceship = stage.sps.getShipOn();
        }
        else if(e.getKeyCode() == VK_RIGHT){
            stage.dx = 3;
            stage.spaceship = stage.sps.getShipOn();
        }
        else if(e.getKeyCode() == VK_LEFT){
            stage.dx = -3;
            stage.spaceship = stage.sps.getShipOn();
        }
        if(e.getKeyCode() == VK_SPACE){
            shoot();
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == VK_UP){
            stage.dy = 0;
            stage.spaceship = stage.sps.getShip();
        }
        else if(e.getKeyCode() == VK_DOWN){
            stage.dy = 0;
            stage.spaceship = stage.sps.getShip();
        }
        else if(e.getKeyCode() == VK_RIGHT){
            stage.dx = 0;
            stage.spaceship = stage.sps.getShip();
        }
        else if(e.getKeyCode() == VK_LEFT){
            stage.dx = 0;
            stage.spaceship = stage.sps.getShip();
        }
    }
    
    public void checkCollisions(Rectangle shipA) {
        
        //System.out.println(shipA);
        Rectangle r3 = shipA;
        for (Aliens alien : aliens) {
            Rectangle r2 = alien.getArea();
            if (r3.intersects(r2)) {
                //stage.sps.setVisible(false);
                //alien.setVisible(false);
                blast(alien, stage.sps);
            }
        }

        ArrayList<Missile> ms = this.getMissiles();
        for (Missile m : ms) {
            Rectangle r1 = m.getArea();
            for (Aliens alien : aliens) {
                Rectangle r2 = alien.getArea();
                if (r1.intersects(r2)) {
                    //m.setVisible(false);
                    //alien.setVisible(false);
                    blast(alien, m);
                }
            }
        }
    }

    void initPos(){
        for(int r = 0; r < numOfAliens; r++){
            pos[r][0] = 30+(int)(Math.random()*(Game.w -30));
            pos[r][1] = -(int)(Math.random()*700);
        }

    }
    
    void blast(Aliens alien, Spaceship ss){
        ImageIcon fire = new ImageIcon("images/blast2.gif");
        //Thread.sleep();
        new Thread(){
            public void run(){
                try {
                    alien.image = fire.getImage();
                    stage.spaceship = fire.getImage();
                    Thread.sleep(300);
                    
                } catch (InterruptedException ex) {
                    Logger.getLogger(ShipControl.class.getName()).log(Level.SEVERE, null, ex);
                }
                ss.setVisible(false);
                alien.setVisible(false);
            }
        }.start();
        
    }
    
    void blast(Aliens alien, Missile m){
        ImageIcon fire = new ImageIcon("images/blast2.gif");
        alien.image = fire.getImage();
        m.setVisible(false);
        //Thread.sleep();
        new Thread(){
            public void run(){
                try {
                    Thread.sleep(300);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ShipControl.class.getName()).log(Level.SEVERE, null, ex);
                }
                alien.setVisible(false);
            }
        }.start();
        
    }
    

    @Override
    public void mouseClicked(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("{" + e.getX() + "," + e.getY() + "}");
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
