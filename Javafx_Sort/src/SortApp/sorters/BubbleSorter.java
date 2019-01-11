package SortApp.sorters;

import SortApp.Sorter;

public class BubbleSorter implements Sorter {

    @Override
    public void sort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    Sorter.swapElements(array, j, j + 1);
                }
            }
        }
    }

    @Override
    public String getAlgorithmName() {
        return "Bubble Sort";
    }


}
