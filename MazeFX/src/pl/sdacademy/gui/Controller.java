package pl.sdacademy.gui;

import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import pl.sdacademy.maze.Field;
import pl.sdacademy.maze.Maze;
import pl.sdacademy.maze.MazeSolver;
import pl.sdacademy.maze.Point;

import java.io.IOException;
import java.util.List;


public class Controller{
    @FXML
    private GridPane gridPane;
    private Label[][] labels;
    private Point currentPoint;
    private Maze maze;

    /**
     * Labirynth initialization
     *
     * @throws IOException labyrinth is initialized from the .txt file in the project named maze.txt
     */
    public void initialize() throws IOException {
        maze = new Maze("maze.txt");
        currentPoint = maze.getStartPoint();
        labels = new Label[maze.getxSize()][maze.getySize()];
        for (int x = 0; x < maze.getxSize(); x++) {
            for (int y = 0; y < maze.getySize(); y++) {
                Label label = new Label();
                labels[x][y] = label;
                styleLabel(new Point(x, y));
                gridPane.add(label, x, y);
            }
        }

    }

    /**
     * Labyrinth syle
     *
     * @param point allows change of the style of the labyrinth
     */
    private void styleLabel(Point point) {
        Label label = labels[point.getX()][point.getY()];
        label.setPrefHeight(20);
        label.setPrefWidth(20);
        gridPane.setHgap(0);
        gridPane.setVgap(0);
        if (point.equals(currentPoint)) {
            label.setStyle("-fx-background-color: chocolate");
        } else {
            switch (maze.getFieldAt(point)) {
                case WALL:
                    label.setStyle("-fx-background-color: black");
                    break;
                case START:
                    label.setStyle("-fx-background-color: green");
                    break;
                case END:
                    label.setStyle("-fx-background-color: red");
                    break;
                case PATH:
                        label.setStyle("-fx-background-color: white");
                    break;
            }
        }
    }

    /**
     * User movement methods
     *
     * @param point allows user to move in the labyrinth using GUI buttons UP, LEFT, DOWN, RIGHT
     */
    private void userMove(Point point) {
        Field field = maze.getFieldAt(point);
        if (field != null && field != Field.WALL) {
            Point oldCurrentPoint = currentPoint;
            currentPoint = point;
            styleLabel(oldCurrentPoint);
            styleLabel(currentPoint);
        }
    }

    public void onRightButtonClick() {
        Point newCurrentPoint = new Point(currentPoint.getX() + 1, currentPoint.getY());
        userMove(newCurrentPoint);
    }

    public void onLeftButtonClick() {
        Point newCurrentPoint = new Point(currentPoint.getX() - 1, currentPoint.getY());
        userMove(newCurrentPoint);
    }

    public void onUpButtonClick() {
        Point newCurrentPoint = new Point(currentPoint.getX(), currentPoint.getY() - 1);
        userMove(newCurrentPoint);
    }

    public void onDownButtonClick() {
        Point newCurrentPoint = new Point(currentPoint.getX(), currentPoint.getY() + 1);
        userMove(newCurrentPoint);
    }

    /**
     * Solver method
     * Moves the user cursor to the end of the labyrinth
     */
    public void onSolveButtonClick() {
        List<Point> solution = new MazeSolver(maze).solve(currentPoint);
        Thread solutionThread = new Thread(() -> {
            solution.forEach(point -> {
                userMove(point);
                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        });
        solutionThread.setDaemon(true);
        solutionThread.start();
    }

}


