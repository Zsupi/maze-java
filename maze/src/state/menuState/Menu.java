package state.menuState;

import state.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Menu extends GamePanel {
    //components
    private JButton gameButton;
    private JButton scoreBoardButton;
    private JButton exitButton;
    private JButton continueButton;
    private JLabel title;
    //addons
    private final GridBagConstraints gridBag = new GridBagConstraints();
    private final ActionListener actionListener;

    public Menu(ActionListener actionListener) {
        super();
        this.setLayout(new GridBagLayout());
        this.actionListener = actionListener;
    }

    private void buttonSetup(){
        gameButton = new JButton("New Game");
        continueButton = new JButton("Continue");
        scoreBoardButton = new JButton("Scoreboard");
        exitButton = new JButton("Exit");

        gameButton.setFont(font.deriveFont(Font.PLAIN, 30* scale));
        continueButton.setFont(font.deriveFont(Font.PLAIN, 30* scale));
        scoreBoardButton.setFont(font.deriveFont(Font.PLAIN, 30* scale));
        exitButton.setFont(font.deriveFont(Font.PLAIN, 30* scale));

        gameButton.setActionCommand("Game");
        continueButton.setActionCommand("Continue");
        scoreBoardButton.setActionCommand("Scoreboard");
        exitButton.setActionCommand("Exit");

        gameButton.setBackground(Color.BLACK);
        continueButton.setBackground(Color.BLACK);
        scoreBoardButton.setBackground(Color.BLACK);
        exitButton.setBackground(Color.BLACK);

        gameButton.setForeground(Color.white);
        continueButton.setForeground(Color.white);
        scoreBoardButton.setForeground(Color.white);
        exitButton.setForeground(Color.white);

        gameButton.addActionListener(actionListener);
        continueButton.addActionListener(actionListener);
        scoreBoardButton.addActionListener(actionListener);
        exitButton.addActionListener(actionListener);
    }

    private void titleSetup(){
        title = new JLabel("Labyrinth");

        title.setFont(font.deriveFont(Font.PLAIN, 100* scale));
        title.setBackground(Color.white);
        title.setForeground(Color.white);
    }

    public void init(){
        this.setVisible(true);
        buttonSetup();
        titleSetup();

        gridBag.fill = GridBagConstraints.HORIZONTAL;
        gridBag.gridx = 0;
        gridBag.gridy = 0;
        gridBag.anchor = GridBagConstraints.LINE_START;
        gridBag.insets = new Insets(25, 0, 25,0);


        this.add(gameButton, gridBag);
        gridBag.gridy = 1;
        this.add(continueButton, gridBag);
        gridBag.gridy = 2;
        this.add(scoreBoardButton, gridBag);
        gridBag.gridy = 3;
        this.add(exitButton, gridBag);

        gridBag.insets = new Insets(0, (int) (150*scale), 0, 0);
        gridBag.gridx = 1;
        gridBag.gridy = 1;
        gridBag.anchor = GridBagConstraints.CENTER;
        this.add(title, gridBag);
    }
}
