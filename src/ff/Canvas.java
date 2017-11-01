package ff;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * This is the canvas that will display the current state of the game.
 */
public class Canvas extends JPanel implements KeyListener, ActionListener {

  private GameState gs;
  private Timer tm = new Timer(10, this);
  private KeysPressed keys;
  private int keyDelay;
  private int keyTime;
  
  /**
   * Constructor for canvas that adds a KeyListener, GameState, and KeysPressed.
   * Also starts the timer.
   */
  public Canvas() {
    addKeyListener(this);
    this.gs = new GameState();
    this.keys = new KeysPressed();
    this.keyDelay = 5;
    this.keyTime = 0;
    tm.start();
  }
  
  @Override
  public void addNotify() {
    super.addNotify();
    requestFocus();
  }
  
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    gs.draw(g);
  }
  
  @Override
  public void keyPressed(KeyEvent key) {
    keys.addKey(key.getKeyCode());
    repaint();
  }

  @Override
  public void keyReleased(KeyEvent key) {
    keys.removeKey(key.getKeyCode());
  }

  @Override
  public void keyTyped(KeyEvent arg0) {
    // TODO Auto-generated method stub
  }
  
  @Override
  public void actionPerformed(ActionEvent arg0) {
    
    if (!keys.getKeys().isEmpty()) {
      if (this.keyTime == 0) {
        gs.updateKeyPress(keys.getKeys());
      }
      
      this.keyTime += 1;
      
      if (this.keyTime > this.keyDelay) {
        this.keyTime = 0;
      }
    } else {
      this.keyTime = 0;
    }
    
    gs.updateTimer();
    repaint();
  }
}