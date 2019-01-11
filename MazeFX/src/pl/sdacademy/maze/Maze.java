package pl.sdacademy.maze;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Maze {
    private Field[][] fields;
    private int xSize;
    private int ySize;
    private Point startPoint;
    private Point endPoint;

    /**
     * Constructor
     * @param filename gets the maze shape from the .txt file
     * @throws IOException input output exception handle
     */
    public Maze(String filename) throws IOException {
        Path path = Paths.get(filename);
        List<String> fileLines = Files.readAllLines(path);
        xSize = fileLines.get(0).length();
        ySize = fileLines.size();
        fields = new Field[xSize][ySize];
        fillFields(fileLines);
    }

    private void fillFields(List<String> fileLines) {
        for (int y = 0; y < ySize; y++) {
            String fileLine = fileLines.get(y);
            for (int x = 0; x < xSize; x++) {
                switch (fileLine.charAt(x)) {
                    case '+':
                    case '-':
                    case '|':
                        fields[x][y] = Field.WALL;
                        break;
                    case ' ':
                        fields[x][y] = Field.PATH;
                        break;
                    case 'E':
                        endPoint = new Point(x, y);
                        fields[x][y] = Field.END;
                        break;
                    case 'S':
                        startPoint = new Point(x, y);
                        fields[x][y] = Field.START;
                        break;
                }
            }
        }
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public Field getFieldAt(Point point) {
        if (point.getX() < 0 || point.getX() >= xSize || point.getY() < 0 || point.getY() >= ySize) {
            return null;
        } else {
            return fields[point.getX()][point.getY()];
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int y = 0; y < fields.length; y++) {
            for (int x = 0; x < fields.length; x++) {
                switch (fields[x][y]) {
                    case WALL:
                        result.append("*");
                        break;
                    case START:
                        result.append("S");
                        break;
                    case END:
                        result.append("E");
                        break;
                    case PATH:
                        result.append(" ");
                        break;
                }
            }
            result.append("\n");
        }
        return result.toString();
    }

    public void printWithPoint(Point point) {
        StringBuilder result = new StringBuilder();
        for (int y = 0; y < fields.length; y++) {
            for (int x = 0; x < fields.length; x++) {
                if (point.getX() == x && point.getY() == y) {
                    result.append(".");
                } else {
                    switch (fields[x][y]) {
                        case WALL:
                            result.append("*");
                            break;
                        case START:
                            result.append("S");
                            break;
                        case END:
                            result.append("E");
                            break;
                        case PATH:
                            result.append(" ");
                            break;
                    }
                }
            }
            result.append("\n");
        }
        System.out.println(result.toString());
    }

    public Field[][] getFields() {
        return fields;
    }

    public int getxSize() {
        return xSize;
    }

    public int getySize() {
        return ySize;
    }
}
