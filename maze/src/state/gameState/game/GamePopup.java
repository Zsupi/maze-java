package state.gameState.game;

import state.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GamePopup extends GamePanel {

    private final Game panel;

    private JButton resume;
    private JButton saveAndExit;
    //private JLabel title;

    public GamePopup(Game panel){
        super();
        this.panel = panel;

        resume = new JButton("Resume");
        saveAndExit = new JButton("SaveAndExit");
    }

    public void popup(JFrame frame){
        this.setVisible(true);
        frame.setContentPane(this);
        frame.pack();
        panel.stopped();
    }

    public void init() {
        /*title = new JLabel("Pause");
        title.setText("Pause");
        title.setForeground(Color.WHITE);
        title.setBackground(Color.white);
        title.setFont(font.deriveFont(Font.PLAIN, 50));
        title.setLocation(screenSize.width/2-title.getPreferredSize().width/2, 500);*/

        resume.setBackground(Color.BLACK);
        resume.setFocusPainted(true);
        resume.setForeground(Color.white);
        resume.setActionCommand("Resume");
        resume.setFont(font.deriveFont(Font.PLAIN, 50));
        resume.addActionListener(e -> {
            panel.resume();
            this.setVisible(false);
        });
        resume.setPreferredSize(new Dimension(300, 60));
        resume.setBounds(screenSize.width/2-resume.getPreferredSize().width/2,
                screenSize.height/2,
                resume.getPreferredSize().width, resume.getPreferredSize().height);

        saveAndExit.setBackground(Color.BLACK);
        saveAndExit.setFocusPainted(true);
        saveAndExit.setForeground(Color.white);
        saveAndExit.setActionCommand("SaveAndExit");
        saveAndExit.setFont(font.deriveFont(Font.PLAIN, 50));
        saveAndExit.addActionListener(e -> {
            try {
                panel.save();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        saveAndExit.setPreferredSize(new Dimension(300, 60));
        saveAndExit.setBounds(screenSize.width/2-saveAndExit.getPreferredSize().width/2,
                screenSize.height/2+2*resume.getPreferredSize().height,
                saveAndExit.getPreferredSize().width, saveAndExit.getPreferredSize().height);

        //this.add(title);
        this.add(resume);
        this.add(saveAndExit);
    }
}
