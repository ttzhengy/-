package 查找;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BinarySearchDemo {
    public static void main(String[] args) {
        // int[] arr = generateOrderedArray(10,100);
        // System.out.println(Arrays.toString(arr));
        //
        // int findVal = 4;
        // int index = binarySearch(arr, findVal, 0, arr.length - 1);
        // if (index==-1){
        //     System.out.printf("找不到数组中数字为%d的数\n",findVal);
        // }else {
        //     System.out.printf("数组中数字为%d的数下标是%d\n",findVal,index);
        // }

        int[] arr = { 1, 8, 10, 89, 1000, 1000, 1000, 1234 };
        List<Integer> resIndexList = binarySearch2(arr, 1000, 0, arr.length - 1);
        System.out.println("resIndexList=" + resIndexList);
    }

    // 只能返回数组中的单个findVal索引
    private static int binarySearch(int[] arr, int findVal, int left, int right){
        // 跳出递归条件
        if (left>right){
            return -1;
        }
        int mid = (left+right)/2;
        if (findVal < arr[mid]){
            return binarySearch(arr,findVal,left,mid-1);
        }else if (findVal > arr[mid]){
            return binarySearch(arr,findVal,mid+1,right);
        }else {
            return mid;
        }
    }

    // 返回索引数组
    private static List<Integer> binarySearch2(int[] arr, int findVal, int left, int right){
        // 跳出递归条件
        if (left>right){
            return new ArrayList<Integer>();
        }
        int mid = (left+right)/2;
        if (findVal < arr[mid]){
            return binarySearch2(arr,findVal,left,mid-1);
        }else if (findVal > arr[mid]){
            return binarySearch2(arr,findVal,mid+1,right);
        }else {
            List<Integer> resIndexList = new ArrayList<>();
            int temp = mid-1;
            while (temp>0 && arr[temp] == findVal){
                resIndexList.add(temp--);
            }
            resIndexList.add(mid);
            temp = mid+1;
            while (temp<arr.length-1 && arr[temp] == findVal){
                resIndexList.add(temp++);
            }
            return resIndexList;
        }
    }

    private static int[] generateOrderedArray(int len,int max){
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random()*max);
            int insertVal;
            int insertIndex;
            for (int j = (i+1)/2; j >=1 ; j/=2) {
                for (int k = j; k < (i+1); k++) {
                    insertVal = arr[k];
                    insertIndex = k-j;
                    while (insertIndex>=0 && insertVal<arr[insertIndex]){
                        arr[insertIndex+j] = arr[insertIndex];
                        insertIndex-=j;
                    }
                    if (insertIndex+j != k){
                        arr[insertIndex+j]=insertVal;
                    }
                }

            }
        }
        return arr;
    }
}
