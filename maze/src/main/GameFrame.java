package main;

import state.gameState.game.Game;
import state.gameState.nameInput.NameInput;
import state.gameState.nameInput.NameSubmitListener;
import state.menuState.Menu;
import state.menuState.MenuButtonListener;
import state.scoreboardState.BackButtonListener;
import state.scoreboardState.ScoreBoard;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GameFrame extends JFrame {
    private final ImageIcon icon = new ImageIcon("Resources/Icon/Labirintus-3d.png");
    private final ActionListener menuListener = new MenuButtonListener(this);
    //private final NameSubmitListener nameListener = new NameSubmitListener(this);
    private final BackButtonListener backListener = new BackButtonListener(this);

    private final Menu menu = new Menu(menuListener);
    private final Game game  = new Game(this);
    //private final NameInput name = new NameInput(nameListener);
    private final ScoreBoard score = new ScoreBoard(backListener);


    public GameFrame() {
        super("Maze");
        this.setResizable(true);
        this.setUndecorated(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(icon.getImage());
        this.setVisible(true);
    }

    public void menuState(){
        menu.init();
        this.setContentPane(menu);
        this.pack();
    }

    public void gameState(String userName){
        game.NewGame(userName);
        this.setContentPane(game);
        game.grabFocus();
        this.pack();
    }

    public void resumeGameState(){
        try {
            game.load();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.setContentPane(game);
        game.grabFocus();
        this.pack();
    }

    public void nameState(){
        NameSubmitListener nameListener = new NameSubmitListener(this);
        NameInput name = new NameInput(nameListener);
        name.init();
        this.setContentPane(name);
        this.pack();
    }

    public void scoreState(){
        score.init();
        this.setContentPane(score);
        this.pack();
    }
}