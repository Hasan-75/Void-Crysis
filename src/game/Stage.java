/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author Hasan
 */
public class Stage extends JPanel implements ActionListener{
    Image image, spaceship;
    ShipControl control;
    Spaceship sps;
    JLabel leftAliens;
    Game g;
    int left = 0;
    String lblstr = "  Aliens Left: ";
    int x, y, dx, dy, w, h, initial_bgY = Game.h-4500, bgY = initial_bgY, dBgy = 1;
    public Stage(Game g){
        this.g = g;
        makeStage();
        setProperties();
    }
    
    public Stage(String s){
        
    }
    
    final void makeStage(){
        setFocusable(true);
        setSize(Game.w, Game.h);
        setDoubleBuffered(true);
        add(addLbl());
        setLayout(null);
        loadImage();
        control = new ShipControl(this);
    }
    
    JLabel addLbl(){
        leftAliens = new JLabel(lblstr+left);
        leftAliens.setBounds(Game.w-200, Game.h-150, 150, 80);
        leftAliens.setForeground(Color.WHITE);
        leftAliens.setFont(new Font("Arial", 14, 20));
        leftAliens.setBorder(BorderFactory.createLineBorder(Color.white));
        return leftAliens;
    }
    
    private void setProperties() {
        x = (this.getWidth()/2)-40;
        y = this.getHeight()-200;
        w = 120;
        h= 150;
        dx = 0;
        dy =0;
    }

    void loadImage() {
        ImageIcon ii = new ImageIcon("images/space5.png");
        image = ii.getImage();
        setProperties();
        sps = new Spaceship(x, y);
        sps.setImageDimensions(w, h);
        spaceship = sps.getShip();
    }
    
    
    
    Image getBGImage(){
        return image;
    }

    @Override
    public void paintComponent(Graphics g){
        //System.out.println(image.getHeight(this));
        //System.out.println(image.getWidth(this));
        g.drawImage(image, 0, bgY, Game.w , image.getHeight(this), this);
        if(bgY > 0){
            bgY = initial_bgY;

        }
        //g.drawImage(spaceship, x, y, w, h, this);

        if(sps.isVisible())
            g.drawImage(spaceship, x, y, w, h, this);
        drawMissiles(g);
        drawAliens(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        x += dx;
        y += dy;
        bgY += dBgy;
        //System.out.println(bgY);
        updateMissiles();
        updateAliens();
        control.checkCollisions(new Rectangle(x, y, w, h));
        repaint();
        if(left == 0 || !(control.ship.isVisible()) ){
            control.timer.stop();
            gameOver();
            
        }
    }


    private void gameOver() {
        this.getGraphics().dispose();
        new Thread(){
            public void run(){
                try {
                    Thread.sleep(500);
                    if(left == 0)
                        leftAliens.setText("    Congrats!!!");
                    else
                        leftAliens.setText("    Game Over");
                    leftAliens.setLocation((Game.w-150)/2,(Game.h-80)/2);
                    control.aliens.clear();
                    control.ship.setVisible(false);
                    repaint();
                    //control.timer.stop();
                    Thread.sleep(1500);
                    g.dispose();
                    StartPan.makeStartPanel();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Stage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }.start();
        
        
    }
    
    private void drawMissiles(Graphics g) {
        ArrayList<Missile> missiles = control.getMissiles();
        for (Missile missile : missiles) {
            g.drawImage(missile.getImage(), missile.getX(),
                    missile.getY(), missile.width, missile.height,  this);
        }
    
    }

    
   private void updateMissiles() {
        ArrayList<Missile> missiles = control.getMissiles();

        for (int i = 0; i < missiles.size(); i++) {

            Missile missile = missiles.get(i);

            if (missile.isVisible()) {

                missile.missileMove();
            } else {
                missiles.remove(i);
            }
        }
    } 
   
   
   private void drawAliens(Graphics g){
        ArrayList<Aliens> aliens = control.getAliens();
        for (Aliens alien : aliens) {
            g.drawImage(alien.getImage(), alien.getX(),
                    alien.getY(), alien.width, alien.height,  this);
        }
    }
   
   
   private void updateAliens() {
        ArrayList<Aliens> aliens = control.getAliens();
        left = aliens.size();
        leftAliens.setText(lblstr+left);
        for (int i = 0; i < aliens.size(); i++) {
            Aliens a = aliens.get(i);

            if (a.isVisible()) {

                a.move();
            } else {
                aliens.remove(a);
            }
        }
    } 

   
    
}