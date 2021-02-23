package point.Item;

import point.MyPoint;
import point.player.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Torch extends Item{
    private static final int RAD = 15;
    transient private BufferedImage texture;

    public Torch(MyPoint point){
        super(point.getX(), point.getY());
        try {
            texture = ImageIO.read(getClass().getResource("/Sprites/Torch.png"));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isAvailable() {
        return super.isAvailable();
    }

    @Override
    public void effect(Player player) {
        player.addRad(RAD);
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
        texture = ImageIO.read(getClass().getResource("/Sprites/Torch.png"));
    }
}
