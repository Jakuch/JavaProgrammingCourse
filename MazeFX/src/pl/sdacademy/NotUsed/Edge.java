package pl.sdacademy.NotUsed;


import pl.sdacademy.maze.Point;

import java.util.Random;

public class Edge {
    private int weight;
    private Point leftPoint;
    private Point rightPoint;

    public Edge(Point leftPoint, Point rightPoint) {
        this.weight = new Random().nextInt();
        this.leftPoint = leftPoint;
        this.rightPoint = rightPoint;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Point getLeftPoint() {
        return leftPoint;
    }

    public void setLeftPoint(Point leftPoint) {
        this.leftPoint = leftPoint;
    }

    public Point getRightPoint() {
        return rightPoint;
    }

    public void setRightPoint(Point rightPoint) {
        this.rightPoint = rightPoint;
    }
}
