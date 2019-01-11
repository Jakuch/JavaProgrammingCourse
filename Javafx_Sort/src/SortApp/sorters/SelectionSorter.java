package SortApp.sorters;

import SortApp.Sorter;

public class SelectionSorter implements Sorter {

    @Override
    public void sort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            Sorter.swapElements(array, minIndex, i);
        }
    }

    @Override
    public String getAlgorithmName() {
        return "Selection Sort";
    }
}
