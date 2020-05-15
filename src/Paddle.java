import java.awt.*;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Paddle {
    private int xDirection;
    private int yDirection;
    private int[] pixels;
    private Rectangle boundingBox;
    private int width = 6;
    private int height = 48;

    public Paddle(int x, int y, int col){
        boundingBox = new Rectangle(x, y, width, height);
        pixels = new int[width*height];
        for (int i = 0 ; i < pixels.length ; i++) {
            pixels[i] = col;
            boundingBox.x = 10;
        }
    }

    public void keyPressed(KeyEvent e){
        int p1score = Ball.p1Score;
        int p2score = Ball.p2Score;
        if(e.getKeyChar() == 'w'){
            setYDirection(-3);

            //Combacksystems
            if (p2score - p1score > 2){
                setYDirection(-4);
            }
        }
        if(e.getKeyChar() == 's'){
            setYDirection(3);

            //Combacksystem
            if (p2score - p1score > 2){
                setYDirection(4);
            }
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
        if(boundingBox.y >= 352) {
            boundingBox.y = 352;
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