package SortApp.sorters;

import SortApp.Sorter;

public class MergeSorter implements Sorter {

    @Override
    public void sort(int[] array) {
        sort(array, 0, array.length - 1);
    }

    private static void sort(int[] array, int startIndex, int endIndex) {
        if (startIndex == endIndex - 1 && array[startIndex] > array[endIndex]) {
            Sorter.swapElements(array, startIndex, endIndex);
        } else if (startIndex < endIndex) {
            int firstPartEndIndex = (startIndex + endIndex) / 2;
            int secondPartStartIndex = ((startIndex + endIndex) / 2) + 1;
            sort(array, startIndex, firstPartEndIndex);
            sort(array, secondPartStartIndex, endIndex);
            merge(array, startIndex, secondPartStartIndex, endIndex);
        }
    }

    private static void merge(int[] array, int leftPartIndexStart, int rightPartIndexStart, int endIndex) {
        int length = endIndex - leftPartIndexStart + 1;
        int[] sortedArray = new int[length];
        int firstPartIndex = leftPartIndexStart;
        int secondPartIndex = rightPartIndexStart;
        int sortedArrayIndex = 0;

        while (firstPartIndex < rightPartIndexStart && secondPartIndex <= endIndex) {
            if (array[firstPartIndex] <= array[secondPartIndex]) {
                sortedArray[sortedArrayIndex] = array[firstPartIndex];
                firstPartIndex++;
            } else {
                sortedArray[sortedArrayIndex] = array[secondPartIndex];
                secondPartIndex++;
            }
            sortedArrayIndex++;
        }

        for (; firstPartIndex < rightPartIndexStart; firstPartIndex++) {
            sortedArray[sortedArrayIndex] = array[firstPartIndex];
            sortedArrayIndex++;
        }
        for (; secondPartIndex <= endIndex; secondPartIndex++) {
            sortedArray[sortedArrayIndex] = array[secondPartIndex];
            sortedArrayIndex++;
        }
        for (sortedArrayIndex = 0; sortedArrayIndex < sortedArray.length; sortedArrayIndex++) {
            array[leftPartIndexStart + sortedArrayIndex] = sortedArray[sortedArrayIndex];
        }
    }

    @Override
    public String getAlgorithmName() {
        return "Merge Sort";
    }
}
