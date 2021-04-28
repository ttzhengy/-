package 排序;

import java.util.Arrays;

public class RadixSortDemo {
    public static void main(String[] args) {
        // int arr[] = { 53, 3, 542, 748, 14, 214};
        int[] arr = generateRandomArray(8000000, 8000000);
        // System.out.println(Arrays.toString(arr));
        long startTime = System.currentTimeMillis();
        RadixSort(arr);
        long endTime = System.currentTimeMillis();
        // System.out.println(Arrays.toString(arr));
        System.out.println(endTime-startTime+ " " + (endTime-startTime)/1000);
    }

    private static void RadixSort(int[] arr){
        int[][] bucketArr;
        int[] bucketIndex;
        // boolean flag = true;

        // 将数字转String来计算位数
        int max = arr[0];
        for (int a : arr) {
            if (a>max){
                max = a;
            }
        }
        int maxDigit = String.valueOf(max).length();

        for(int k = 0 , digit = 1; k < maxDigit; k++, digit*=10){
            bucketArr = new int[10][arr.length];
            bucketIndex = new int[10];
            for (int i = 0; i < arr.length; i++) {
                // 除以数位模10，得到具体数位的数字
                int digitOfEle = arr[i] / digit % 10;
                // 放入对应桶，对应桶的指针++
                bucketArr[digitOfEle][bucketIndex[digitOfEle]++]=arr[i];
            }

            // 从桶放回原数组
            int index = 0;
            for (int i = 0; i < bucketArr.length; i++) {
                for (int j = 0; j < bucketIndex[i]; j++) {
                    arr[index++] = bucketArr[i][j];
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
