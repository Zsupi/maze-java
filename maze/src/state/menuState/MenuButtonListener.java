package state.menuState;

import main.GameFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MenuButtonListener implements ActionListener {
    private final GameFrame frame;
    private final File file = new File("Resources/data/lastGame.txt");

    public MenuButtonListener(GameFrame frame) {
        this.frame = frame;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case("Game")->frame.nameState();
            case("Continue") -> {
                if (file.length() != 0)
                    frame.resumeGameState();
            }
            case("Scoreboard")->frame.scoreState();
            case("Exit")->System.exit(0);
            default -> throw new IllegalStateException("Unexpected value: " + e.getActionCommand());
        }
    }
}