package snake;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

@RunWith(Parameterized.class)
public class parametrizedSnakeTestNoApple {
    private Point head;
    private List<Point> body;
    private Direction direction;
    private boolean cutTail;
    private Point expectedPoint;


    public parametrizedSnakeTestNoApple(Point head, List<Point> body, Direction direction, boolean cutTail, Point expectedPoint) {
        this.head = head;
        this.body = body;
        this.direction = direction;
        this.cutTail = cutTail;
        this.expectedPoint = expectedPoint;

    }

    @Parameterized.Parameters
    public static Object[][] data() {
        Point head = new Point(5, 5);
        List<Point> body1 = Arrays.asList(new Point(5, 6));
        List<Point> body2 = Arrays.asList(new Point(5, 6), new Point(5, 7));
        List<Point> body3 = Arrays.asList(new Point(5, 6), new Point(5, 7), new Point(6, 7));

        return new Object[][]{
                {head, body1, Direction.RIGHT, true, new Point(6, 5)},
                {head, body2, Direction.UP, true, new Point(5, 4)},
                {head, body3, Direction.LEFT, true, new Point(4, 5)},
        };
    }

    @Test
    public void testMoveMethodWithoutApple() {
        //given
        Snake snake = new Snake(head, body);
        //when
        snake.move(direction, cutTail);
        //then
        assertTrue(snake.getBody().contains(head));
        assertEquals(expectedPoint, snake.getHead());


    }
}
