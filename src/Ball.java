import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Ball {
    private double xDirection, yDirection;
    private int[] pixels;
    private Rectangle boundingBox;
    private int height = 10;
    private int width = 10;
    private int p1Score;
    private int p2Score;

    public Ball(int x, int y) {
        pixels = new int[width * height];
        /*for(int j = 0 ; j < height ; j++ ) {
            for (int i = 0 ; i < width ; i++) {
                if ((i-width/2)*(i-width/2) + (j-height/2)*(j-height/2) < width*width/4) {
                    pixels[i] = 0xFFFFFFFF;
                } else {
                    pixels[i] = 0x00000000;
                }
            }
        }*/

        for (int i = 0; i < pixels.length; i++)
            pixels[i] = 0xFFFFFFFF;

        boundingBox = new Rectangle(x, y, width, height);

        Random r = new Random();
        int rDir = r.nextInt(1);
        if (rDir == 0) {
            rDir--;
        }
        setXDirection(rDir);
        int yrDir = r.nextInt(1);
        if (yrDir == 0) {
            yrDir--;
        }
        setYDirection(yrDir);
    }

    public void setXDirection(double xdir) {
        xDirection = xdir;
    }

    public void setYDirection(double ydir) {
        yDirection = ydir;
    }

    public double getXDirection() {
        return xDirection;
    }

    public double getYDirection() {
        return yDirection;
    }

    public void draw(int[] Screen, int screenWidth) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Screen[(boundingBox.y + i) * screenWidth + boundingBox.x + j] = pixels[i * width + j];
            }
        }
    }

    public void collision(Rectangle r) {
        if (boundingBox.intersects(r)) {
            if (getXDirection() > 0 && Math.abs(r.x - (boundingBox.x + boundingBox.width)) <= getXDirection()) {
                setXDirection(-2);
            } else if (getXDirection() < 0 && Math.abs(r.x + r.width - boundingBox.x) <= -getXDirection()) {
                setXDirection(+2);
            } else if (getYDirection() > 0 && Math.abs(r.y - (boundingBox.y + boundingBox.height)) <= getYDirection()) {
                setYDirection(-2);
            } else if (getYDirection() < 0 && Math.abs(r.y + r.height - boundingBox.y) <= -getYDirection()) {
                setYDirection(+2);
            }
        }
    }

    public void move() {
        boundingBox.x += xDirection;
        boundingBox.y += yDirection;
        System.out.println();
        //Bounce the ball when edge is detected and reset if ball touches right and left wall
        if (boundingBox.x <= 0) {
            boundingBox.x = 250;
            boundingBox.y = 200;
            setXDirection(0);
            setYDirection(0);
            p2Score++;
            JOptionPane.showMessageDialog(null, "PLAYER 1 SCORE:" + p1Score +
                    "\n" + "PLAYER 2 SCORE:" + p2Score);
            System.out.println("p1score" + p1Score);
        }
        if (boundingBox.x >= 492) {
            boundingBox.x = 250;
            boundingBox.y = 200;
            setXDirection(0);
            setYDirection(0);
            p1Score++;
            JOptionPane.showMessageDialog(null, "PLAYER 1 SCORE:" + p1Score +
                    "\n" + "PLAYER 2 SCORE:" + p2Score);
        }
        if (boundingBox.y <= 0) setYDirection(+2);
        if (boundingBox.y >= 390) setYDirection(-2);
    }


    public int getP1Score() {
        return p1Score;
    }

    public int getP2Score() {
        return p2Score;
    }

    public void setP1Score(int p1Score) {
        this.p1Score = p1Score;
    }

    public void setP2Score(int p2Score) {
        this.p2Score = p2Score;
    }

    public void update(Rectangle r) {
        collision(r);
        move();
        collision(r);
    }

    public void keyReleased(KeyEvent keyEvent) {

        int negativY = -2;
        int positivY = +2;
        int negativX = -2;
        int positivX = +2;

        if (keyEvent.getKeyCode() == keyEvent.VK_SPACE) {
            int randomDerX = new Random().nextBoolean() ? negativX : positivX;
                setXDirection(randomDerX);

            int randomDerY = new Random().nextBoolean() ? negativY : positivY;
                setYDirection(randomDerY);
        }
    }
}
