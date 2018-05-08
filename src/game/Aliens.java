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

    private final int INITIAL_Y = 0;

    public Aliens(int x, int y) {
        super(x, y);

        initAlien();
    }

    private void initAlien() {

        loadImage("images/alien.png");
        Dimension d = this.getDefaultDimension();
        this.setImageDimensions(d);
    }

    public void move() {

        if (y > Game.h) {
            y = INITIAL_Y;
        }

        y += 1;
    }
}