package state.gameState.nameInput;

import main.GameFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NameSubmitListener implements ActionListener {
    private final GameFrame frame;
    private JTextField t;

    public void setT(JTextField t) {
        this.t = t;
    }

    public NameSubmitListener(GameFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Submit")){
            frame.gameState(t.getText());
        }
    }
}