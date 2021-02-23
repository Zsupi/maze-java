package state.gameState.game;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.Serializable;

public class StopWatch implements Serializable {
    private final Timer timer;

    private int elapsedTime = 0;
    private int minute = 0;
    private int second = 0;

    public StopWatch(int minute, int second){
        elapsedTime = 60000*minute + second*1000;
        ActionListener timeListener = e -> {
            elapsedTime += 1000;
            this.minute = (elapsedTime / 60000);
            this.second = (elapsedTime / 1000) % 60;
        };
        timer = new Timer(1000, timeListener);
    }

    public void start(){
        timer.start();
    }

    public void stop(){
        timer.stop();
    }

    public void restart(){
        elapsedTime = 0;
        minute = 0;
        second = 0;
        timer.restart();
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }
}
