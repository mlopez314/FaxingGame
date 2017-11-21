package ff;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

/**
 * Represents the model of the current state of the game.
 */
public class GameState {
  
  private Player player;
  private Boss boss;
  private ArrayList<FaxDocument> documents;
  private ArrayList<FaxMachine> machines;
  
  private ArrayList<TimeExtender> timeExs;
  private boolean canAddTE;
  private int totalTimeExtended;
  
  private Random rng = new Random();
  private int currDocIdx;
  
  private long sysStartTime;
  private int startTime;
  private int timeLeft;
  private int elapsedTime;
  
  private boolean gameWon;
  private boolean gameLost;
  
  private int round;
  private int points;
  
  private boolean recentKeyPress;
  
  /**
   * Constructor for GameState.
   */
  public GameState() {
    this.player = new Player(this.generateRandomPosn());
    this.boss = new Boss(this.generateRandomPosn(), 1);
    
    this.machines = new ArrayList<FaxMachine>();
    for (int i = 1; i <= 5; i++) {
      machines.add(new FaxMachine(i, generateRandomPosn()));
    }
    
    
    this.documents = new ArrayList<FaxDocument>();
    for (int i = 1; i <= 5; i++) {
      for (int j = 0; j < 3; j++) {
        documents.add(new FaxDocument(i, generateRandomPosn()));
      }
    }
    
    this.timeExs = new ArrayList<TimeExtender>();
    this.canAddTE = true;
    this.totalTimeExtended = 0;
    
    this.currDocIdx = -1;
    
    this.sysStartTime = System.nanoTime();
    
    this.startTime = 100;
    this.timeLeft = this.startTime;
    this.elapsedTime = 0;
    
    this.gameWon = false;
    this.gameLost = false;
    
    this.round = 1;
    this.points = 0;
    
    this.recentKeyPress = false;
  }
  
  /**
   * Draws the entire state of the game.
   * 
   * @param g the Graphics component
   */
  public void draw(Graphics g) {
    g.setColor(Color.WHITE);
    g.fillRect(0, 0, 800, 800);
    
    g.setColor(Color.LIGHT_GRAY);
    g.fillRect(50, 10, 720, 720);
    
    g.setColor(Color.WHITE);
    g.fillRect(50, 10, 720, this.timeLeft * (720 / this.startTime));
    
    g.setColor(Color.BLACK);
    g.drawRect(50, 10, 720, 720);
    
    this.player.draw(g);
    this.boss.draw(g);
    
    for (FaxMachine fm : this.machines) {
      fm.draw(g);
    }
    
    for (FaxDocument fd : this.documents) {
      fd.draw(g);
    }
    
    for (TimeExtender te : this.timeExs) {
      te.draw(g);
    }
    
    g.setColor(Color.BLACK);
    g.setFont(new Font("Consolas", 0, 18));
    
    g.drawString("Lvl", 5, 20);
    g.drawString(Integer.toString(round), 5, 40);
    
    g.drawString("Docs", 5, 80);
    g.drawString(Integer.toString(this.documents.size()), 5, 100);
    
    g.drawString("Pts", 5, 140);
    g.drawString(Integer.toString(points), 5, 160);
    
    g.drawString("Time", 5, 200);
    g.drawString(Integer.toString(timeLeft), 5, 220);
    
    if (gameWon) {
      g.drawString("You won", 350, 350);
    }
    
    if (gameLost) {
      g.drawString("You are fired!", 350, 350);
      g.drawString("Press ESC to restart", 350, 370);
    }
  }
  
  /**
   * Generates a random Posn on the board.
   * 
   * @return a randomly generated Posn.
   */
  private Posn generateRandomPosn() {
    return new Posn((rng.nextInt(36) + 3) * 20,
                    (rng.nextInt(36) + 1) * 20);
  }
  
  /**
   * Updates the state of the game given the list of key codes.
   * 
   * @param keyCodes the list of key codes
   */
  public void updateKeyPress(ArrayList<Integer> keyCodes) {
    if (keyCodes.contains(KeyEvent.VK_ESCAPE)) {
      this.reset();
    }
    
    if (this.timeLeft > 0 && !this.gameLost) {
      if (this.currDocIdx > -1) {
        
        if (this.recentKeyPress == false) {
          this.player.move(keyCodes);
        } else {
          this.recentKeyPress = false;
        }
        
        this.documents.get(currDocIdx).follow(this.player);
        
        
        boolean foundMachine = false;
        for (int i = 0; !foundMachine && i < this.machines.size(); i++) {
          if (this.machines.get(i).getPos().equals(this.documents.get(currDocIdx).getPos())
              && this.machines.get(i).getNumber() == this.documents.get(currDocIdx).getNumber()) {
            this.documents.remove(currDocIdx);
            this.currDocIdx = -1;
            foundMachine = true;
            this.points += 10;
          }
        }
        
      } else {
        
        if (this.recentKeyPress == false) {
          this.player.move(keyCodes);
        } else {
          this.recentKeyPress = false;
        }
        
        boolean foundDoc = false;
        for (int i = 0; !foundDoc && i < this.documents.size(); i++) {
          if (this.documents.get(i).getPos().equals(this.player.getPos())) {
            foundDoc = true;
            this.currDocIdx = i;
          }
        }
      }
      
      for (int i = 0; i < this.timeExs.size(); i++) {
        if (this.timeExs.get(i).getPos().equals(this.player.getPos())) {
          this.totalTimeExtended += 5;
          this.timeExs.remove(i);
          i--;
          break;
        }
        
        if (this.timeExs.get(i).getAmount() == 0) {
          this.timeExs.remove(i);
          i--;
          break;
        }
        
        this.timeExs.get(i).countdown();
      }
      
      if (this.documents.isEmpty()) {
        this.gameWon = true;
        this.reset(this.startTime - 5, this.round + 1, this.points + 10 * this.timeLeft);
      }
    }    
  }

  /**
   * Updates the state of the game based on the elapsed time.
   */
  public void updateTimer() {
    if (this.timeLeft > 0 && !gameWon) {
      long elapsedSysTime = System.nanoTime() - this.sysStartTime;
      this.elapsedTime = (int)(elapsedSysTime / 1000000000);
      this.timeLeft = this.startTime - this.elapsedTime + this.totalTimeExtended;
      
      if (canAddTE && this.elapsedTime % 10 == 0 && this.elapsedTime > 0) {
        this.timeExs.add(new TimeExtender(generateRandomPosn(), 50));
        this.canAddTE = false;
      }
      
      if (this.elapsedTime % 11 == 0  && this.elapsedTime > 0) {
        this.canAddTE = true;
      }
      
      if (this.timeLeft == 0) {
        this.gameLost = true;
      }
      
      if (this.elapsedTime % 2 == 0 && this.elapsedTime > 0 && !this.gameLost) {
        this.boss.chase(player);
      }
      
      if (this.elapsedTime % 2 != 0 && this.elapsedTime > 0 && !this.gameLost) {
        this.boss.resetChaseDist();
      }
      
      if (this.player.getPos().equals(this.boss.getPos()) && this.elapsedTime > 1) {
        this.gameLost = true;
      }
    }
  }
  
  /**
   * Resets the entire game.
   */
  public void reset() {
    this.player = new Player(this.generateRandomPosn());
    this.boss = new Boss(this.generateRandomPosn(), 1);
    
    this.machines = new ArrayList<FaxMachine>();
    for (int i = 1; i <= 5; i++) {
      machines.add(new FaxMachine(i, generateRandomPosn()));
    }
    
    
    this.documents = new ArrayList<FaxDocument>();
    for (int i = 1; i <= 5; i++) {
      for (int j = 0; j < 3; j++) {
        documents.add(new FaxDocument(i, generateRandomPosn()));
      }
    }
    
    this.timeExs = new ArrayList<TimeExtender>();
    this.canAddTE = true;
    this.totalTimeExtended = 0;
    
    this.currDocIdx = -1;
    
    this.sysStartTime = System.nanoTime();
    
    this.startTime = 100;
    this.timeLeft = this.startTime;
    this.elapsedTime = 0;
    
    this.gameWon = false;
    this.gameLost = false;
    
    this.round = 1;
    this.points = 0;
    
    this.recentKeyPress = false;
  }
  
  /**
   * Resets the game based on the current round, timer, and score.
   * 
   * @param newTimer new timer
   * @param round the current round
   * @param points the player's current score
   */
  public void reset(int newTimer, int round, int points) {
    this.player = new Player(this.generateRandomPosn());
    this.boss = new Boss(this.generateRandomPosn(), round);
    
    this.machines = new ArrayList<FaxMachine>();
    for (int i = 1; i <= 5; i++) {
      machines.add(new FaxMachine(i, generateRandomPosn()));
    }
    
    
    this.documents = new ArrayList<FaxDocument>();
    for (int i = 1; i <= 5; i++) {
      for (int j = 0; j < 3; j++) {
        documents.add(new FaxDocument(i, generateRandomPosn()));
      }
    }
    
    this.timeExs = new ArrayList<TimeExtender>();
    this.canAddTE = true;
    this.totalTimeExtended = 0;
    
    this.currDocIdx = -1;
    
    this.sysStartTime = System.nanoTime();
    
    this.startTime = newTimer;
    this.timeLeft = this.startTime;
    this.elapsedTime = 0;
    
    this.gameWon = false;
    this.gameLost = false;
    
    this.round = round;  
    this.points = points;
    
    this.recentKeyPress = false;
  }

  public void recentKeyPress() {
    this.recentKeyPress = true;
  }
}