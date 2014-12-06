/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game2;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author aberg2
 */
@SuppressWarnings("serial")
public class Pane extends JPanel implements ActionListener {

    private final int tileSizeX = 32;
    private int tileSizeY = 32;
    private int paneSizeX = 30;
    private int paneSizeY = 15;
    private Level currentLevel;
    private Timer time;
    private Player p1;
    private boolean weDidIt;

    public Pane() {
        p1 = new Player();
        addKeyListener(new AL());
        setFocusable(true);
        time = new Timer(5, this);
        time.start();
    }

    public void setCurrentLevel(Level xlevel) {
        currentLevel = xlevel;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        Image img = Toolkit.getDefaultToolkit().getImage("myTiles/tile_1x3.png");

        for (int ix = 0; ix < 70; ix++) {
            for (int jx = 0; jx < 15; jx++) {
                //drawTile(img, g2d, ix*levels.tW, jx*levels.tW, 0,levels.level[ix][jx]);
                drawTile(img, g2d, ix * tileSizeX, jx * tileSizeY, 0, currentLevel.getMap(ix, jx));
            }
        }
        g2d.drawImage(p1.getImage(), (int) p1.getX(), (int) p1.getY(), null);
    }

    protected void drawTile(Image img, Graphics g, int x, int y, int mx, int my) {

        g.drawImage(img, x, y, x + tileSizeX, y + tileSizeY,
                mx * tileSizeX, my * tileSizeY, (mx + 1) * tileSizeX,
                (my + 1) * tileSizeY, this);
    }

    public int getTileSizeX() {
        return tileSizeX;
    }

    public int getTileSizeY() {
        return tileSizeX;
    }

    public int getPaneSizeX() {
        return paneSizeX;
    }

    public int getPaneSizeY() {
        return paneSizeY;
    }

    private void checkCollision(boolean isHorizontal) {

        myRectangle playerRect = new myRectangle(
                p1.getX(), p1.getY(), (double) p1.getTileSizeX(), (double) p1.getTileSizeY());

        weDidIt = false;

        for (int ix = 0; ix < paneSizeX; ix++) {
            for (int jx = 0; jx < paneSizeY; jx++) {
                if (currentLevel.getMap(ix, jx) > 0) {

                    myRectangle rect = new myRectangle(
                            (double) (ix * tileSizeX), (double) (jx * tileSizeY),
                            (double) (tileSizeX), (double) (tileSizeY));

                    //System.out.println("xxxx " + rect.getX0());
                    //System.out.println("yyyy " + rect.getY0());
                    if (intersectRectangle(playerRect, rect)) {
                        //System.out.println("aaaaa! "+ix+" "+jx);
                        resolveCollision(ix, jx, p1, isHorizontal);
                        weDidIt = true;
                    }

                }
            }
        }

        if (!weDidIt && !isHorizontal) {

            p1.setTouchGround(false);

        }
    }

    public void resolveCollision(int ix, int jx, Player player, boolean isHorizontal) {
        // First resolve in y
        // move upward until not touching

        Rectangle tileRec = new Rectangle(ix * tileSizeX, jx * tileSizeY, tileSizeX, tileSizeY);
        // Rectangle playerRec = p1.getBounds();  
        double tileRectangleMaxY = tileRec.getMaxY();
        double tileRectangleMinY = tileRec.getMinY();
        double tileRectangleMaxX = tileRec.getMaxX();
        double tileRectangleMinX = tileRec.getMinX();

        double playerNewX = player.getX();
        double playerNewY = player.getY();
        double playerOldX = player.getOldX();
        double playerOldY = player.getOldY();

        double playerWidth = player.getTileSizeX();
        double playerHeight = player.getTileSizeY();

        //System.out.println(pX + " " + pXOld);
        //System.out.println( (pX+pWX) + " " + (pXOld+pWX));
        //System.out.println( tileRectangleMinX);
        // if player entered tile from left -> move left..
        if (isHorizontal) {
            //System.out.println("I'm trying!");

            if (playerNewX + playerWidth >= tileRectangleMinX && playerOldX + playerWidth <= tileRectangleMinX) {
                player.setX(tileRectangleMinX - playerWidth - 1);
                player.setVx(0.0f);

            } else if (playerNewX <= tileRectangleMaxX && playerOldX >= tileRectangleMaxX) {
                player.setX(tileRectangleMaxX + 1);
                player.setVx(0.0f);

            }
        } else {

            // if player entered tile from top -> move up..
            if (playerNewY + playerHeight >= tileRectangleMinY && playerOldY + playerHeight <= tileRectangleMinY) {
                player.setY(tileRectangleMinY - playerHeight);

                player.setVy(0.0f);
                player.setTouchGround(true);
            }

        }

    }

    public Rectangle getBounds(int ix, int jx) {
        return new Rectangle(ix * tileSizeX, jx * tileSizeY, tileSizeX, tileSizeY);
    }

    public void actionPerformed(ActionEvent e) {

        p1.moveHorizontally();
        checkCollision(true);
        p1.moveVertically();
        checkCollision(false);

        repaint();

    }

    private class AL extends KeyAdapter {

        public void keyReleased(KeyEvent e) {
            p1.keyReleased(e);
        }

        public void keyPressed(KeyEvent e) {
            p1.keyPressed(e);
            //System.out.println(X);
        }

    }

    public boolean intersectRectangle(myRectangle r1, myRectangle r2) {

        double r1X0 = r1.getX0();
        double r1X1 = r1.getX1();
        double r1Y0 = r1.getY0();
        double r1Y1 = r1.getY1();

        double r2X0 = r2.getX0();
        double r2X1 = r2.getX1();
        double r2Y0 = r2.getY0();
        double r2Y1 = r2.getY1();

        //if(X1+W1 > X2 &&  X1+W1<X2+W2  && Y1+H1 > Y2 && Y1+H1 < Y2+H2  ) {
        if (r1Y1 > r2Y0 && r1Y0 <= r2Y1 && r1X1 > r2X0 && r1X0 <= r2X1) {
         //System.out.println(X1+" " +(X1+W1) + " " + X2 + " " + (X2 +W2) );
            //System.out.println(Y1+" "+ (Y1+H1) + " " + Y2 + " " + (Y2+H2));
            //System.out.println("Hitme!");
            return true;
        } else {
            return false;
        }

    }
}
