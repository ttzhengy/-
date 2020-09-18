
import java.util.*;

public class Heap {
    int[] heap = null;
    int maxSize ;
    int size = 0;

    public Heap(int s){
        maxSize = s;
        heap = new int[maxSize+1];
        heap[0] = 0;
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
        int last = heap[maxSize];
        return last != 0;
    }

    public boolean isEmpty(){
        return heap[1] == 0;
    }

    public void insert(int a){
        if(isFull()){
            System.out.println("Heap is full");
            return;
        }
        heap[++size] = a;
        int cur = size;
        for(;heap[cur/2]<a && cur != 1;cur/=2){
            heap[cur] = heap[cur/2];
        }
        heap[cur] = a;
    }

    public int delete(){
        int pop = heap[1];
        int parent = 1;
        int child = 2*parent;
        if(isEmpty()){
            return 0;
        }
        int temp = heap[size];
        while(child <= size){
            if(child != size && heap[child+1]>heap[child]){
                child++;
            }
            if(temp > heap[child]){
                break;
            }else{
                heap[parent] = heap[child];
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
        Scanner sc = new Scanner(System.in);
        while(max-- >= 0){
            H.insert(sc.nextInt());
            H.printHeap();
        }
    }
}
