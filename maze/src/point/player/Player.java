package point.player;

import maze.Direction;
import point.Drawable;
import point.MyPoint;
import point.block.Block;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class Player extends MyPoint implements Drawable {
    private static final int SPEED = 5;

    private final String name;
    private int score;
    private int rad;


    private int minute;
    private int second;

    public Player(String name, MyPoint start){
        super(start.getX(), start.getY());
        this.name = name;
        rad = 200;
        score = 0;
    }

    public Player(String name, int point, int minute, int second){
        this.name = name;
        this.score = point;
        this.minute = minute;
        this.second = second;
    }

    //getter method
    public static int getLength(){
        return length/2;
    }

    public MyPoint getCenter(){
        return new MyPoint(x+length/4, y+length/4);
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }

    public int getRad() {
        return rad;
    }

    //setter method
    public void setTime(int minute, int second){
        this.minute = minute;
        this.second = second;
    }

    public void addPoint(int point){
        score += point;
    }

    public void addRad(int rad){
        this.rad += rad;
    }

    public void move(Direction direction, Block[][]surrounding){
        switch (direction) {
            case RIGHT -> {
                x += SPEED;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (collision(surrounding[i][j]) && !surrounding[i][j].isPath()) {
                            x -= SPEED;
                            return;
                        }
                    }
                }
            }
            case LEFT -> {
                x -= SPEED;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (collision(surrounding[i][j]) && !surrounding[i][j].isPath()) {
                            x += SPEED;
                            return;
                        }
                    }
                }
            }
            case UP -> {
                y -= SPEED;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (collision(surrounding[i][j]) && !surrounding[i][j].isPath()) {
                            y += SPEED;
                            return;
                        }
                    }
                }
            }
            case DOWN -> {
                y += SPEED;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (collision(surrounding[i][j]) && !surrounding[i][j].isPath()) {
                            y -= SPEED;
                            return;
                        }
                    }
                }
            }
            default -> throw new IllegalStateException("Unexpected value: " + direction);
        }
    }

    public boolean collision(MyPoint point){
        float deltax = point.getCenter().getX() - this.getCenter().getX();
        float deltay = point.getCenter().getY() - this.getCenter().getY();

        float collidex = Math.abs(deltax) - (MyPoint.getLength()/2 + getLength()/2);
        float collidey = Math.abs(deltay) - (MyPoint.getLength()/2 + getLength()/2);

        return collidex < 0.0f && collidey < 0.0f;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        Area field = new Area(new Rectangle(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height));
        Area circle = new Area(new Ellipse2D.Double(getCenter().getX()-rad/2, getCenter().getY()-rad/2, rad, rad));
        field.subtract(circle);
        ((Graphics2D) g).fill(field);

        g.setColor(Color.green);
        g.drawRect(x, y, getLength(), getLength());
    }
}
