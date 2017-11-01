package ff;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * Represents a faxing document by giving it a position and a number.
 */
public class FaxDocument extends GamePiece {
  
  private int num;
  
  /**
   * Constructor for FaxDocument.
   * 
   * @param num the number on the document
   * @param pos the initial position of the document
   */
  public FaxDocument(int num, Posn pos) {
    super(pos);
    this.num = num;
  }
  
  /**
   * Getter method for num.
   * 
   * @return the number on the document
   */
  public int getNumber() {
    return this.num;
  }
  
  /**
   * Sets the position of the document to the same position as the Player.
   * 
   * @param p the Player
   */
  public void follow(Player p) {
    this.setPos(p.getPos());
  }
  
  /**
   * Draws the document.
   */
  public void draw(Graphics g) {
    g.setColor(Color.CYAN);
    g.fillRect(getPos().x - 10, getPos().y - 10, 20, 20);
    
    g.setFont(new Font("Consolas", 0, 18));
    g.setColor(Color.BLACK);
    g.drawString(Integer.toString(num), getPos().x - 5, getPos().y + 5);
  }
}