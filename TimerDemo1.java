import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TimerDemo1 extends JFrame implements KeyListener
{ 
  private Timer enemyTimer;                      // propels top enemy horizontally across screen
  private Timer enemy2Timer;                     // propels top enemy2 horizontally across screen
  private Timer enemy3Timer;                     // propels top enemy3 horizontally across screen
  private Timer enemy4Timer;                     // propels top enemy4 horizontally across screen
  private Timer enemy5Timer;                     // propels top enemy5 horizontally across screen
  private Timer playerTimer;                     // propels bottom player horizontally across screen
  private Timer timerBullet;                     // propels bottom player's bullet vertically up the screen
  private int playerX;                           // the x Location of player
  private int enemyX;                            // the x location of enemy
  private int enemy2X;                           // the x location of enemy2
  private int enemy3X;                           // the x location of enemy3
  private int enemy4X;                           // the x location of enemy4
  private int enemy5X;                           // the x location of enemy5
  private int bulletY;                           // the y location of player's bullet
  private int bulletX;                           // the x location of player's bullet
  private int enemyMoveAmount;                   // the enemy's horizontal move amount in each timer step
  private int enemy2MoveAmount;                  // the enemy2's horizontal move amount in each timer step
  private int enemy3MoveAmount;                  // the enemy3's horizontal move amount in each timer step
  private int enemy4MoveAmount;                  // the enemy4's horizontal move amount in each timer step
  private int enemy5MoveAmount;                  // the enemy5's horizontal move amount in each timer step
  private int bulletMoveAmount;                  // the bullet's vertical move amount in each timer step
  private final static int SCREEN_WIDTH = 400;   // width of screen
  
   public TimerDemo1()                           // default constructor
   {
      playerX = 200;                             // initial horizontal position of player                
      enemyX = 100;                              // initial horizontal position of enemy
      enemy2X = 150;                             // initial horizontal position of enemy2
      enemy3X = 200;                             // initial horizontal position of enemy3
      enemy4X = 250;                             // initial horizontal position of enemy4
      enemy5X = 300;                             // initial horizontal position of enemy5
     
      enemyMoveAmount = 10;                      // move amount of enemy
      enemy2MoveAmount = -20;                    // move amount of enemy
      enemy3MoveAmount = -15;                    // move amount of enemy
      enemy4MoveAmount = 30;                     // move amount of enemy
      enemy5MoveAmount = 25;                     // move amount of enemy
      bulletMoveAmount = 10;                     // move amount of enemy
     
      bulletX = -10;                             // initially placing bullet off the screen so its not visible
      bulletY = 180;
      
      addKeyListener(this);                      // necessary to make the KeyListener work correctly
     
      // enemy moving back and forth horizontally
      enemyTimer = new Timer(200, new ActionListener()
      {

         public void actionPerformed(ActionEvent evt)
         {

            if (enemyX <= 0)                      // left boundary  detection for enemy
            {
               enemyMoveAmount = 10;
            }
            if (enemyX >= SCREEN_WIDTH - 25)      // right boundary detection for enemy
            {
               enemyMoveAmount = -10;
            }
             
            enemyX += enemyMoveAmount;            // moving enemy horizontally across screen
            repaint();
         }

      });  
      
      // enemy 2 moving back and forth horizontally
      enemy2Timer = new Timer(200, new ActionListener()
      {

         public void actionPerformed(ActionEvent evt)
         {

            if (enemy2X <= 0)                     // left boundary  detection for enemy2
            {
               enemy2MoveAmount =  20;
            }
            if (enemy2X >= SCREEN_WIDTH - 25)     // right boundary detection for enemy2
            {
               enemy2MoveAmount = -20;
            }
             
            enemy2X += enemy2MoveAmount;          // moving enemy2 horizontally across screen
            repaint();
         }

      });  
      // enemy 3 moving back and forth horizontally
      enemy3Timer = new Timer(200, new ActionListener()
      {

         public void actionPerformed(ActionEvent evt)
         {

            if (enemy3X <= 0)                     // left boundary  detection for enemy3
            {
               enemy3MoveAmount = 15;
            }
            if (enemy3X >= SCREEN_WIDTH - 25)     // right boundary detection for enemy3
            {
               enemy3MoveAmount = -15;
            }
             
            enemy3X += enemy3MoveAmount;          // moving enemy3 horizontally across screen
            repaint();
         }
      });    
      enemy4Timer = new Timer(200, new ActionListener()
      {

         public void actionPerformed(ActionEvent evt)
         {

            if (enemy4X <= 0)                      // left boundary detection for enemy4
            {
               enemy4MoveAmount = 30;
            }
            if (enemy4X >= SCREEN_WIDTH - 25)      // right boundary detection for enemy4
            {
               enemy4MoveAmount = -30;
            }
             
            enemy4X += enemy4MoveAmount;           // moving enemy4 horizontally across screen
            repaint();
         }
       });  
       enemy5Timer = new Timer(200, new ActionListener()
      {

         public void actionPerformed(ActionEvent evt)
         {

            if (enemy5X <= 0)                 // left boundary  detection for enemy5
            {
               enemy5MoveAmount = 25;
            }
            if (enemy5X >= SCREEN_WIDTH - 25) // right boundary detection for enemy5
            {
               enemy5MoveAmount = -25;
            }
             
            enemy5X += enemy5MoveAmount;       // moving enemy5 horizontally across screen
            repaint();
         }
       });
            
      // player moving back and forth horizontally
       
       
      // bullet moving up the screen
      timerBullet = new Timer(50, new ActionListener()
      {

         public void actionPerformed(ActionEvent evt)
         {            
            bulletY -= bulletMoveAmount;        // moving bullet vertically up the screen
           
            // detecting collision with enemy
            if (bulletX >= enemyX && bulletX <= enemyX + 10        // within the width of the enemy
                           && bulletY <= 10 && bulletY >= 0)       // within the vertical span of the enemy
            {
               enemyX = -1000;                                     // moves enemy to -1000
               timerBullet.stop();                                 // stops the bullet timer  
               enemyTimer.stop();                                  // stops the enemy timer
            }
            if (bulletX >= enemy2X && bulletX <= enemy2X + 10      // within the width of the enemy
                           && bulletY <= 10 && bulletY >= 0)       // within the vertical span of the enemy
            {
               enemy2X = -1000;                                    // moves enemy2 to -1000
               timerBullet.stop();                                 // stops the bullet timer  
               enemy2Timer.stop();                                 // stops the enemy2 timer
            }  
            if (bulletX >= enemy3X && bulletX <= enemy3X + 10      // within the width of the enemy
                           && bulletY <= 10 && bulletY >= 0)       // within the vertical span of the enemy
            {
               enemy3X = -1000;                                    // moves enemy3 to -1000
               timerBullet.stop();                                 // stops the bullet timer
               enemy3Timer.stop();                                 // stops the enemy3 timer
            }
            if (bulletX >= enemy4X && bulletX <= enemy4X + 10      // within the width of the enemy
                           && bulletY <= 10 && bulletY >= 0)       // within the vertical span of the enemy
            {
               enemy4X = -1000;                                    // moves enemy4 to -1000
               timerBullet.stop();                                 // stops the bullet timer  
               enemy4Timer.stop();                                 // stops the enemy4 timer
            }
            if (bulletX >= enemy5X && bulletX <= enemy5X + 10      // within the width of the enemy
                           && bulletY <= 10 && bulletY >= 0)       // within the vertical span of the enemy
            {
               enemy5X = -1000;                                    // moves enemy5 to -1000
               timerBullet.stop();                                 // stops the bullet timer  
               enemy5Timer.stop();                                 // stops the enemy5 timer
            }
            if (bulletY <= 0)                                      // bullet boundary detection at top of screen
            {
               timerBullet.stop();
            }

           
            repaint();
         }

      });  
           
      enemyTimer.start();
      enemy2Timer.start();
      enemy3Timer.start();
      enemy4Timer.start();
      enemy5Timer.start();
   }
/////////////////////////////////////////////////////////////////////
   public void paint(Graphics g)
   {
      requestFocus();
      g.setColor(Color.gray);
      g.fillRect(0, 0, SCREEN_WIDTH, SCREEN_WIDTH);            // repaint background
     
      g.setColor(Color.blue);                                  // repaint enemy at top of screen
      g.fillRect(enemyX, 20, 20, 20);
          
      g.setColor(Color.red);                                   // repaint enemy2 at top of screen
      g.fillRect(enemy2X, 20, 20, 20);    
      
      g.setColor(Color.black);                                 // repaint enemy2 at top of screen
      g.fillRect(enemy3X, 20, 20, 20);
          
      g.setColor(Color.pink);                                  // repaint enemy at top of screen
      g.fillRect(enemy4X, 20, 20, 20);  
      
      g.setColor(Color.cyan);                                  // repaint enemy at top of screen
      g.fillRect(enemy5X, 20, 20, 20);  
          
      g.setColor(Color.green);                                 // repaint player at bottom of screen  
      g.fillRect(playerX, 200, 20, 20);
                                      
      g.drawLine(bulletX, bulletY, bulletX, bulletY - 10);     // repaint bullet
          
      g.setColor(Color.orange);                                // update status
      g.drawString("press spacebar to fire bullet", 10, 280);
      g.drawString("press a to move left / d to move right", 10, 310);
      
      if (enemyX == -1000 && enemy2X == -1000 && enemy3X == -1000 && enemy4X == -1000 && enemy5X == -1000)
         {
            g.setColor(Color.orange);
            g.drawString("You win!", 180, 180);
         }
   }
 /////////////////////////////////////////////////////////////////////////////////////
   public void keyTyped(KeyEvent key)
   {
      // space bar shoots the bullet
      if (key.getKeyChar() == ' ' && !timerBullet.isRunning())
      {
         bulletX = playerX;         // line up bullet horizontally with current position of player
         bulletY = 180;      
         timerBullet.start();
      }   
      if (key.getKeyChar() == 'a' && playerX > 0)
      {
         playerX -= 10;
      }
      if (key.getKeyChar() == 'd' && playerX + 30 <= SCREEN_WIDTH)
      {
         playerX += 10;
      }
   }
  
   public void keyPressed(KeyEvent e) { }
    
    
   public void keyReleased(KeyEvent e) { }
  
  
  /////////////////////////////////////////////////////////////////
   public static void main(String[] args)
   {
      TimerDemo1 prog = new TimerDemo1();
      prog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      prog.setSize(SCREEN_WIDTH, SCREEN_WIDTH);
      prog.setVisible(true);
   }// end of main method

}// end of class
