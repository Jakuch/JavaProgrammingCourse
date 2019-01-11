package SortApp.sorters;

import SortApp.Sorter;

public class HeapSorter implements Sorter {

    @Override
    public void sort(int[] array) {
        buildHeap(array);
        sortUsingHeap(array);
    }

    private static void sortUsingHeap(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            Sorter.swapElements(array, 0, i);
            fixHeapDownwards(array, i, 0);

        }
    }

    public static void buildHeap(int[] array) {
        for (int i = 1; i < array.length; i++) {
            fixHeapUpwards(array, i);
        }
    }

    private static void fixHeapUpwards(int[] array, int index) {
        if (index > 0) {
            //Rekurencja
            if (array[index] > array[(index - 1) / 2]) {
                Sorter.swapElements(array, index, (index - 1) / 2);
                fixHeapUpwards(array, (index - 1) / 2);
            }
        }
    }

    private static void fixHeapDownwards(int[] array, int heapSize, int index) {
        Integer greaterIndex = higherIndex(array, heapSize, index);
        if (greaterIndex != null && array[greaterIndex] > array[index]) {
            Sorter.swapElements(array, greaterIndex, index);
            fixHeapDownwards(array, heapSize, greaterIndex);
        }
    }

    private static Integer higherIndex(int[] array, int heapSize, int index) {
        int leftChildIndex = 2 * index + 1;
        if (leftChildIndex < heapSize) { //sprawdzenie czy w drzewie jest lewe dziecko
            int rightChildIndex = leftChildIndex + 1;
            if (rightChildIndex < heapSize) { //sprawdzenie czy w drzewie jest prawe dziecko
                return array[rightChildIndex] > array[leftChildIndex] ? rightChildIndex : leftChildIndex;
            } else {
                return leftChildIndex;
            }
        } else {
            return null;
        }
    }

    @Override
    public String getAlgorithmName() {
        return "Heap Sort";
    }
}
