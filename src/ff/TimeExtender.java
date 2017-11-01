package ff;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * Represents a time extender that will spawn randomly in the game.
 */
public class TimeExtender extends GamePiece {

  private int amount;
  
  /**
   * Constructor for TimeExtender.
   * 
   * @param pos the initial position.
   * @param amount the amount of time to extend.
   */
  public TimeExtender(Posn pos, int amount) {
    super(pos);
    this.amount = amount;
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
    g.setColor(Color.ORANGE);
    g.fillOval(getPos().x - 10, getPos().y - 10, 20, 20);
    
    g.setFont(new Font("Consolas", 0, 18));
    g.setColor(Color.BLACK);
    g.drawString(Integer.toString(amount), getPos().x - 5, getPos().y + 5);
  } 
}