package state.scoreboardState;

import state.GamePanel;

import javax.swing.*;
import java.awt.*;

public class ScoreBoard extends GamePanel {
    private PlayerData data;

    private BackButtonListener buttonListener;
    private JButton backButton;

    public ScoreBoard(BackButtonListener buttonListener){
        super();

        this.buttonListener = buttonListener;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //title
        g.setColor(Color.WHITE);
        g.setFont(font.deriveFont(Font.PLAIN, 40*scale));
        g.drawString("Labyrinth", screenSize.width/2-120, 150);
        //data
        g.setFont(font.deriveFont(Font.PLAIN, 20*scale));
        data.draw(g);

    }

    public void init(){
        this.setVisible(true);

        data = new PlayerData();

        backButton = new JButton("Back");
        backButton.setBackground(Color.BLACK);
        backButton.addActionListener(buttonListener);
        backButton.setFont(font.deriveFont(Font.PLAIN, 25*scale));
        backButton.setActionCommand("Back");
        backButton.setPreferredSize(new Dimension(200, 60));
        backButton.setBounds(screenSize.width/2- backButton.getPreferredSize().width/2, 800, backButton.getPreferredSize().width, backButton.getPreferredSize().height);

        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setIcon(bgImage);
        backgroundLabel.setBounds(0, 0, screenSize.width, screenSize.height);

        this.add(backButton);
        this.add(backgroundLabel);
    }


}
