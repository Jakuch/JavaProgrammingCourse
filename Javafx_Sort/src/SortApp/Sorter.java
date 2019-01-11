package SortApp;

public interface Sorter {

    void sort(int[] array);

    String getAlgorithmName();

    static void swapElements(int[] array, int index1, int index2) {
        int buffer = array[index1];
        array[index1] = array[index2];
        array[index2] = buffer;
    }


}
