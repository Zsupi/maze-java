package state.gameState.nameInput;

import state.GamePanel;

import javax.swing.*;
import java.awt.*;

//itt valami miatt bent hagyja az előzőleg megadott nevet, ha 2x egymás után indítjuk el,
//nem tudom miatt mert uj textfieldet hozok létre

public class NameInput extends GamePanel {
    private JLabel nameOutput;
    private JTextField nameInput;
    private JButton submitButton;
    private JLabel backgroundLabel;

    private final NameSubmitListener actionListener;

    public NameInput(NameSubmitListener actionListener){
        super();
        this.actionListener = actionListener;
    }

    private void placing(){
        float preferredWidth =  nameOutput.getPreferredSize().width +
                                nameInput.getPreferredSize().width +
                                submitButton.getPreferredSize().width;
        int x = (int) Math.ceil((this.getPreferredSize().width - preferredWidth)/2);
        int y = this.getPreferredSize().height/2;

        nameOutput.setBounds(x, y, nameOutput.getPreferredSize().width, nameOutput.getPreferredSize().height);
        x += nameOutput.getPreferredSize().width+10;
        nameInput.setBounds(x, y, nameInput.getPreferredSize().width, nameInput.getPreferredSize().height);
        x += nameInput.getPreferredSize().width+10;
        submitButton.setBounds(x, y, submitButton.getPreferredSize().width, submitButton.getPreferredSize().height);
    }

    public void init(){
        this.setVisible(true);
        backgroundLabel = new JLabel();
        backgroundLabel.setIcon(bgImage);
        backgroundLabel.setBounds(0, 0, this.getPreferredSize().width, this.getPreferredSize().height);

        nameOutput = new JLabel("Name:");
        nameOutput.setFont(font.deriveFont(Font.PLAIN, 30* scale));
        nameOutput.setBackground(Color.BLACK);
        nameOutput.setForeground(Color.white);

        nameInput = new JTextField(20);
        nameInput.setFont(font.deriveFont(Font.PLAIN, 30* scale));
        nameInput.setBackground(Color.BLACK);
        nameInput.setForeground(Color.white);
        actionListener.setT(nameInput);

        submitButton = new JButton("Submit");
        submitButton.setFont(font.deriveFont(Font.PLAIN, 30* scale));
        submitButton.setBackground(Color.BLACK);
        submitButton.setForeground(Color.white);
        submitButton.setActionCommand("Submit");
        submitButton.addActionListener(actionListener);

        placing();

        this.add(nameOutput);
        this.add(nameInput);
        this.add(submitButton);
        this.add(backgroundLabel);

    }

}
