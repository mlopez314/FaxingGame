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
  private Timer tm = new Timer(100, this);
  private KeysPressed keys;
  private boolean isPaused;
  
  /**
   * Constructor for canvas that adds a KeyListener, GameState, and KeysPressed.
   * Also starts the timer.
   */
  public Canvas() {
    addKeyListener(this);
    this.gs = new GameState();
    this.keys = new KeysPressed();
    this.isPaused = false;
    tm.start();
  }
  
  public void togglePause() {
    this.isPaused = !this.isPaused;
    
    if (this.isPaused) {
      tm.stop();
    } else {
      tm.restart();
    }
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
    
    if (this.isPaused) {
      g.drawString("Paused", 250, 250);
    }
  }
  
  @Override
  public void keyPressed(KeyEvent key) {
    
    if (key.getKeyCode() == KeyEvent.VK_P) {
      togglePause();
      repaint();
    } else if (!this.isPaused) {
      if (!keys.getKeys().contains(key.getKeyCode()) && keys.getKeys().isEmpty()) {
        keys.addKey(key.getKeyCode());
        gs.updateKeyPress(keys.getKeys());
        gs.recentKeyPress();
      } else {
        keys.addKey(key.getKeyCode());
      }
      
      repaint();
    }
  }

  @Override
  public void keyReleased(KeyEvent key) {
    keys.removeKey(key.getKeyCode());
  }

  @Override
  public void keyTyped(KeyEvent arg0) {
    // TODO Auto-generated method stub
  }
  
  private long currSysTime = System.nanoTime();
  private long elapsedTime = System.nanoTime();
  
  @Override
  public void actionPerformed(ActionEvent arg0) {  
    this.elapsedTime = System.nanoTime() - currSysTime;
    this.currSysTime = System.nanoTime();
    gs.updateKeyPress(keys.getKeys());   
    gs.updateTimer();
    repaint();
    
    if (elapsedTime > 110000000) {
      tm.setDelay(tm.getDelay() - 1);
      System.out.println(tm.getDelay());
    } else if (elapsedTime < 90000000) {
      tm.setDelay(tm.getDelay() + 1);
      System.out.println(tm.getDelay());
    }
  }
}