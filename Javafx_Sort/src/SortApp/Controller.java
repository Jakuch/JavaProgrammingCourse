package SortApp;

import SortApp.sorters.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

public class Controller {
    @FXML
    private LineChart<Number, Number> lineChart;
    @FXML
    private LineChart<Number, Number> lineChart1;
    @FXML
    private LineChart<Number, Number> lineChart2;
    @FXML
    private TextField elementsNumber;
    @FXML
    private TextField maxElemValue;
    @FXML
    private TextField elementsNumberTab2;
    @FXML
    private TextField maxElemValueTab2;
    @FXML
    private TextField timeChart1;
    @FXML
    private TextField timeChart2;
    @FXML
    private Button startGraphs;
    @FXML
    private Button resetGraph;
    @FXML
    private Button startComparison;
    @FXML
    private Button resetComparison;
    @FXML
    private ChoiceBox<String> firstSort;
    @FXML
    private ChoiceBox<String> secondSort;

    private volatile boolean secondTabIsRunnung;

//dodac inne algorytmy sortujace
//GUI -porownywane/ podglad sortowania

    public void initialize() {

        initCheckBoxes(firstSort, secondSort);
        resetGraph.setOnAction(event -> lineChart.getData().clear());
        resetComparison.setOnAction(event -> {
            secondTabIsRunnung = false;
            lineChart1.getData().clear();
            lineChart2.getData().clear();
            timeChart1.clear();
            timeChart2.clear();
        });

        startGraphs.setOnAction(event -> {
            int elements = Integer.parseInt(elementsNumber.getText());
            int elementMaxValue = Integer.parseInt(maxElemValue.getText());
            firstTab(lineChart, elements, elementMaxValue);
        });

        startComparison.setOnAction(event -> {
            secondTabIsRunnung = true;
            int chartElem = Integer.parseInt(elementsNumberTab2.getText());
            int elemValue = Integer.parseInt(maxElemValueTab2.getText());
            Sorter sorter1 = getFromFirstCheckBoxes(firstSort);
            Sorter sorter2 = getFromSecondCheckBoxes(secondSort);

            secondTabGraphs(lineChart1, lineChart2, sorter1, sorter2, chartElem, elemValue);
        });
    }

    private void firstTab(LineChart<Number, Number> chart, int elements, int elementMaxValue) {
        List<Sorter> sorters = Arrays.asList(new BubbleSorter(),
                new HeapSorter(),
                new InsertionSorter(),
                new MergeSorter(),
                new SelectionSorter());

        Platform.runLater(() -> {
            int points;
            if (elements <= 100) {
                points = 5;
            } else if (elements <= 1000) {
                points = 50;
            } else {
                points = 500;
            }

            for (Sorter sorter : sorters) {
                XYChart.Series<Number, Number> series = new XYChart.Series<>();
                series.setName(sorter.getAlgorithmName());

                chart.getData().add(series);
                for (int i = 0; i <= elements; i += points) {
                    int sortTime = SortUtils.getSortTime(i, elementMaxValue, sorter);
                    series.getData().add(new XYChart.Data<>(i, sortTime));
                }
            }
        });
    }

    private void initCheckBoxes(ChoiceBox<String> sorter1, ChoiceBox<String> sorter2) {
        ObservableList<String> availableSorters = FXCollections.observableArrayList("BubbleSorter",
                "HeapSorter",
                "InsertionSorter",
                "MergeSorter",
                "SelectionSorter");

        sorter1.setItems(availableSorters);
        sorter2.setItems(availableSorters);

    }

    private void secondTabGraphs(LineChart<Number, Number> firstGraph, LineChart<Number, Number> secondGraph, Sorter graph1, Sorter graph2, int elements, int elementsValue) {
        XYChart.Series<Number, Number> series1 = new XYChart.Series<>();
        series1.setName(graph1.getAlgorithmName());
        firstGraph.getData().add(series1);

        for (int i = 0; i <= elements; i += 500) {
            int sortTime = SortUtils.getSortTime(i, elementsValue, graph1);
            series1.getData().add(new XYChart.Data<>(i, sortTime));
            timeChart1.setText("" + sortTime);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        XYChart.Series<Number, Number> series2 = new XYChart.Series<>();
        series2.setName(graph2.getAlgorithmName());
        secondGraph.getData().add(series2);

        for (int i = 0; i <= elements; i += 500) {
            int sortTime = SortUtils.getSortTime(i, elementsValue, graph2);
            series2.getData().add(new XYChart.Data<>(i, sortTime));
            timeChart2.setText("" + sortTime);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Sorter getFromFirstCheckBoxes(ChoiceBox<String> sorterName) {
        Sorter sorter = null;
        switch (sorterName.getValue()) {
            case "BubbleSorter":
                sorter = new BubbleSorter();
                break;
            case "HeapSorter":
                sorter = new HeapSorter();
                break;
            case "InsertionSorter":
                sorter = new InsertionSorter();
                break;
            case "MergeSorter":
                sorter = new MergeSorter();
                break;
            case "SelectionSorter":
                sorter = new SelectionSorter();
                break;
        }
        return sorter;
    }

    public Sorter getFromSecondCheckBoxes(ChoiceBox<String> sorterName) {
        Sorter sorter = null;
        switch (sorterName.getValue()) {
            case "BubbleSorter":
                sorter = new BubbleSorter();
                break;
            case "HeapSorter":
                sorter = new HeapSorter();
                break;
            case "InsertionSorter":
                sorter = new InsertionSorter();
                break;
            case "MergeSorter":
                sorter = new MergeSorter();
                break;
            case "SelectionSorter":
                sorter = new SelectionSorter();
                break;
        }
        return sorter;
    }

    public LineChart<Number, Number> getLineChart() {
        return lineChart;
    }

    public LineChart<Number, Number> getLineChart1() {
        return lineChart1;
    }

    public LineChart<Number, Number> getLineChart2() {
        return lineChart2;
    }

    public TextField getElementsNumber() {
        return elementsNumber;
    }

    public TextField getMaxElemValue() {
        return maxElemValue;
    }

    public TextField getElementsNumberTab2() {
        return elementsNumberTab2;
    }

    public TextField getMaxElemValueTab2() {
        return maxElemValueTab2;
    }

    public Button getStartGraphs() {
        return startGraphs;
    }

    public Button getResetGraph() {
        return resetGraph;
    }

    public Button getStartComparison() {
        return startComparison;
    }

    public Button getResetComparison() {
        return resetComparison;
    }

    public ChoiceBox<String> getFirstSort() {
        return firstSort;
    }

    public ChoiceBox<String> getSecondSort() {
        return secondSort;
    }

    public boolean isSecondTabIsRunnung() {
        return secondTabIsRunnung;
    }

    private static void infoBox(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
}
