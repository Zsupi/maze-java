package state.scoreboardState;

import main.GameFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BackButtonListener implements ActionListener {
    GameFrame frame;
    public BackButtonListener(GameFrame frame){
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Back")){
            frame.menuState();
        }
    }
}
