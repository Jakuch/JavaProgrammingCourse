package SortApp;

import java.util.Random;

public class SortUtils {

    public static int getSortTime(int numberOfElements, int maxValueOfElement, Sorter sorter) {
        int[] array = generateArray(numberOfElements, maxValueOfElement);

        long startTime = System.currentTimeMillis();
        sorter.sort(array);
        //System.out.println("Czas sortowania " + sorter.getAlgorithmName() + " : " + (System.currentTimeMillis() - startTime));
        return (int) (System.currentTimeMillis() - startTime);
    }

    private static int[] generateArray(int numberOfElements, int maxValueOfElement) {
        Random generate = new Random();
        int[] newArray = new int[numberOfElements];
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = generate.nextInt(maxValueOfElement + 1);
        }
        return newArray;
    }
}
