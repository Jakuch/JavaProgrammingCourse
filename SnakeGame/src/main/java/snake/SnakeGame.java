package snake;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public abstract class SnakeGame {
    private Snake snake;
    private Direction direction;
    private int xBound;
    private int yBound;
    private Point apple;
    private int score;

    public SnakeGame(int xBound, int yBound) {
        Point head = new Point(2, 2);
        List<Point> body = Arrays.asList(new Point(3, 2), new Point(3, 3));
        this.snake = new Snake(head, body);
        this.direction = Direction.DOWN;
        this.xBound = xBound;
        this.yBound = yBound;
        score = 0;
        randomizeApple();
    }

    public void start() {
        int speed = 500;
        while (!checkIfSnakeCrashed()) {
            onNextStep();
            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean appleEaten = snake.getHead().move(direction).equals(apple);
            snake.move(direction, !appleEaten);
            if (appleEaten) {
                if(speed > 300) {
                    speed -= 50;
                } else {
                    speed -= 10;
                }
                addScore(10);
                randomizeApple();
            }
        }
        onGameEnded();
    }

    public abstract void onGameEnded();

    public abstract void onNextStep();

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Snake getSnake() {
        return snake;
    }

    public Point getApple() {
        return apple;
    }

    public int getScore() {
        return score;
    }

    private boolean checkIfSnakeCrashed() {
        Point head = snake.getHead();
        return checkIfHeadIsOutOfBounds(head) || checkIfHeadAteBody(head);
    }

    private boolean checkIfHeadAteBody(Point head) {
        return snake.getBody().contains(head);
    }

    private boolean checkIfHeadIsOutOfBounds(Point head) {
        return head.getX() < 0 || head.getY() < 0 || head.getX() > xBound || head.getY() > yBound;
    }

    public void randomizeApple() {
        Random random = new Random();
        do {
            apple = new Point(random.nextInt(xBound), random.nextInt(yBound));
        } while (snake.contains(apple));
    }

    public void addScore(int score) {
        this.score += score;
    }
}
