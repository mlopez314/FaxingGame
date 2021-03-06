package ff;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Represents a faxing document by giving it a position and a number.
 */
public class FaxDocument extends GamePiece {
  
  private int num;
  private BufferedImage img;
  
  /**
   * Constructor for FaxDocument.
   * 
   * @param num the number on the document
   * @param pos the initial position of the document
   */
  public FaxDocument(int num, Posn pos) {
    super(pos);
    this.num = num;
    
    try {
      this.img = ImageIO.read(FaxDocument.class.getResource("/images/document.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
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
    //g.setColor(Color.CYAN);
    //g.fillRect(getPos().x - 9, getPos().y - 9, 18, 18);
    g.drawImage(img, getPos().x - 10, getPos().y - 10, null);
    
    g.setFont(new Font("Consolas", 0, 18));
    g.setColor(Color.BLACK);
    g.drawString(Integer.toString(num), getPos().x - 5, getPos().y + 5);
  }
}