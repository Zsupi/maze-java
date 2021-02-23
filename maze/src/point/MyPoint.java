package point;

import java.io.Serializable;

public class MyPoint implements Serializable {
    protected int x;
    protected int y;

    protected static final int length = 40;

    //constructors
    public MyPoint(){
        x = 0;
        y = 0;
    }

    public MyPoint(int x, int y){
        this.x = x;
        this.y = y;
    }

    //get methods
    public static int getLength() {
        return length;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * @param p is a maze.MyPoint, which cant be null.
     * @return double this is the distance between the two Points
     */
    public int getDistance(MyPoint p){
        return (int)Math.sqrt(Math.pow(p.getX() - this.x, 2) - Math.pow(p.getY() - this.y, 2));
    }

    /**
     * @return The center of the point
     */
    public MyPoint getCenter(){
        return new MyPoint(x+length/2, y+length/2);
    }

    /**
     *
     * @param p is a MyPointer, cant be null
     * @return true or false depends on the coordinates of the two points
     *         if they are equal, the points will also be so the return will be true.
     */
    public boolean equals(MyPoint p){
        return this.x == p.getX() & this.y == p.getY();
    }

    //set methods
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }


}
