package pl.sdacademy.maze;

public class State {
    private Point point;
    private int scoreFnValue; //wartosc funkcji oceniajacej
    private State origin;
    private boolean visited; //domyslnie false

    public State(Point point, int scoreFnValue, State origin) {
        this.point = point;
        this.scoreFnValue = scoreFnValue;
        this.origin = origin;
    }

    public Point getPoint() {
        return point;
    }

    public int getScoreFnValue() {
        return scoreFnValue;
    }

    public State getOrigin() {
        return origin;
    }

    public boolean isVisited() {
        return visited;
    }

    public void visit(){
        visited = true;
    }
}
