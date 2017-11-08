package ff;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

/**
 * Represents the player with a position.
 */
public class Player extends GamePiece {
  
  private BufferedImage img;
  
  /**
   * Constructor for player.
   * 
   * @param pos the initial position
   */
  public Player(Posn pos) {
    super(pos);
    
    try {
      this.img = ImageIO.read(Player.class.getResource("/images/player.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  /**
   * Moves the player based on the given list of key codes.
   *   <br> W - up (y -= 20)
   *   <br> A - left (x -= 20)
   *   <br> S - down (y += 20)
   *   <br> D - right (x += 20)
   * @param keyCodes list of key codes
   */
  public void move(ArrayList<Integer> keyCodes) {
    
    if (keyCodes.contains(KeyEvent.VK_W)) {
      if (getPos().y > 20) {
        getPos().move(0, -20);
      }
    }
    
    if (keyCodes.contains(KeyEvent.VK_A)) {
      if (getPos().x > 60) {
        getPos().move(-20, 0);
      }
    }
    
    if (keyCodes.contains(KeyEvent.VK_S)) {
      if (getPos().y < 720) {
        getPos().move(0, 20);
      }
    }
    
    if (keyCodes.contains(KeyEvent.VK_D)) {
      if (getPos().x < 760) {
        getPos().move(20, 0);
      }
    }
  }
  
  /**
   * Draws the player.
   */
  public void draw(Graphics g) {
    //g.setColor(Color.GREEN);
    //g.fillRect(getPos().x - 10, getPos().y - 10, 20, 20);
    g.drawImage(img, getPos().x - 10, getPos().y - 10, null);
  }
}