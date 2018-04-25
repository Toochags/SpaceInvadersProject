import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TimerDemo1 extends JFrame implements KeyListener
{
  private Timer enemyTimer;                      // propels top enemy horizontally across screen
  private Timer enemy2Timer;                     // propels top enemy2 horizontally across screen
  private Timer enemy3Timer;
  private Timer playerTimer;                     // propels bottom player horizontally across screen
  private Timer timerBullet;                     // propels bottom player's bullet vertically up the screen
  private int playerX;                           // the x Location of player
  private int enemyX;                            // the x location of enemy
  private int enemy2X;                           // the x location of enemy2
  private int enemy3X;
  private int bulletY;                           // the y location of player's bullet
  private int bulletX;                           // the x location of player's bullet
  private int enemyMoveAmount;                   // the enemy's horizontal move amount in each timer step
  private int enemy2MoveAmount;                  // the enemy2's horizontal move amount in each timer step
  private int enemy3MoveAmount;
  private int playerMoveAmount;                  // the player's horizontal move amount in each timer step
  private int bulletMoveAmount;                  // the bullet's vertical move amount in each timer step
  private int playerScore;                       // player score
  private final static int SCREEN_WIDTH = 400;   // width of screen
  
   public TimerDemo1()             // default constructor
   {
      playerX = 200;                // initial horizontal position of player                
      enemyX = 100;                  // initial horizontal position of enemy
      enemy2X = 300;               // initial horizontal position of enemy2
      enemy3X = 200;
     
      enemyMoveAmount = 10;  
      enemy2MoveAmount = -20;
      enemy3MoveAmount = -15;
      playerMoveAmount = 10;
      bulletMoveAmount = 10;    
     
      bulletX = -10;                // initially placing bullet off the screen so its not visible
      bulletY = 180;
      
      addKeyListener(this);         // necessary to make the KeyListener work correctly
     
      // enemy moving back and forth horizontally
      enemyTimer = new Timer(200, new ActionListener()
      {

         public void actionPerformed(ActionEvent evt)
         {

            if (enemyX <= 0)                 // left boundary  detection for enemy
            {
               enemyMoveAmount = 10;
            }
            else if (enemyX >= SCREEN_WIDTH - 25) // right boundary detection for enemy
            {
               enemyMoveAmount = -10;
            }
             
            enemyX += enemyMoveAmount;       // moving enemy horizontally across screen
            repaint();
         }

      });  
      
      // enemy 2 moving back and forth horizontally
      enemy2Timer = new Timer(200, new ActionListener()
      {

         public void actionPerformed(ActionEvent evt)
         {

            if (enemy2X <= 0)                 // left boundary  detection for enemy
            {
               enemy2MoveAmount =  20;
            }
            else if (enemy2X >= SCREEN_WIDTH - 25) // right boundary detection for enemy
            {
               enemy2MoveAmount = -20;
            }
             
            enemy2X += enemy2MoveAmount;       // moving enemy horizontally across screen
            repaint();
         }

      });  
      // enemy 3 moving back and forth horizontally
      enemy3Timer = new Timer(200, new ActionListener()
      {

         public void actionPerformed(ActionEvent evt)
         {

            if (enemy3X <= 0)                 // left boundary  detection for enemy
            {
               enemy3MoveAmount = 10;
            }
            else if (enemy3X >= SCREEN_WIDTH - 25) // right boundary detection for enemy
            {
               enemy3MoveAmount = -10;
            }
             
            enemy3X += enemy3MoveAmount;       // moving enemy horizontally across screen
            repaint();
         }

      });  
            
      // player moving back and forth horizontally
      playerTimer = new Timer(200, new ActionListener()
      {

         public void actionPerformed(ActionEvent evt)
         {

            if (playerX <= 0)                   // left boundary detection for player
            {
               playerMoveAmount = 10;
            }
            else if (playerX >= SCREEN_WIDTH - 25)   // right boundary detection for player
            {
               playerMoveAmount = -10;
            }
             
            playerX += playerMoveAmount;        // moving player horizontally across screen
            repaint();
         }

      });  
       
      // bullet moving up the screen
      timerBullet = new Timer(100, new ActionListener()
      {

         public void actionPerformed(ActionEvent evt)
         {            
            bulletY -= bulletMoveAmount;        // moving bullet vertically up the screen
           
            // detecting collision with enemy
            if (bulletX >= enemyX && bulletX <= enemyX + 5        // within the width of the enemy
                           && bulletY <= 10 && bulletY >= 0)       // within the vertical span of the enemy
            {
               enemyX = -1000;
               timerBullet.stop();                                 // stop bullet immediately to avoid double score  
               enemyTimer.stop();
            }
            if (bulletX >= enemy2X + 10 && bulletX <= enemy2X + 10 // within the width of the enemy
                           && bulletY <= 10 && bulletY >= 0)       // within the vertical span of the enemy
            {
               enemy2X = -1000;
               timerBullet.stop();                                 // stop bullet immediately to avoid double score  
               enemy2Timer.stop(); 
            }  
            if (bulletX >= enemy3X + 10 && bulletX <= enemy3X + 10 // within the width of the enemy
                           && bulletY <= 10 && bulletY >= 0)       // within the vertical span of the enemy
            {
               enemy3X = -1000;
               timerBullet.stop();                                 // stop bullet immediately to avoid double score  
               enemy3Timer.stop();               
            }
            if (bulletY <= 0)                                 // bullet boundary detection at top of screen
            {
               timerBullet.stop();
            }

           
            repaint();
         }

      });  
           
      enemyTimer.start();
      enemy2Timer.start();
      enemy3Timer.start();
   }

   public void paint(Graphics g)
   {
      requestFocus();
      g.setColor(Color.gray);
      g.fillRect(0, 0, SCREEN_WIDTH, SCREEN_WIDTH);         // repaint background
     
      g.setColor(Color.blue);                              // repaint enemy at top of screen
      g.fillRect(enemyX, 20, 20, 20);
          
      g.setColor(Color.red);                              // repaint enemy2 at top of screen
      g.fillRect(enemy2X, 20, 20, 20);    
      
      g.setColor(Color.black);                              // repaint enemy2 at top of screen
      g.fillRect(enemy3X, 20, 20, 20);
          
      g.setColor(Color.green);                               // repaint player at bottom of screen  
      g.fillRect(playerX, 200, 20, 20);
                                      
      g.drawLine(bulletX, bulletY, bulletX, bulletY - 10);  // repaint bullet
          
      g.setColor(Color.orange);                              // update status
      g.drawString("press spacebar to fire bullet", 10, 280);
      g.drawString("press a to move left / d to move right", 10, 310);
      
      if (enemyX == -1000 && enemy2X == -1000 && enemy3X == -1000)
         {
            g.setColor(Color.orange);
            g.drawString("You win!", 180, 200);
         }
   }
 
   public void keyTyped(KeyEvent key)
   {
      // space bar shoots the bullet
      if (key.getKeyChar() == ' ' && !timerBullet.isRunning())
      {
         bulletX = playerX;         // line up bullet horizontally with current position of player
         bulletY = 180;          
         timerBullet.start();
      }   
      else if (key.getKeyChar() == 'a' && playerX + 0 <= SCREEN_WIDTH)
      {
         playerX += -10;
         if (playerX <= 0);                   
         {
            playerMoveAmount = -10;
         }
      }
      else if (key.getKeyChar() == 'd' && playerX + 30 <= SCREEN_WIDTH)
      {
         playerX += 10;
         if (playerX >= SCREEN_WIDTH - 25);  
         {
            playerMoveAmount = 10;
         }
      }
   }
  
   public void keyPressed(KeyEvent e) { }
   public void keyReleased(KeyEvent e) { }
  
   public static void main(String[] args)
   {
      TimerDemo1 prog = new TimerDemo1();
      prog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      prog.setSize(SCREEN_WIDTH, SCREEN_WIDTH);
      prog.setVisible(true);
   }// end of main method

}// end of class
