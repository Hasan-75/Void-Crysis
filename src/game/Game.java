/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import javax.swing.JFrame;

/**
 *
 * @author Hasan
 */
public class Game extends JFrame{
    
    static int w = 1200, h = 600;
    public Game(){
        init();
    }
    
    final void init(){
        setSize(w, h);
        setLayout(null);
        //add(new SP());
        add(new Stage());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        setVisible(true);
    }
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Game game = new Game();
            }
        });
        
        
    }
    
}
