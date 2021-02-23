package point.Item;

import point.MyPoint;
import point.player.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Coin extends Item{
    private static final int POINT = 25;
    transient private BufferedImage texture;

    public Coin(MyPoint point){
        super(point.getX(), point.getY());
        try {
            texture = ImageIO.read(getClass().getResource("/Sprites/Coin.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isAvailable() {
        return super.isAvailable();
    }

    @Override
    public void effect(Player player) {
        player.addPoint(POINT);
    }

    @Override
    public void draw(Graphics g) {
        if (available){
            g.drawImage(texture, x, y, getLength(), getLength(), null);
        }
    }

    @Override
    public void pickedUpBy(Player player) {
        effect(player);
        available = false;
    }

    @Override
    public void load() throws IOException{
        texture = ImageIO.read(getClass().getResource("/Sprites/Coin.png"));
    }
}
