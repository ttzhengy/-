package 排序;
import java.util.*;

public class QuickSortDemo {
    public static void main(String[] args) {
        // int[] arr = {2,1,4,3,5,6};
        int[] arr = generateRandomArray(8000000, 8000000);
        // System.out.println(Arrays.toString(arr));
        long startTime = System.currentTimeMillis();
        QuickSort(arr,0,arr.length-1);
        long endTime = System.currentTimeMillis();
        // System.out.println(Arrays.toString(arr));
        System.out.println(endTime-startTime+ " " + (endTime-startTime)/1000);
    }

    private static void QuickSort(int[] arr, int leftIndex, int rightIndex){
        // 防止left=right的情况下无限递归
        if (leftIndex<rightIndex){
            int left = leftIndex;
            int right = rightIndex;
            int pivot = arr[left];
            while (left<right) {
                // 从右向左找比pivot小的数
                while (left < right && arr[right] > pivot) {
                    right--;
                }
                if (left < right) {
                    arr[left++] = arr[right];
                }
                // 从左到右找pivot大的数
                while (left < right && arr[left] < pivot) {
                    left++;
                }
                if (left < right) {
                    arr[right--] = arr[left];
                }
            }
            arr[left] = pivot;
            QuickSort(arr,leftIndex,left-1);
            QuickSort(arr,left+1,rightIndex);
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

