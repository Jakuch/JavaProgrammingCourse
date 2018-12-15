package vaccineApplication.infection;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import vaccineApplication.population.Person;

public class InfectionVisualisation {
    private Infection infection;
    private Label[][] labels;


    public InfectionVisualisation(Infection infection, GridPane gridPane) {
        this.infection = infection;
        Platform.runLater(() -> {
            styleGridPane(gridPane);
            int populationSize = (int) Math.sqrt(infection.getPopulation().getPopulationSize());
            labels = new Label[populationSize][populationSize];
            for (int x = 0; x < populationSize; x++) {
                for (int y = 0; y < populationSize; y++) {
                    Label label = new Label();
                    labels[x][y] = label;
                    styleLabel(new Point(x, y), infection.getPopulation().getPeople()[x][y]);
                    gridPane.add(label, x, y);
                }
            }
        });
    }

    public void clear(GridPane gridPane){
        int populationSize = (int) Math.sqrt(infection.getPopulation().getPopulationSize());
        for (int x = 0; x < populationSize; x++) {
            for (int y = 0; y < populationSize; y++) {
                //Label label = new Label();
                clearLabels(new Point(x,y));
                //labels[x][y] = label;
                //gridPane.add(label, x, y);
            }
        }
        clearGridPane(gridPane);
    }

    private void clearGridPane(GridPane gridPane) {
        gridPane.setStyle(null);
        gridPane.setGridLinesVisible(false);
    }

    private void clearLabels(Point point) {
        labels[point.getX()][point.getY()].setStyle(null);
    }

    private void styleGridPane(GridPane gridPane) {
        gridPane.setGridLinesVisible(true);
    }

    private void styleLabel(Point point, Person person) {
        Label label = labels[point.getX()][point.getY()];
        label.setPrefWidth(5);
        label.setPrefHeight(5);
        label.setMaxSize(5, 5);
        if (person.isSick() && person.isVaccinated()) {
            label.setStyle("-fx-background-color: chocolate");
        } else if (person.isVaccinated()) {
            label.setStyle("-fx-background-color: blue");
        } else if (person.isSick()) {
            label.setStyle("-fx-background-color: red");
        } else if (person.isDead()) {
            label.setStyle("-fx-background-color: black");
        } else {
            label.setStyle("-fx-background-color: green");
        }
    }
}
