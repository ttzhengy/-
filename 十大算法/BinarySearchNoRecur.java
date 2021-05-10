package 十大算法;

public class BinarySearchNoRecur {
    public static void main(String[] args) {
        int[] arr = {1,3,8,10,11,67,100};
        arr = generateOrderedArray(100,1000);
        int target = 0;
        System.out.println(BinarySearch(arr,target));
    }

    public static int BinarySearch(int[] arr,int i){
        int left = 0;
        int right = arr.length-1;
        int mid = left+(right-left)/2;
        while (right>=left){
            if (arr[mid]==i){
                return mid;
            }else if (arr[mid]>i){
                right = mid-1;
            }else {
                left = mid+1;
            }
            mid = left+(right-left)/2;
        }
        return -1;
    }

    private static int[] generateOrderedArray(int len,int max) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * max);
            int insertVal;
            int insertIndex;
            for (int j = (i + 1) / 2; j >= 1; j /= 2) {
                for (int k = j; k < (i + 1); k++) {
                    insertVal = arr[k];
                    insertIndex = k - j;
                    while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                        arr[insertIndex + j] = arr[insertIndex];
                        insertIndex -= j;
                    }
                    if (insertIndex + j != k) {
                        arr[insertIndex + j] = insertVal;
                    }
                }
            }
        }
        return arr;
    }
}
