package snake;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class snakeTest {

    @Test
    public void testContainsMethodForPoint() {
        //given
        Point head = new Point(1, 1);
        List<Point> body = Arrays.asList(new Point(1, 2), new Point(1, 3));
        Snake snake = new Snake(head, body);
        //when
        Point point = new Point(5, 5);
        boolean result = snake.contains(point);
        //then
        assertFalse(result);
    }

    @Test
    public void testContainsMethodForPointOnSnakeHead() {
        //given
        Point head = new Point(1, 1);
        List<Point> body = Arrays.asList(new Point(1, 2), new Point(1, 3));
        Snake snake = new Snake(head, body);
        //when
        Point point = new Point(1, 1);
        boolean result = snake.contains(point);
        //then
        assertTrue(result);
    }

    @Test
    public void testContainsMethodForPointOnSnakeBody() {
        //given
        Point head = new Point(1, 1);
        List<Point> body = Arrays.asList(new Point(1, 2), new Point(1, 3));
        Snake snake = new Snake(head, body);
        //when
        Point point = new Point(1, 1);
        boolean result = snake.contains(point);
        //then
        assertTrue(result);
    }
}
