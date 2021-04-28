package 排序;

import java.util.Arrays;

public class MergeSortDemo {
    public static void main(String[] args) {
        // int[] arr = {2,1,4,3,5,6};
        int[] arr = generateRandomArray(8000000, 8000000);
        int[] temp = new int[arr.length];
        // System.out.println(Arrays.toString(arr));
        long startTime = System.currentTimeMillis();
        MergeSort(arr,0,arr.length-1,temp);
        long endTime = System.currentTimeMillis();
        // System.out.println(Arrays.toString(arr));
        System.out.println(endTime-startTime+ " " + (endTime-startTime)/1000);
    }

    private static void MergeSort(int[] arr,int left,int right,int[] temp){
        if (left < right){
            int mid = (left+right)/2;
            // 分
            MergeSort(arr,left,mid,temp);
            MergeSort(arr,mid+1,right,temp);
            // 治
            Merge(arr,left,mid,right,temp);
        }
    }

    private static void Merge(int[] arr,int left,int mid,int right,int[] temp){
        int tempIndex = left;
        int i = left;
        int j = mid+1;
        // 两个数组的元素根据大小放入临时数组
        while (i<=mid && j<=right){
            if (arr[i]<arr[j]){
                temp[tempIndex++] = arr[i];
                i++;
            }else {
                temp[tempIndex++] = arr[j];
                j++;
            }
        }
        // 将未放完的数组全部装入临时数组
        while (i<=mid){
            temp[tempIndex++] = arr[i++];
        }
        while (j<=right){
            temp[tempIndex++] = arr[j++];
        }

        // 将合并完的临时数组装入原数组的对应部分
        int tempLeft=left;
        while (tempLeft<=right){
            arr[tempLeft] = temp[tempLeft];
            tempLeft++;
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
