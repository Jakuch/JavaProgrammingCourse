package vaccineApplication.GUI;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import vaccineApplication.infection.Infection;
import vaccineApplication.infection.InfectionVisualisation;
import vaccineApplication.population.Population;

import javax.swing.*;

public class Controller {
    @FXML
    private Button buttonStart;
    @FXML
    private Button buttonStop;
    @FXML
    private TextField daysIn;
    @FXML
    private TextField popSquareL;
    @FXML
    private TextField infProb;
    @FXML
    private TextField infProbVacc;
    @FXML
    private TextField vaccineProb;
    @FXML
    private TextField daysOut;
    @FXML
    private TextField pplOut;
    @FXML
    private TextField pplDeadOut;
    @FXML
    private TextField popul;
    @FXML
    private GridPane gridPane;

    private Population population;
    private InfectionVisualisation visualisation;
    private Infection infection;
    private volatile boolean bExit;

    public void initialize() {
        System.out.println("App initialization");
        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            infoBox("Input probability (double 0 - 1 values), length of the population square (int positive values ) and click start button.\n " +
                    "                               Visualisation is on only for <50 population square side length ", "Instructions");
        }).start();
        bExit = false;

        buttonStart.setOnAction(event -> {
            bExit = false;
            startInfection();
        });

        buttonStop.setOnAction(event -> {
            pauseInfection();
        });
    }

    private static void infoBox(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public void onResetViewClick() {
        clearVisualisation();
    }

    public void onGenerateButtonClick() {
        population = new Population.populationBuilder(Integer.parseInt(popSquareL.getText()))
                .setDaysInfecting(Integer.parseInt(daysIn.getText()))
                .setInfectionProbabilityWithVaccine(Double.parseDouble(infProbVacc.getText()))
                .setSickProbability(Double.parseDouble(infProb.getText()))
                .setVaccinatedProbability(Double.parseDouble(vaccineProb.getText()))
                .build();

        infection = new Infection(population);
        visualisation = new InfectionVisualisation(infection, gridPane);


    }

    private void startInfection() {
        infectionThread().start();
    }

    private void pauseInfection() {
        bExit = true;
    }


    private Thread infectionThread() {
        return new Thread(() -> {
            for (int i = 0; ; i++) {

                Platform.runLater(() -> {
                    daysOut.setText("" + infection.getDaysPassed());
                    popul.setText("" + population.getPopulationSize());
                    pplOut.setText("" + infection.getNumberOfInfected());
                    pplDeadOut.setText("" + infection.getPopulation().getDead());
                });

                visualisation = updateVisualisation();
                infection.start();

                if (bExit) {
                    break;
                }
                if (infection.getNumberOfInfected() >= population.getPopulationSize()) {
                    break;
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private InfectionVisualisation updateVisualisation() {
        return new InfectionVisualisation(infection, gridPane);
    }

    public void onInstructionsButtonClick() {
        infoBox("Input probability (double 0 - 1 values), length of the population square (int positive values ) and click start button.\n " +
                "                               Visualisation is on only for <50 population square side length ", "Instructions");
    }

    public void onCloseButtonClick() {
        Runtime.getRuntime().exit(0);
    }

    private void clearVisualisation() {
        visualisation.clear(gridPane);
    }

}
