/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author PC
 */
public class BomberMan extends Canvas{
    /** Bufferstrategy makes it easy to visually update what's going on in the game. after updating the game events u call <something>.show and it updates the screen */
	private BufferStrategy strategy;
        private boolean gameRunning = true;
	/** The list of all the entities that exist in the game */
	private ArrayList entities = new ArrayList();
	/** The list of entities that need to be removed from the grid, for resetting after a game over */
	private ArrayList removeList = new ArrayList();
	/** The speed at which the player should move (pixels/sec) */
	private double moveSpeed = 300;
	/** The time at which the last bomb is dropped for one player */
	private long lastBomb = 0;
	/** The minimum interval before each bomb drop for each player (ms) */
	private long bombPlaceInterval = 500;
	/** The number of health left in a player.  */
	private int healthCount;
	
	
	private boolean waitingForKeyPress = true;

	private boolean leftPressed = false;

	private boolean rightPressed = false;
	/** bombPressed becomes true when one player drops a bomb */
	private boolean bombPressed = false;

public BomberMan() {
		// create a frame to contain our game
		JFrame container = new JFrame("Bomber Man");
		
		// get hold the content of the frame and set up the resolution of the game
		JPanel panel = (JPanel) container.getContentPane();
		panel.setPreferredSize(new Dimension(800,600));
		panel.setLayout(null);
	
		// setup our canvas size and put it into the content of the frame
		setBounds(0,0,800,600);
		panel.add(this);
		
		// Tell AWT not to bother repainting our canvas since we're
		// going to do that our self in accelerated mode
		setIgnoreRepaint(true);
		
		// finally make the window visible 
		container.pack();
		container.setResizable(false);
		container.setVisible(true);
		
		// add a listener to respond to the user closing the window. If they
		// do we'd like to exit the game
		container.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		// create the buffering strategy which will allow AWT
		// to manage our accelerated graphics
		createBufferStrategy(2);
		strategy = getBufferStrategy();
		
		
	}
private void startGame() {


		leftPressed = false;
		rightPressed = false;
		bombPressed = false;
	}
private class KeyInputHandler extends KeyAdapter {
		/** The number of key presses after pressing any key to begin the game */
		private int pressCount = 1;
		
		/**
		 * 
		 * a keyPressed checks if a key is pressed down, however a keyTyped method is needed 
		 * 
		 *
		 * @param e The details of the key that was pressed 
		 */
                @Override
		public void keyPressed(KeyEvent e) {
			// if we're waiting for an "any key" typed then we don't 
			// want to do anything with just a "press"
			if (waitingForKeyPress) {
				return;
			}
			
			
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				leftPressed = true;
                                System.out.println("left");
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				rightPressed = true;
                                  System.out.println("right");
			}
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				bombPressed = true;
                                  System.out.println("space");
			}
		} 
                
}
		
		

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      BomberMan g = new BomberMan();

// TODO code application logic here
    }
    
}
