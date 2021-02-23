package maze;

import point.Drawable;
import point.MyPoint;
import point.block.Block;
import point.player.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Maze implements Drawable {
    private final Block[][] field;
    private final static MyPoint fieldSize = new MyPoint(21, 21);

    private final static MyPoint startPoint = new MyPoint(1, 1);

    private MyPoint exit;

    //constructors
    public Maze(int width, int height){
        BufferedImage wallImage = null;
        try {
            wallImage = ImageIO.read(getClass().getResourceAsStream("/Sprites/wall.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        field = new Block[fieldSize.getX()][ fieldSize.getY()];

        int x =   ((width - fieldSize.getX()*Block.getLength())/2);
        int y =   ((height - fieldSize.getY()*Block.getLength())/2);
        for (int i = 0; i< fieldSize.getX(); i++){
            for (int j = 0; j< fieldSize.getY(); j++){
                field[i][j] = new Block(x+i*Block.getLength(), y+j*Block.getLength(), wallImage);
            }
        }
    }

    public Maze(Block[][] newField, MyPoint exit) throws IOException {
        field = new Block[fieldSize.getX()][ fieldSize.getY()];

        this.exit = exit;

        BufferedImage wallImage = ImageIO.read(getClass().getResourceAsStream("/Sprites/wall.png"));

        for (int i = 0; i<newField.length; i++){
            for (int j = 0; j<newField[i].length; j++){
                field[i][j] = newField[i][j];
                field[i][j].load(wallImage);
            }
        }
    }

    public static MyPoint getFieldSize() {
        return fieldSize;
    }

    /**
     * @param player the player of the game
     * @return a 3x3 matrix surrounding the player
     */

    public Block[][] getSurrounding(Player player){
        int idxX = Math.min((int)Math.ceil((player.getX()-field[0][0].getX())/(MyPoint.getLength())), Maze.getFieldSize().getX()-2);
        int idxY = Math.min((int)Math.ceil((player.getY()-field[0][0].getY())/(MyPoint.getLength())), Maze.getFieldSize().getY()-2);

        idxX = Math.max(idxX, 1);
        idxY = Math.max(idxY, 1);

        Block[][] sor;
        sor = new Block[3][3];
        for (int i=0; i<3; i++){
            for (int j =0; j<3; j++){
                sor[i][j] = new Block();
            }
        }

        for (int i = 0; i<3; i++){
            for (int j = 0; j<3; j++){
                sor[i][j] = field[i+idxX-1][j+idxY-1];
            }
        }
        return sor;
    }

    public Block[][] getField() {
        return field;
    }

    public MyPoint getExit() {
        return exit;
    }

    /**
     * We set the maze in init state, only with walls.
     * Than we start to create the maze.
     */
    public void create(){
        for (int i = 0; i< fieldSize.getX(); i++){
            for (int j = 0; j< fieldSize.getY(); j++){
                field[i][j].setPath(false);
            }
        }

        CreateMaze createMaze = new CreateMaze(fieldSize, field);
        createMaze.create(startPoint);
        exit = createMaze.createExit();
    }

    /**
     * give a random path
     */
    public MyPoint getAPath(){
        Random rand = new Random();
        int x = rand.nextInt(20)+1;
        int y = rand.nextInt(20)+1;

        if (field[x][y].isPath()){
            return field[x][y];
        }
        else {
            while (!field[x][y].isPath()) {
                x = rand.nextInt(20)+1;
                y = rand.nextInt(20)+1;
            }
        }
        return field[x][y];
    }

    /**
     * draw all the walls
     * @param g the graphic we gonna draw the block at.
     */

    @Override
    public void draw(Graphics g) {
        for (Block[] blocks : field) {
            for (Block block : blocks) {
                block.draw(g);
            }
        }
    }
}
