package point.block;

import point.Drawable;
import point.MyPoint;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Block extends MyPoint implements Drawable {
    private boolean path;
    transient private BufferedImage wallImage;

    //constructors
    public Block(){
        super();
        path = false;
    }

    public Block(int x, int y, BufferedImage wallImage){
        super(x, y);
        path = false;
        this.wallImage = wallImage;
    }

    //get methods
    public boolean isPath() {
        return path;
    }

    //set methods
    public void setPath(boolean path) {
        this.path = path;
    }

    //draw the wall
    @Override
    public void draw(Graphics g){
        if (!path) {
            g.drawImage(wallImage, x,  y,  (length),  (length), null);
        }
    }

    public void load(BufferedImage wallImage) {
        this.wallImage = wallImage;
    }
}
