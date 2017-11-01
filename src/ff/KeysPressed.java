package ff;

import java.util.ArrayList;

/**
 * Represents a container for every key that is currently pressed.
 */
public class KeysPressed {
  
  private ArrayList<Integer> keys;
  
  /**
   * Constructor for KeysPressed.
   */
  public KeysPressed() {
    this.keys = new ArrayList<Integer>();
  }
  
  /**
   * Getter method for keys.
   * 
   * @return the list of key codes.
   */
  public ArrayList<Integer> getKeys() {
    return keys;
  }
  
  /**
   * Adds a key code to the list.
   * 
   * @param keyCode the given key code
   */
  public void addKey(int keyCode) {
    if (!keys.contains(keyCode)) {
      keys.add(keyCode);
    }
  }
  
  /**
   * Removes a keyCode.
   * 
   * @param keyCode the given keyCode
   */
  public void removeKey(int keyCode) {
    keys.remove(new Integer(keyCode));
  }
}