package pl.sdacademy.maze;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MazeSolver {
    private Maze maze;
    private List<State> stateList;

    public MazeSolver(Maze maze) {
        this.maze = maze;
    }

    public List<Point> solve(){
        return solve(maze.getStartPoint());
    }

    public List<Point> solve(Point startPoint) {
        stateList = new ArrayList<>();
        State state = new State(startPoint, calculateScoreFnValue(startPoint), null);
        stateList.add(state);
        while (!state.getPoint().equals(maze.getEndPoint())) {
            visit(state);
            state = findBestUnvisitedState();
        }

        List<Point> pointList = new ArrayList<>();

        while(!state.getPoint().equals(startPoint)){
            pointList.add(state.getPoint());
            state = state.getOrigin();
        }
        pointList.add(startPoint);
        Collections.reverse(pointList);

        return pointList;
    }

    private State findBestUnvisitedState() {
        return stateList.stream()
                .filter(state -> !state.isVisited())
                .min((state1, state2) -> state1.getScoreFnValue() - state2.getScoreFnValue())
                .orElseThrow(() -> new RuntimeException("Skonczyly sie nieodwiedzone stany"));
    }

    private void visit(State state) {
        state.visit();
        int x = state.getPoint().getX();
        int y = state.getPoint().getY();

        List<Point> surroundingPoint = Arrays.asList(
                new Point(x + 1, y),
                new Point(x - 1, y),
                new Point(x, y + 1),
                new Point(x, y - 1)
        );

        surroundingPoint.stream()
                .filter(point -> {
                    Field field = maze.getFieldAt(point);
                    return field != null && field != Field.WALL && !doesStateExist(point);
                })
                .forEach(point -> stateList.add(new State(point, calculateScoreFnValue(point), state)));
    }

    private boolean doesStateExist(Point point) {
        return stateList.stream()
                .anyMatch(state -> state.getPoint().equals(point));
    }

    public int calculateScoreFnValue(Point point) {
        Point endPoint = maze.getEndPoint();
        return Math.abs(endPoint.getX() - point.getX()) + Math.abs(endPoint.getY() - point.getY());
    }
}
