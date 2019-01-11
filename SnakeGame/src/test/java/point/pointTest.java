package point;

import org.junit.Test;
import snake.Point;

import static org.junit.Assert.*;

public class pointTest {

    @Test
    public void testEqualsMethodForSamePoints() {
        //given
        Point point1 = new Point(1, 1);
        //when
        Point point2 = new Point(1, 1);
        //then
        assertEquals(point1, point2);
    }

    @Test
    public void testEqualsMethodForSameObjectUsedTwice() {
        //given
        Point point = new Point(1, 1);
        //when
        //then
        assertEquals(point, point);
    }

    @Test
    public void testEqualsMethodForDifferentXParameters() {
        //given
        Point point1 = new Point(1,1);
        //when
        Point point2 = new Point(2,1);
        //then
        assertNotEquals(point1, point2);
    }

    @Test
    public void testEqualsMethodForDifferentYParameters() {
        //given
        Point point1 = new Point(1,1);
        //when
        Point point2 = new Point(1,2);
        //then
        assertNotEquals(point1, point2);
    }

    @Test
    public void testEqualsMethodForBothDifferentParameters() {
        //given
        Point point1 = new Point(1,2);
        //when
        Point point2 = new Point(2,1);
        //then
        assertNotEquals(point1, point2);
    }
}
