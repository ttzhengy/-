package 排序;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Date;

public class BubbleSortDemo {
    public static void main(String[] args) {
        int[] arr = generateRandomArray(80000,100000);
        // int[] arr = {1,2,3,4,5,6};
        long startTime = System.currentTimeMillis();
        // System.out.println(Arrays.toString(arr));
        BubbleSort(arr);
        long endTime = System.currentTimeMillis();
        // System.out.println(Arrays.toString(arr));
        System.out.println(endTime-startTime+ " " + (endTime-startTime)/1000);
    }

    private static void BubbleSort(int[] arr){
        int length = arr.length;
        boolean flag = false;
        for (int i = 0; i < length-1; i++) {
            for (int j = 0; j < length-i-1; j++) {
                if (arr[j]>arr[j+1]){
                    flag = true;
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
            // System.out.printf("第%d次排序:",i+1);
            // System.out.println(Arrays.toString(arr));
            if (!flag) {
                break;
            }else {
                flag=false; // 必须要重置，否则只要前面的循环中交换过一次，flag就一直是true
            }
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
