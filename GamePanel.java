/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author Matt
 */
public class GamePanel extends JPanel implements Runnable {

    public static int width;
    public static int height;
    /**threads are used to make several different calculations at the same time,
    *to speed up the game on a slow computer
    * 
    **/
    private Thread thread;
    private boolean running = false;
private BufferedImage img;
private Graphics2D g;

//just in case we figure out how to make the game resizable
    public GamePanel(int width, int height) {
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        requestFocus();
    }


     public void init() {
   running = true;
   img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
   g = (Graphics2D) img.getGraphics();
    }
    

    public void run() {
        init();
        
        final double UPDATE_SPEED = 60.0;
        //Time before update
        final double BEFORE_UPDATE =  1000000000/UPDATE_SPEED;
        final double TARGET_FPS = 60;
        //Total time before render
        final double TTBR = 1000000000/TARGET_FPS;
        //Must update before render
        final int MUBR = 5;
        //Time of last update
        double lastUpdateTime = System.nanoTime();
        
        double lastRenderTime;
        
        int oldFrameCount = 0;
        int frameCount = 0;
        int lastTimeSeconds;
        
        
        
        while (running) {
            
            double now = System.nanoTime();
            int updateCount = 0;
            while(((now - lastUpdateTime) > BEFORE_UPDATE) && (updateCount < MUBR)) {
                update();
               // input();
                lastUpdateTime += BEFORE_UPDATE;
                updateCount ++;
            }
            //
            if(now - lastUpdateTime > BEFORE_UPDATE) {
                lastUpdateTime = now - BEFORE_UPDATE;
            }
          
            render();
            draw();
            lastRenderTime = now;
            frameCount++;
            
            int thisSecond = (int) (lastUpdateTime /  1000000000);
            if(thisSecond > lastUpdateTime) {
                if (frameCount != oldFrameCount) {
                    System.out.println("New time" + thisSecond + " " + frameCount);
                    oldFrameCount = frameCount;
                }
                frameCount = 0;
                lastTimeSeconds= thisSecond;
            }
            

        }
    }
   
    private final int x = 0;
    
    public void update() {
        
    }
    
    public void render(){
        g.setColor(new Color(66, 134, 244));
        g.fillRect(0,0,width,height);
    }
    public void draw() {
        Graphics g = (Graphics) this.getGraphics();
        g.drawImage(img, 0,0,width,height,null);
        g.dispose();
        
    }
}
