package 查找;

import java.util.Arrays;

public class FibonacciSearchDemo {
    public static void main(String[] args) {
        int[] arr = {1,8,10,89,1000,1234};
        System.out.println(FibonacciSearch(arr,1));
    }

    private static int FibonacciSearch(int[] arr, int findVal){
        int low = 0;
        int high = arr.length-1;
        int k = 0;
        int mid = 0;

        // 创建斐波那契数组
        int[] f = fib(arr.length);
        while (high>f[k]-1){
            k++;
        }

        // 创建临时数组，使用斐波那契数列的长度
        int[] temp = Arrays.copyOf(arr,f[k]);
        for (int i = high+1; i < temp.length; i++) {
            temp[i] = arr[high];
        }

        while (low<=high){
            mid = low + f[k-1] - 1;
            // 当查找值小于分割点值，左侧数组长度为f(k-1)-1
            if (findVal<temp[mid]){
                high = mid-1;
                k--;
            // 当查找值大于分割点值，左侧数组长度为f(k-2)-1
            }else if (findVal> temp[mid]){
                low = mid+1;
                k-=2;
            }else {
                return Math.min(mid, arr.length - 1);
            }
        }
        if (arr[low]==findVal){
            return low;
        }
        return -1;
    }

    private static int[] fib(int maxLength){
        int[] f = new int[maxLength];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxLength; i++) {
            f[i] = f[i-1] + f[i-2];
        }
        return f;
    }
}
