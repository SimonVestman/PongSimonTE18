import java.awt.*;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Paddle {
    private int xDirection;
    private int yDirection;
    private int[] pixels;
    private Rectangle boundingBox;
    private int width = 8;
    private int height = 65;

    public Paddle(int x, int y, int col){
        boundingBox = new Rectangle(x, y, width, height);
        pixels = new int[width*height];
        for (int i = 0 ; i < pixels.length ; i++) {
            pixels[i] = col;
            boundingBox.x = 10;
        }
    }

    public void keyPressed(KeyEvent e){
        if(e.getKeyChar() == 'w'){
            setYDirection(-3);
        }
        if(e.getKeyChar() == 's'){
            setYDirection(3);
        }
    }

    public void keyReleased(KeyEvent e){
        if(e.getKeyChar() == 's'){
            setYDirection(0);
        }
        if(e.getKeyChar() == 'w'){
            setYDirection(0);
        }

    }

    public void setXDirection(int xdir) {
        xDirection = xdir;
    }

    public void setYDirection(int ydir){
        yDirection = ydir;
    }

    public Rectangle getBoundingBox() {
        return boundingBox;
    }

    public void update(){
        boundingBox.x += xDirection;
        if(boundingBox.x <= 0) {
            boundingBox.x = 0;
        }
        if(boundingBox.x >= 380) {
            boundingBox.x = 380;
        }
        boundingBox.y += yDirection;
        if(boundingBox.y <= 0) {
            boundingBox.y = 0;
        }
        if(boundingBox.y >= 335) {
            boundingBox.y = 335;
        }
    }

    public void draw(int[] Screen, int screenWidth){
        for (int i = 0 ; i < height ; i++) {
            for (int j = 0 ; j < width ; j++) {
                Screen[(boundingBox.y+i)*screenWidth + boundingBox.x+j] = pixels[i*width+j];
            }
        }
    }
}