package pl.sdacademy.maze;

import java.io.IOException;
import java.util.List;

/**
 * main1 function, prints the maze in the console
 */
public class Main {
    public static void main(String[] args) throws IOException {
        Maze maze = new Maze("maze.txt");
        System.out.println(maze);
        List<Point> solution = new MazeSolver(maze).solve();
        new Thread(() -> {
            solution.stream()
                    .forEach(point -> {
                        maze.printWithPoint(point);
                        try {
                            Thread.sleep(250);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    });
        }).start();

    }
}
