import javax.swing.JFrame;

import ff.Canvas;

/**
 * This is the main method that will run the game.
 * 
 * @author Marcos Lopez
 */
public class Main {
  public static void main(String args[]) {
    JFrame frame = new JFrame("Faxing Game v0.5");
    frame.setSize(800, 800);
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);
    
    Canvas c = new Canvas();
    frame.add(c);
    
    frame.setVisible(true);
  }
}