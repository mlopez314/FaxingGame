package ff;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * Represents a faxing machine that consists of a number and a position.
 */
public class FaxMachine extends GamePiece {
  
  private int num;
  
  /**
   * Constructor for FaxMachine.
   * 
   * @param num the number on the fax machine
   * @param pos the initial position of the fax machine
   */
  public FaxMachine(int num, Posn pos) {
    super(pos);
    this.num = num;
  }
  
  /**
   * Getter method for num.
   * 
   * @return the number
   */
  public int getNumber() {
    return this.num;
  }
  
  /**
   * Draws the fax machine.
   */
  public void draw(Graphics g) {
    g.setColor(Color.PINK);
    g.fillRect(getPos().x - 10, getPos().y - 10, 20, 20);
    
    g.setFont(new Font("Consolas", 0, 18));
    g.setColor(Color.BLACK);
    g.drawString(Integer.toString(num), getPos().x - 5, getPos().y + 5);
  }
}