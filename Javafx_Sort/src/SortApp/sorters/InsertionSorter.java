package SortApp.sorters;

import SortApp.Sorter;

public class InsertionSorter implements Sorter {

    @Override
    public void sort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            for (int j = i; j > 0 && array[j - 1] > array[j]; j--) {
                Sorter.swapElements(array, j, j - 1);
            }
        }
    }

    @Override
    public String getAlgorithmName() {
        return "Insertion Sort";
    }
}
