package pl.sdacademy.maze;



/**
 * Describes single point in the maze
 */
public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Point){
            Point point = (Point) obj;
            return this.x == point.x && this.y == point.y;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
