package state.gameState.game;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {
    private boolean UP;
    private boolean DOWN;
    private boolean LEFT;
    private boolean RIGHT;
    private boolean ESC;

    public InputHandler(JPanel panel){
        panel.addKeyListener(this);
        UP = false;
        DOWN = false;
        LEFT = false;
        RIGHT = false;
        ESC = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keyHandler(e.getKeyCode(), true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keyHandler(e.getKeyCode(), false);
    }

    private void keyHandler(int keyCode, boolean pressed){
        if (keyCode == KeyEvent.VK_W)
            UP = pressed;
        if (keyCode == KeyEvent.VK_S)
            DOWN = pressed;
        if (keyCode == KeyEvent.VK_D)
            RIGHT = pressed;
        if (keyCode == KeyEvent.VK_A)
            LEFT = pressed;
        if (keyCode == KeyEvent.VK_ESCAPE)
            ESC = pressed;
    }

    public boolean isUP() {
        return UP;
    }

    public boolean isDOWN() {
        return DOWN;
    }

    public boolean isLEFT() {
        return LEFT;
    }

    public boolean isRIGHT() {
        return RIGHT;
    }

    public boolean isESC() {
        return ESC;
    }

    public void setESC(boolean ESC) {
        this.ESC = ESC;
    }
}
