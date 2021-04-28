package 排序;
import java.util.*;

public class SelectionSortDemo {
    public static void main(String[] args) {
        // int[] arr = {2,1,3,4,5,6};
        int[] arr = generateRandomArray(80000, 100000);
        long startTime = System.currentTimeMillis();
        // System.out.println(Arrays.toString(arr));
        SelectionSort(arr);
        long endTime = System.currentTimeMillis();
        // System.out.println(Arrays.toString(arr));
        System.out.println(endTime-startTime+ " " + (endTime-startTime)/1000);
    }

    private static void SelectionSort(int[] arr){
        int length = arr.length;
        int minIndex = 0;
        for (int i = 0; i < length - 1; i++) {
            minIndex = i;
            for (int j = i; j < length; j++) {
                if (arr[minIndex]>arr[j]){
                    minIndex = j;
                }
            }
            if (minIndex!=i){
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
            // System.out.printf("第%d次排序:",i+1);
            // System.out.println(Arrays.toString(arr));
        }
    }

    private static int[] generateRandomArray(int len,int max){
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random()*max);
        }
        return arr;
    }
}