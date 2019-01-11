package snake;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

@RunWith(Parameterized.class)
public class parametrizedSnakeTestWithApple {
    private Point head;
    private List<Point> body;
    private Point apple;
    private Direction direction;
    private boolean cutTail;


    public parametrizedSnakeTestWithApple(Point head, List<Point> body, Point apple, Direction direction, boolean cutTail) {
        this.head = head;
        this.body = body;
        this.apple = apple;
        this.direction = direction;
        this.cutTail = cutTail;
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        Point head = new Point(5, 5);
        List<Point> body1 = Arrays.asList(new Point(5, 6));
        List<Point> body2 = Arrays.asList(new Point(5, 6), new Point(5, 7));
        List<Point> body3 = Arrays.asList(new Point(5, 6), new Point(5, 7), new Point(6, 7));

        return new Object[][]{
                {head, body1, new Point(4, 5), Direction.LEFT, false},
                {head, body2, new Point(5, 4), Direction.UP, false},
                {head, body3, new Point(6, 5), Direction.RIGHT, false},
        };
    }

    @Test
    public void testMoveMethodWithApple() {
        //given
        Snake snake = new Snake(head, body);
        //when
        snake.move(direction, cutTail);
        //then
        assertTrue(snake.getBody().contains(head));
        assertEquals(apple, snake.getHead());
        assertEquals(body.size() + 1, snake.getBody().size());

    }
}
