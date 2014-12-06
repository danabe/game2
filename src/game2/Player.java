/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game2;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

/**
 *
 * @author aberg2
 */
public class Player {
    private double x, y, vx, vy, ax, ay;
    private double oldY, oldVy, oldX, oldVx;
    private int health, lives;
    Image currentImage;
    private int tileSizeX = 32;
    private int tileSizeY = 32;
    
    private boolean touchGround;
    
    public void setTouchGround(boolean val){
        touchGround=val;
    }
    
    
    
    public Player(){
        ImageIcon img = new ImageIcon("myTiles/04_player1.png");
        currentImage = img.getImage();
        x  = 100 ;
        y  = 180 ;
        vx = 0;
        vy = 0;
        ax = 0;
        ay = 0.02;
        health = 10;
        lives  = 3;
    }
    
    
    public int getTileSizeX() {
        return tileSizeX;
    }
    
    public int getTileSizeY() {
        return tileSizeY;
    }
    
    public double getX(){
        return( x );
    }
    public double getY(){
        return( y );
    }
    public double getVx(){
        return( vx ) ;
    }
    public double getVy(){
        return( vy );
    }
     public double getAx(){
        return( ax );
    }
    public double getAy(){
        return( ay );
    }
    
    public double getOldX(){
        return( oldX );
    }
    public double getOldY(){
        return( oldY );
    }
    
    public Image getImage(){
        return currentImage;
    }
    
    public void setX(double val){
        x=val;
    }
    public void setY(double val){
        y=val;
    }
    public void setVx(double val){
       vx=val;
    }
    public void setVy(double val){
        vy=val;
    }
    public void setAx(double val){
        ax=val;
    }
    public void setAy(double val){
        ay=val;
    }
    public void setOldX(double val){
        oldX=val;
    }
    public void setOldY(double val){
        oldY=val;
    }
    
    
    
    public Rectangle getBounds() {
        return new Rectangle( (int)x, (int)y, tileSizeX, tileSizeY);
    }
    
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            vx=-1;
        }
        if (key == KeyEvent.VK_RIGHT) {
            vx=1;
        }
        if (key == KeyEvent.VK_DOWN) {
            //vy = 1;
        }
        if (key == KeyEvent.VK_UP && touchGround) {
            vy=-2.2;
            //vy=-2;
            //vy=-1; 
            
        }
    }
    
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            vx=0;
        }
        if (key == KeyEvent.VK_RIGHT) {
            vx=0;
        }
        if (key == KeyEvent.VK_DOWN) {
            //vy=1;
        }
        if (key == KeyEvent.VK_UP) {
            //vy=1;
          
        }
    }
    
    public void moveHorizontally() {
        oldX=x;
        vx = vx + ax;
        x = x + vx;
    }
    
     public void moveVertically() {
        oldY=y;
        vy = vy + ay;
         y =  y + vy;
    }
    
    public void moveBack(){
      //  y=oldY;
        
        //vy=0;
        //x=oldX;
        //vx=0;
        //ay=0;
    }

    
    
    
    
}
