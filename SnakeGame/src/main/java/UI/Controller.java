package UI;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import snake.Direction;
import snake.Point;
import snake.Snake;
import snake.SnakeGame;

public class Controller {
    @FXML
    private Canvas canvas;
    @FXML
    private Label gameEndLabel;
    @FXML
    private Label points;
    private SnakeGame snakeGame;
    private final static int POINT_SIZE = 10;
    private GraphicsContext graphicsContext;

    public void initialize() {
        graphicsContext = canvas.getGraphicsContext2D();
        snakeGame = new SnakeGame((int) canvas.getWidth() / POINT_SIZE, (int) canvas.getHeight() / POINT_SIZE) {
            @Override
            public void onNextStep() {
                draw();
                printScore();
            }

            @Override
            public void onGameEnded() {
                gameEndLabel.setVisible(true);
            }
        };

        Thread thread = new Thread(() -> snakeGame.start());
        thread.setDaemon(true);
        thread.start();

    }

    private void printScore() {
        Platform.runLater(() -> points.setText(""+ snakeGame.getScore()));
    }

    private void draw() {
        graphicsContext.clearRect(0, 0, canvas.getHeight(), canvas.getWidth());
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.fillRect(0,0,canvas.getWidth(),canvas.getHeight());
        graphicsContext.setFill(Color.RED);
        drawPoint(snakeGame.getApple());
        graphicsContext.setFill(Color.BLUE);
        Snake snake = snakeGame.getSnake();
        drawPoint(snake.getHead());
        graphicsContext.setFill(Color.GREEN);
        snake.getBody().forEach(this::drawPoint);
    }

    private void drawPoint(Point point) {
        graphicsContext.fillRect(point.getX() * POINT_SIZE, point.getY() * POINT_SIZE, POINT_SIZE, POINT_SIZE);
    }

    public void moveUp() {
        snakeGame.setDirection(Direction.UP);
    }

    public void moveDown() {
        snakeGame.setDirection(Direction.DOWN);
    }

    public void moveLeft() {
        snakeGame.setDirection(Direction.LEFT);
    }

    public void moveRight() {
        snakeGame.setDirection(Direction.RIGHT);
    }

    public void keyboardMovement() {
        canvas.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case W:
                    moveUp();
                    break;
                case S:
                    moveDown();
                    break;
                case A:
                    moveLeft();
                    break;
                case D:
                    moveRight();
                    break;
            }
        });
    }
}
