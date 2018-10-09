package VaccineApplication;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.util.Random;

public class Controller {
    private volatile boolean bExit;
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
    private Label labelOut;
    @FXML
    private TextField popul;

    private Random randomGenerator = new Random();
    private Person[][] population;

    public void initialize() {
        System.out.println("Inicjalizacja aplikacji Vaccine");
        Thread message = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            infoBox("Input probability (double 0 - 1 values), length of the population square (int positive values ) and click start button. ", "Instructions");
        });
        message.start();
        bExit = false;

        buttonStart.setOnAction(event -> {
            bExit = false;
            infectionThread();
        });

        buttonStop.setOnAction(event -> bExit = true);
    }

    private static void infoBox(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    private void infectionThread() {
        population = populationGenerator();
        Thread infection = new Thread(() -> {
            int daysPassed = 0;
            int infected;
            int getSick;

            for (int k = 0; ; k++) {
                daysPassed += 1;
                daysOut.setText("" + daysPassed);
                infected = 0;
                int lengthLoopI = Integer.parseInt(popSquareL.getText());
                int lengthLoopJ = Integer.parseInt(popSquareL.getText());

                for (int i = 0; i < lengthLoopI; i++) {
                    for (int j = 0; j < lengthLoopJ; j++) {
                        //People infected
                        if (population[i][j].isSick()) {
                            infected += 1;
                        }
                        System.out.print(population[i][j]);
                    }
                    System.out.println();
                }
                System.out.println();

                pplOut.setText("" + infected);
                popul.setText("" + lengthLoopI * lengthLoopJ);

                //Infection spread
                for (int i = 0; i < lengthLoopI; i++) {
                    for (int j = 0; j < lengthLoopJ; j++) {
                        if (population[i][j].isSick() && population[i][j].getDaysInfecting()<=Integer.parseInt(daysIn.getText())) {
                            if (j < Integer.parseInt(popSquareL.getText()) - 1 && !population[i][j + 1].isSick()) {
                                getSick = randomGenerator.nextInt(100);
                                if (population[i][j + 1].isVaccinated()) {
                                    population[i][j + 1].setSick(Double.parseDouble(infProbVacc.getText()) * 100 > getSick);
                                } else {
                                    population[i][j + 1].setSick(Double.parseDouble(infProb.getText()) * 100 > getSick);
                                }
                            }
                            if (i < Integer.parseInt(popSquareL.getText()) - 1 && !population[i + 1][j].isSick()) {
                                getSick = randomGenerator.nextInt(100);
                                if (population[i + 1][j].isVaccinated()) {
                                    population[i + 1][j].setSick(Double.parseDouble(infProbVacc.getText()) * 100 > getSick);
                                } else {
                                    population[i + 1][j].setSick(Double.parseDouble(infProb.getText()) * 100 > getSick);
                                }
                            }
                            if (j > 0 && !population[i][j - 1].isSick()) {
                                getSick = randomGenerator.nextInt(100);
                                if (population[i][j - 1].isVaccinated()) {
                                    population[i][j - 1].setSick(Double.parseDouble(infProbVacc.getText()) * 100 > getSick);
                                } else {
                                    population[i][j - 1].setSick(Double.parseDouble(infProb.getText()) * 100 > getSick);
                                }
                            }
                            if (i > 0 && !population[i - 1][j].isSick()) {
                                getSick = randomGenerator.nextInt(100);
                                if (population[i - 1][j].isVaccinated()) {
                                    population[i - 1][j].setSick(Double.parseDouble(infProbVacc.getText()) * 100 > getSick);
                                } else {
                                    population[i - 1][j].setSick(Double.parseDouble(infProb.getText()) * 100 > getSick);
                                }
                            }
                            population[i][j].setDaysInfecting(population[i][j].getDaysInfecting()+1);
                        } else {

                        }
                    }
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (bExit) {
                    break;
                }
                if (infected >= lengthLoopI * lengthLoopJ) {
                    break;
                }
            }
        });
        infection.start();
    }

    private Person[][] populationGenerator() {
        int populationSize = Integer.parseInt(popSquareL.getText());
        Person[][] newPopulation = new Person[populationSize][populationSize];
        for (int i = 0; i < populationSize; i++) {
            for (int j = 0; j < populationSize; j++) {
                int sickProb = randomGenerator.nextInt(100);
                int vaccProb = randomGenerator.nextInt(100);
                Person newPerson = new Person(Double.parseDouble(infProb.getText()) * 100 > sickProb, Double.parseDouble(vaccineProb.getText()) * 100 > vaccProb);
                if (newPerson.isVaccinated()) {
                    newPerson.setSick(false);
                }
                newPopulation[i][j] = newPerson;
                //System.out.print(newPopulation[i][j]);
            }
            //System.out.println();
        }
        return newPopulation;
    }

}
