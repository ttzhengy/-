package 树;

import java.util.Arrays;

public class HeapSortDemo {
    public static void main(String[] args) {
        int[] arr = generateRandomArray(8000000, 8000000);
        Heap heap = new Heap(arr);
        long startTime = System.currentTimeMillis();
        heap.heapSort();
        long endTime = System.currentTimeMillis();
        // System.out.println(Arrays.toString(arr));
        System.out.println(endTime-startTime+ " " + (endTime-startTime)/1000);
    }

    private static int[] generateRandomArray(int len,int max){
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random()*max);
        }
        return arr;
    }
}

class Heap{
    int[] arr;

    public Heap(int[] arr){
        this.arr = arr;
    }

    public void heapSort(){
        int temp;
        // 对一个大小为arr.length的树，其非叶子节点的数量最多为arr.length/2-1
        // 令所有非叶子节点下沉至合适位置，变为大顶堆
        for (int i = arr.length/2-1; i >= 0; i--) {
            // System.out.println(Arrays.toString(arr));
            adjustHeap(arr,i,arr.length);
        }

        for (int i = arr.length-1; i > 0; i--) {
            temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            adjustHeap(arr,0,i);
        }
    }

    /**
     * 将序列i对应的非叶子节点的树调整成大顶堆，令i节点下沉到合适位置
     * @param arr 待调整数组
     * @param i 非叶节点在数组中的索引
     * @param length 对多少个元素进行调整
     */
    private void adjustHeap(int[] arr, int i, int length){
        int temp = arr[i];
        // k是从i开始的每个左子节点
        for (int k = 2*i+1; k < length; k=2*k+1) {
            // 如果右子节点存在且大于左子节点，则将k转至右子节点
            if (k+1<length && arr[k]<arr[k+1]){
                k++;
            }
            // 将较大的子节点上移。若两个子节点都比父节点小，则跳出循环
            if (arr[k]>temp){
                arr[i] = arr[k];
                // i指向子节点
                i = k;
            }else {
                break;
            }
        }
        // 此时i指向最后一个上移的子节点。将一开始的非叶子节点的值放入该位置
        arr[i] = temp;
    }
}
