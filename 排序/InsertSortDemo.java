package 排序;

import java.util.Arrays;

public class InsertSortDemo {
    public static void main(String[] args) {
        // int[] arr = {2,1};

        int[] arr = generateRandomArray(800000, 8000000);
        // System.out.println(Arrays.toString(arr));
        long startTime = System.currentTimeMillis();
        InsertSort(arr);
        long endTime = System.currentTimeMillis();
        // System.out.println(Arrays.toString(arr));
        System.out.println(endTime-startTime+ " " + (endTime-startTime)/1000);
    }

    private static void InsertSort(int[] arr){
        int length = arr.length;
        int insertVal;
        int insertIndex;
        for (int i = 1; i < length; i++) {
            insertVal = arr[i];
            insertIndex = i-1;
            // 从有序列表的开始比较，比当前位置小则把当前位置后移
            while (insertIndex>=0 && insertVal<arr[insertIndex]){
                arr[insertIndex+1] = arr[insertIndex];
                insertIndex--;
            }
            if (++insertIndex!=i){
                arr[insertIndex] = insertVal;
            }
            // for (int j = i-1; j >= 0; j--) {
            //     if (insert<arr[j]){
            //         arr[j+1] = arr[j];
            //         arr[j] = insert;
            //     }else {
            //         break;
            //     }
            // }
            // System.out.printf("第%d次排序:",i);
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
