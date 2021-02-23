package state.gameState.game;

import main.GameFrame;
import maze.Direction;
import maze.Maze;
import point.Item.Coin;
import point.Item.Item;
import point.Item.Torch;
import point.MyPoint;
import point.block.Block;
import point.player.Player;
import state.GamePanel;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class Game extends GamePanel implements  Runnable {
    //frame
    private final GameFrame frame;
    //components
    private Maze maze;
    private Player player;
    private ArrayList<Item> items;
    //addons
    private StopWatch stopWatch;
    private final GamePopup popup;
    private InputHandler keyHandler;
    private final Thread thread;

    public Game(GameFrame frame){
        super();

        this.frame = frame;
        thread = new Thread(this);
        maze = new Maze(screenSize.width, screenSize.height);
        popup = new GamePopup(this);
    }

    public void NewGame(String name){
        this.setVisible(true);

        //setup maze
        maze.create();

        //setup items
        items = new ArrayList<>();
        for (int i = 0; i<5; i++){
            items.add(new Coin(maze.getAPath()));
            items.add(new Torch(maze.getAPath()));
        }

        //setup players
        MyPoint startPlayer = maze.getAPath();
        player = new Player(name, startPlayer);

        //setup addons
        popup.init();
        keyHandler = new InputHandler(this);
        stopWatch = new StopWatch(0, 0);
        stopWatch.restart();

        //start the game
        System.out.println(thread.getState());
        if (thread.getState() == Thread.State.WAITING){
            synchronized (thread){
                thread.notify();
            }
        }

        else
            thread.start();
        System.out.println(thread.getState());

    }

    public void EndGame(){
        player.setTime(stopWatch.getMinute(), stopWatch.getSecond());
        savePlayer save = new savePlayer();

        try {
            save.save(player);
            PrintWriter writer = new PrintWriter("Resources/data/lastGame.txt");
            writer.print("");
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        frame.scoreState();
        synchronized (thread){
            try {
                thread.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void KeyHandling(){
        if (keyHandler.isDOWN())
            player.move(Direction.DOWN, maze.getSurrounding(player));
        if (keyHandler.isUP())
            player.move(Direction.UP, maze.getSurrounding(player));
        if (keyHandler.isLEFT())
            player.move(Direction.LEFT, maze.getSurrounding(player));
        if (keyHandler.isRIGHT())
            player.move(Direction.RIGHT, maze.getSurrounding(player));
        if (keyHandler.isESC()){
            stopWatch.stop();
            popup.popup(frame);
        }
        items.forEach(item -> {
            if (player.collision(item) && item.isAvailable())
                item.pickedUpBy(player);
        });
    }

    @Override
    public void run() {
        long start;
        long elapsed;
        long wait;
        long targetTime = 1000 / 60; //1000/FPS

        while(true){
            start = System.nanoTime();


            repaint();
            KeyHandling();

            if (player.collision(maze.getExit()))
                EndGame();

            elapsed = System.nanoTime() - start;
            wait = Math.abs(targetTime - elapsed/1000000);


            try {
                Thread.sleep(wait);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //EndGame();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        maze.draw(g);
        items.forEach(item -> item.draw(g));
        player.draw(g);

        g.setFont(font.deriveFont(Font.PLAIN, 30*scale));
        g.setColor(Color.WHITE);
        g.drawString("Score: " + player.getScore(), (int)(30*scale), (int)(30*scale));
        g.drawString("Time: " + stopWatch.getMinute() +":"+stopWatch.getSecond(), (int)(30*scale), (int)(30*scale)*2);
    }

    public void save() throws IOException {
        FileOutputStream fos = new FileOutputStream("Resources/data/lastGame.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(player);
        oos.writeObject(items);
        oos.writeObject(maze.getExit());
        oos.writeObject(maze.getField());
        oos.writeObject(stopWatch);

        frame.menuState();
        oos.close();
    }

    @SuppressWarnings("unchecked")
    public void load() throws IOException, ClassNotFoundException {
        //read
        FileInputStream fis = new FileInputStream("Resources/data/lastGame.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);

        player = (Player) ois.readObject();

        items = (ArrayList<Item>) ois.readObject();
        for (Item item : items) {
            item.load();
        }

        MyPoint exit = (MyPoint) ois.readObject();
        Block[][] blocks = (Block[][]) ois.readObject();

        maze= new Maze(blocks, exit);
        StopWatch tmp = (StopWatch) ois.readObject();
        stopWatch = new StopWatch(tmp.getMinute(), tmp.getSecond());
        stopWatch.start();

        //setup addons
        popup.init();
        keyHandler = new InputHandler(this);

        if (thread.getState() == Thread.State.WAITING){
            synchronized (thread){
                thread.notify();
            }
        }
        else
            thread.start();
    }

    public void resume(){
        frame.setContentPane(this);
        this.grabFocus();
        frame.pack();

        keyHandler.setESC(false);
        synchronized (thread){
            thread.notify();
        }
        stopWatch.start();
    }

    public void stopped(){
        synchronized (thread){
            try {
                thread.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
