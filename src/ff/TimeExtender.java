package ff;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Represents a time extender that will spawn randomly in the game.
 */
public class TimeExtender extends GamePiece {

  private int amount;
  private BufferedImage img;
  
  /**
   * Constructor for TimeExtender.
   * 
   * @param pos the initial position.
   * @param amount the amount of time to extend.
   */
  public TimeExtender(Posn pos, int amount) {
    super(pos);
    this.amount = amount;
    
    try {
      this.img = ImageIO.read(TimeExtender.class.getResource("/images/timeEx.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  /**
   * Getter method for amount.
   * 
   * @return the amount of time to extend
   */
  public int getAmount() {
    return amount;
  }

  @Override
  public void draw(Graphics g) {
    //g.setColor(Color.ORANGE);
    //g.fillOval(getPos().x - 10, getPos().y - 10, 20, 20);
    g.drawImage(img, getPos().x - 10, getPos().y - 10, null);
    
    g.setFont(new Font("Consolas", 0, 18));
    g.setColor(Color.BLACK);
    g.drawString(Integer.toString((int)Math.ceil((double)amount / 10)), getPos().x - 5, getPos().y + 5);
  }

  public void countdown() {
    this.amount -= 1;
  } 
}