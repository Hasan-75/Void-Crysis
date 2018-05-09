/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Dimension;

/**
 *
 * @author Hasan
 */
public class Aliens extends Sprite {

    //private final int INITIAL_Y = 0;

    int inPos[] = new int[2];
    int dx = 0;
    MovementThread mt = new MovementThread();
    public Aliens(int x, int y) {
        super(x, y);
        inPos[0] = x;
        inPos[1] = y;
        initAlien();
    }

    private void initAlien() {

        loadImage("images/alien.png");
        Dimension d = this.getDefaultDimension();
          this.setImageDimensions(d);
    }

    public void move() {

        if (y > Game.h) {
            y = inPos[1];

        }
        if (x > Game.w - 30) {
            dx = -1;

        }
        if (x < 30) {
            dx = 1;

        }

        /*if((int)(Math.random()*1000)%33 == 0){
            dx = 1;
        } else if((int)(Math.random()*1000)%29 == 0){
            dx = -1;
        }*/
        x += dx;
        y += 1;
    }
}

class MovementThread implements Runnable{
    Aliens a;
    void go(Aliens a){
        this.a = a;
        Thread t = new Thread(this);
        t.start();
    }
    @Override
    public void run() {
        while (true) {
            try {
                int limit = 500 + (int) (Math.random() * 3000);
                a.dx = 1;
                Thread.sleep(limit);
                limit = 500 + (int) (Math.random() * 3000);
                a.dx = -1;
                Thread.sleep(limit);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}