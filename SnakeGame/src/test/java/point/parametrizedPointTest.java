package point;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import snake.Direction;
import snake.Point;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class parametrizedPointTest {
    private int x;
    private int y;
    private Direction direction;
    private Point expectedPoint;

    public parametrizedPointTest(int x, int y, Direction direction, Point expectedPoint) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.expectedPoint = expectedPoint;
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][]{
                {0, 0, Direction.UP, new Point(0, -1)},
                {1, 1, Direction.DOWN, new Point(1, 2)},
                {1, 2, Direction.LEFT, new Point(0, 2)},
                {2, 1, Direction.RIGHT, new Point(3, 1)},
        };
    }

    @Test
    public void testMoveMethodForAllDirections() {
        //given
        Point point = new Point(x, y);
        //when
        Point result = point.move(direction);
        //then
        assertEquals(expectedPoint, result);
    }
}
