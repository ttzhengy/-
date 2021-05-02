package 查找;

public class InsertValueSearchDemo {
    public static void main(String[] args) {
        int [] arr = new int[100];
        for(int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }
        int index = insertValueSearch(arr, 1,0, arr.length - 1);
        System.out.println("index = " + index);

    }

    private static int insertValueSearch(int[] arr, int findVal, int left, int right){
        // 跳出递归条件
        if (left>right){
            return -1;
        }
        System.out.println("search");
        int mid = left + (findVal - arr[left])/(arr[right] - arr[left]) * (right - left);
        if (findVal < arr[mid]){
            return insertValueSearch(arr,findVal,left,mid-1);
        }else if (findVal > arr[mid]){
            return insertValueSearch(arr,findVal,mid+1,right);
        }else {
            return mid;
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
