package snake;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    private Point head;
    private List<Point> body;

    public Snake(Point head, List<Point> body) {
        this.head = head;
        this.body = new ArrayList<>(body);
    }

    public Snake(Point head) {
        this.head = head;
        this.body = new ArrayList<>();
    }

    public void move(Direction direction, boolean cutTail) {
        body.add(0, head);
        if (cutTail) {
            body.remove(body.size() - 1);
        }
        head = head.move(direction);
    }

    @Override
    public String toString() {
        return "Snake{" +
                "head=" + head +
                ", body=" + body +
                '}';
    }

    public Point getHead() {
        return head;
    }

    public List<Point> getBody() {
        return body;
    }

    public boolean contains(Point point) {
        return body.contains(point) || head.equals(point);
    }
}
