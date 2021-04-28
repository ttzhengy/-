package 排序;

import java.util.Arrays;

public class ShellSortDemo {
    public static void main(String[] args) {
        // int[] arr = {2,1,4,3,5,6};

        int[] arr = generateRandomArray(800000, 8000000);
        // System.out.println(Arrays.toString(arr));
        long startTime = System.currentTimeMillis();
        ShellSort(arr);
        long endTime = System.currentTimeMillis();
        // System.out.println(Arrays.toString(arr));
        System.out.println(endTime-startTime+ " " + (endTime-startTime)/1000);
    }

    private static void ShellSort(int[] arr){
        int arrLength = arr.length;
        int insertVal;
        int insertIndex;
        for (int i = arrLength/2; i >= 1; i/=2) {
            for (int j = i; j < arrLength; j++) {
                insertVal = arr[j];
                insertIndex = j-i;
                while (insertIndex>=0 && insertVal<arr[insertIndex]){
                    arr[insertIndex+i] = arr[insertIndex];
                    insertIndex-=i;
                }
                if (insertIndex+i != j){
                    arr[insertIndex+i]=insertVal;
                }
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
