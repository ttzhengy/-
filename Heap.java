
import java.util.*;

public class Heap {
    int[] heap = null;
    int maxSize ;
    int size = 0;

    public Heap(int s){
        maxSize = s;
        heap = new int[maxSize+1];
        heap[0] = 0;                        //初始化数组的第一项为0
    }

    public Heap(int[] a, int s){
        maxSize = s;
        heap = new int[maxSize+1];
        for (int i = 1;i<a.length+1 && i<maxSize;i++) {
            heap[i] = a[i-1];
        }
        heap[0] = 0;
    }

    public boolean isFull(){
        int last = heap[maxSize];           //判断堆的最后一项是否为0
        return last != 0;
    }

    public boolean isEmpty(){
        return heap[1] == 0;                //判断堆的第一项是否为0
    }

    public void insert(int a){
        if(isFull()){                       //堆已满则跳出
            System.out.println("Heap is full");
            return;
        }
        heap[++size] = a;
        int cur = size;
        for(;heap[cur/2]<a && cur != 1;cur/=2){     //若插入元素比父节点的元素大，则将父节点元素移至当前节点
            heap[cur] = heap[cur/2];
        }
        heap[cur] = a;          //插入元素比父节点元素小，或已达到根节点
    }

    public int delete(){
        int pop = heap[1];
        int parent = 1;
        int child = 2*parent;
        if(isEmpty()){
            return 0;
        }
        int temp = heap[size];      //选取堆中最后一项，移至堆顶
        while(child <= size){
            if(child != size && heap[child+1]>heap[child]){     //选择较大的子节点
                child++;
            }
            if(temp > heap[child]){
                break;
            }else{
                heap[parent] = heap[child];     //父节点比子节点大则停止循环，否则将两节点交换
            }
            parent = child;
            child *= 2;
        }
        heap[parent] = temp;
        size--;
        return pop;
    }

    public void printHeap(){
        for (int i : heap) {
            System.out.print(i + " ");
        }
    }

    public static void main(String[] args) {
        int max = 10;
        Heap H = new Heap(max);
        }
    }
}
