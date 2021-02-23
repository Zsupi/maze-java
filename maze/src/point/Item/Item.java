package point.Item;

import point.Drawable;
import point.MyPoint;
import point.player.Player;

import java.awt.*;
import java.io.IOException;

public abstract class Item extends MyPoint implements Drawable {
    protected boolean available;

    public Item(int x, int y){
        super(x, y);
        available = true;
    }

    public boolean isAvailable() {
        return available;
    }

    public static int getLength(){
        return length/2;
    }

    public abstract void effect(Player player);

    @Override
    public abstract void draw(Graphics g);

    public abstract void pickedUpBy(Player player);

    public abstract void load() throws IOException;
}
