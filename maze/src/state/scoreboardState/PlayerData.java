package state.scoreboardState;

import point.Drawable;
import point.player.Player;
import state.gameState.game.savePlayer;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class PlayerData implements Drawable {
    private ArrayList<Player> data;

    public PlayerData() {
        savePlayer players = new savePlayer();
        try {
            this.data = players.load();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        for (int i = 0; i< Math.min(data.size(), 10); i++) {
            if (data.get(i) != null) {
                g.drawString(data.get(i).getName(), 250, 300 + i * 50);
                g.drawString((String.valueOf(data.get(i).getScore())), 750, 300 + i * 50);
                g.drawString(data.get(i).getMinute() + ":" + data.get(i).getSecond(), 1250, 300 + i * 50);
            }
        }
    }
}