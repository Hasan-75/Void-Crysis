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
    
    static int w , h ;
    public Game(){
        init();
    }
    
    final void init(){
        
        setExtendedState(this.getExtendedState() | this.MAXIMIZED_BOTH);
        setSize(w, h);
        setLayout(null);
        setTitle("Void Crysis");
        //add(new SP());
        add(new Stage(this));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        setVisible(true);
    }
    
    
    public static void startGame() {
        // TODO code application logic here
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Game game = new Game();
            }
        });
        
        
    }
    
}
