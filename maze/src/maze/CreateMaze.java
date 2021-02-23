package maze;

import point.block.Block;
import point.MyPoint;

import java.util.Random;

public class CreateMaze {
    private final Direction[] directions = {Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT};
    private final MyPoint fieldSize;
    private final Block[][]field;

    public CreateMaze(MyPoint size, Block[][] field){
        fieldSize = size;
        this.field = field;
    }

    /**
     * Its shuffle the directions randomly with the Fisher-Yates shuffle
     */
    private void shuffle() {
        Random random = new Random();

        for (int i = 3; i > 0; i--) {
            int r = random.nextInt(i+1);
            Direction temp = directions[i];
            directions[i] = directions[r];
            directions[r] = temp;
        }
    }

    /**
     * The algorithm is based on Czirkos Zoltán's C maze generator which can be found on infoc.
     * It goes in 4 directions (up, down right, left) in random orders, which was made by the shuffle method.
     * It only can goes in a direction when on that way the second neighbor is not the edge of the field and it's
     * not a path. If it can it set its neighbor's path attribute to true and set its own startPoint to second neighbor
     * in that direction.
     *
     * @param startPoint this is the point where the algorithm start the paths.
     * @author Czirkos Zoltán
     */
    public void create(MyPoint startPoint){
        shuffle();

        if (startPoint.equals(new MyPoint(0,0)) || startPoint.equals(new MyPoint(fieldSize.getX()-1, fieldSize.getY()-1))){
            return;
        }

        field[  startPoint.getX()][  startPoint.getY()].setPath(true);
        for (Direction direction : directions) {
            switch (direction) {
                case RIGHT:
                    if (startPoint.getX() < fieldSize.getX() - 2 && !field[  startPoint.getX() + 2][  startPoint.getY()].isPath()) {
                        field[  startPoint.getX() + 1][  startPoint.getY()].setPath(true);

                        MyPoint newStart = new MyPoint(startPoint.getX() + 2, startPoint.getY());
                        create(newStart);
                    }
                    break;
                case LEFT:
                    if (startPoint.getX() >= 2 && !field[  startPoint.getX() - 2][  startPoint.getY()].isPath()) {
                        field[  startPoint.getX() - 1][  startPoint.getY()].setPath(true);

                        MyPoint newStart = new MyPoint(startPoint.getX() - 2, startPoint.getY());
                        create(newStart);
                    }
                    break;
                case UP:
                    if (startPoint.getY() >= 2 && !field[  startPoint.getX()][  startPoint.getY() - 2].isPath()) {
                        field[  startPoint.getX()][  startPoint.getY() - 1].setPath(true);

                        MyPoint newStart = new MyPoint(startPoint.getX(), startPoint.getY() - 2);
                        create(newStart);
                    }
                    break;
                case DOWN:
                    if (startPoint.getY() < fieldSize.getY() - 2 && !field[  startPoint.getX()][  startPoint.getY() + 2].isPath()) {
                        field[  startPoint.getX()][  startPoint.getY() + 1].setPath(true);

                        MyPoint newStart = new MyPoint(startPoint.getX(), startPoint.getY() + 2);
                        create(newStart);
                    }
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + direction);
            }
        }
    }

    public Block createExit(){
        shuffle();
        Random random = new Random();
        int selected = random.nextInt(fieldSize.getX());
        switch (directions[0]) {
            case UP -> {
                while (!field[selected][1].isPath()) {
                    selected = random.nextInt(fieldSize.getX());
                }
                field[selected][0].setPath(true);
                return field[selected][0];
            }
            case DOWN -> {
                while (!field[selected][fieldSize.getY() - 2].isPath()) {
                    selected = random.nextInt(fieldSize.getX());
                }
                field[selected][fieldSize.getY() - 1].setPath(true);
                return field[selected][fieldSize.getY() - 1];
            }
            case LEFT -> {
                while (!field[1][selected].isPath()) {
                    selected = random.nextInt(fieldSize.getY());
                }
                field[0][selected].setPath(true);
                return field[0][selected];
            }
            case RIGHT -> {
                while (!field[fieldSize.getY() - 2][selected].isPath()) {
                    selected = random.nextInt(fieldSize.getY());
                }
                field[fieldSize.getY() - 1][selected].setPath(true);
                return field[fieldSize.getY() - 1][selected];
            }
            default -> throw new IllegalStateException("Unexpected value: " + directions[0]);
        }
    }
}
