/**
 * [Level3.java]
 * The information for the third level of the game
 * @author Jeffrey Xu and Avneesh Verma
 * @version 1.0 January 22, 2021
 */

/* Imports */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Level3 extends Level {
  
  /* CONSTRUCTOR */
  public Level3(JFrame gameWindow) {
    
    this.window = gameWindow;
    this.keyListener = new MyKeyListener(this);
    this.canvas = new GraphicsPanel(this);
    
    // Window
    this.canvas.addKeyListener(this.keyListener);
    this.window.add(this.canvas);
    this.canvas.requestFocusInWindow();
    
    this.player1 = new Player(FRAME_WIDTH/4, FRAME_HEIGHT-GROUND_HEIGHT-63, 10, -30);
    
    // Enemy Properties
    this.goombas = new Goomba[] {
      new Goomba(FRAME_WIDTH - 530, FRAME_HEIGHT - GROUND_HEIGHT - 32, 110),
      new Goomba(FRAME_WIDTH - 200, FRAME_HEIGHT - GROUND_HEIGHT - 32, 110),
      new Goomba(FRAME_WIDTH + 180, FRAME_HEIGHT - GROUND_HEIGHT - 32, 110),
      new Goomba(FRAME_WIDTH + 180, FRAME_HEIGHT - GROUND_HEIGHT - 32, 110),
      new Goomba(FRAME_WIDTH + 570, FRAME_HEIGHT - GROUND_HEIGHT - 32, 110),
      new Goomba(2*FRAME_WIDTH - 150, FRAME_HEIGHT - GROUND_HEIGHT - 32, 150),
      new Goomba(5*FRAME_WIDTH + 340, FRAME_HEIGHT - GROUND_HEIGHT - 32, 200),
      new Goomba(6*FRAME_WIDTH + 114, FRAME_HEIGHT - GROUND_HEIGHT - 32, 200)
    };
    
    this.crabs = new Crab[] {
      new Crab(FRAME_WIDTH - 530, FRAME_HEIGHT - GROUND_HEIGHT - 182, 110),
      new Crab(FRAME_WIDTH - 200, FRAME_HEIGHT - GROUND_HEIGHT - 282, 110),
      new Crab(FRAME_WIDTH + 180, FRAME_HEIGHT - GROUND_HEIGHT - 372, 110),
      new Crab(FRAME_WIDTH + 570, FRAME_HEIGHT - GROUND_HEIGHT - 302, 110),
      new Crab(2*FRAME_WIDTH + 260, 268, 110),
      new Crab(4*FRAME_WIDTH - 5, FRAME_HEIGHT - GROUND_HEIGHT - 199, 110),
      new Crab(6*FRAME_WIDTH + 114, FRAME_HEIGHT - GROUND_HEIGHT - 296, 110)
    };

    this.bees = new Bee[] {
      new Bee(500, 475, 60),
      new Bee(580, 460, 90),
      new Bee(3062, FRAME_HEIGHT - GROUND_HEIGHT - 118, 70),
      new Bee(3142, FRAME_HEIGHT - GROUND_HEIGHT - 118, 70),
      new Bee(3222, FRAME_HEIGHT - GROUND_HEIGHT - 118, 70),
      new Bee(4*FRAME_WIDTH + 375, FRAME_HEIGHT - GROUND_HEIGHT - 450, 80),
      new Bee(4*FRAME_WIDTH + 475, 480, 75),
      new Bee(5*FRAME_WIDTH - 100, 465, 80),
      new Bee(5*FRAME_WIDTH + 330, 400, 100)
    };

    this.flyers = new Flyer[] {
      new Flyer(740, 350, 100),
      new Flyer(FRAME_WIDTH + 47, 500, 145),
      new Flyer(FRAME_WIDTH + 50, 525, 270),
      new Flyer(FRAME_WIDTH + 53, 550, 392),
      new Flyer(FRAME_WIDTH + 55, 575, 515),
      new Flyer(FRAME_WIDTH + 58, 600, 630),
      new Flyer(3*FRAME_WIDTH - 295, 350, 110),
      new Flyer(3*FRAME_WIDTH + 760, 212, 100),
      new Flyer(4*FRAME_WIDTH + 400, 550, 500),
      new Flyer(4*FRAME_WIDTH + 700, 550, 500),
      new Flyer(6*FRAME_WIDTH + 40, FRAME_HEIGHT - GROUND_HEIGHT - 132, 280)
    };
    
    this.enemies = new Enemy[][] {goombas, crabs, bees, flyers};
  
    // Platform properties
    this.platforms = new int[][] {
      // End checkpoint/flag
      {7*FRAME_WIDTH - 375, FRAME_HEIGHT - 250, 75, 150},
      //Other platforms
      {2*FRAME_WIDTH - 200, 180, 245, 65},
      {2*FRAME_WIDTH + 140, 300, 245, 65},
      {3*FRAME_WIDTH + 655, FRAME_HEIGHT - GROUND_HEIGHT - 365, 245, 65},
      {4*FRAME_WIDTH + 274, FRAME_HEIGHT - GROUND_HEIGHT - 304, 245, 65},
      {4*FRAME_WIDTH + 635, FRAME_HEIGHT - GROUND_HEIGHT - 200, 245, 65},
      {6*FRAME_WIDTH + 384, FRAME_HEIGHT - GROUND_HEIGHT - 155, 245, 65},
    };

    this.icePlatforms = new int[][] {
      {FRAME_WIDTH - 650, FRAME_HEIGHT - GROUND_HEIGHT - 150, 278, 65},
      {FRAME_WIDTH - 320, FRAME_HEIGHT - GROUND_HEIGHT - 250, 278, 65},
      {FRAME_WIDTH + 60, FRAME_HEIGHT - GROUND_HEIGHT - 340, 278, 65},
      {FRAME_WIDTH + 450, FRAME_HEIGHT - GROUND_HEIGHT - 270, 278, 65},
      {3*FRAME_WIDTH - 410, FRAME_HEIGHT - GROUND_HEIGHT - 247, 278, 65},
      {3*FRAME_WIDTH - 55, FRAME_HEIGHT - GROUND_HEIGHT - 140, 278, 65},
      {3*FRAME_WIDTH + 275, FRAME_HEIGHT - GROUND_HEIGHT - 230, 278, 65},
      {4*FRAME_WIDTH - 125, FRAME_HEIGHT - GROUND_HEIGHT - 167, 278, 65},
      {5*FRAME_WIDTH - 180, FRAME_HEIGHT - GROUND_HEIGHT - 330, 278, 65},
      {6*FRAME_WIDTH - 368 , 236, 278, 65},
      {6*FRAME_WIDTH - 6, 356, 278, 65},
    };
    
    // Wall Properties
    this.walls = new int[][] {
      // Start wall
      {-500, 0, 550, FRAME_HEIGHT - GROUND_HEIGHT + 5},
      // End wall
      {7*FRAME_WIDTH, 0, 1000, FRAME_HEIGHT - GROUND_HEIGHT + 5},
      {5*FRAME_WIDTH, FRAME_HEIGHT - GROUND_HEIGHT - 330, 100, 333},
      {6*FRAME_WIDTH - 370, 236, 100, 387}
    };
    
    this.spikes = new int[17][4];
    // Initalize spikes
    this.spikes[0][0] = 2*FRAME_WIDTH - 425;
    this.spikes[1][0] = 2*FRAME_WIDTH + 30;
    for (int s = 0; s < this.spikes.length; s++) {
      if (s >= 14) {
        this.spikes[s][0] = 4*FRAME_WIDTH + 255 + 350*(s-14);
      }
      else if (s >= 2) {
        this.spikes[s][0] = 3*FRAME_WIDTH - 410 + 125*(s-2);
      }
      this.spikes[s][1] = FRAME_HEIGHT - GROUND_HEIGHT - 30;
      this.spikes[s][2] = 125;
      this.spikes[s][3] = 30;
    }
    this.spikes[13][0] = 3*FRAME_WIDTH + 765;
    this.spikes[13][1] = 225;

    this.water = new Rectangle[] {
      new Rectangle(3000, FRAME_HEIGHT - GROUND_HEIGHT - 192, 320, 192),
      new Rectangle(5*FRAME_WIDTH + 590, FRAME_HEIGHT - GROUND_HEIGHT - 192, 320, 192),
      new Rectangle(5*FRAME_WIDTH + 590, FRAME_HEIGHT - GROUND_HEIGHT - 384, 320, 192),
    };
    
    this.coins = new Rectangle[] {
      new Rectangle(FRAME_WIDTH + 175, 450, 60, 58),
      new Rectangle(2*FRAME_WIDTH - 115, 90, 60, 58),
      new Rectangle(5*FRAME_WIDTH - 115, 525, 60, 58)
    };
  }
}
