package ff;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Represents the player's boss who will slowly chase after the player
 * mid-game.
 */
public class Boss extends GamePiece {

  private BufferedImage img;
  private int chaseDist;
  private int currChaseDist;
  
  /**
   * Constructor for Boss.
   * 
   * @param pos the current position
   */
  public Boss(Posn pos, int chaseDist) {
    super(pos);
    
    try {
      this.img = ImageIO.read(Boss.class.getResource("/images/boss.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    this.chaseDist = chaseDist;
    this.currChaseDist = chaseDist;
  }
  
  /**
   * When called, the boss will move one step closer to the player.
   * 
   * @param p the player
   */
  public void chase(Player p) {
    if (this.currChaseDist > 0) {
      if (Math.abs(getPos().x - p.getPos().x) > Math.abs(getPos().y - p.getPos().y)) {
        if (getPos().x > p.getPos().x) {
          getPos().move(-20, 0);
        } else {
          getPos().move(20, 0);
        }
      } else {
        if (getPos().y > p.getPos().y) {
          getPos().move(0, -20);
        } else {
          getPos().move(0, 20);
        }
      }

      this.currChaseDist -= 1;
    }
  }
  
  public void resetChaseDist() {
    this.currChaseDist = this.chaseDist;
  }

  @Override
  public void draw(Graphics g) {
    g.drawImage(img, getPos().x - 10, getPos().y- 10, null);
  }
  
}