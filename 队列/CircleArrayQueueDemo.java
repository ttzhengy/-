package 队列;

import java.util.Scanner;

public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        CircleArrQueue arr = new CircleArrQueue(5);
        //arr.pop();
        Scanner scan = new Scanner(System.in);
        char key = ' ';
        Boolean loop = true;
        while (loop) {
            System.out.println("s：显示队列");
            System.out.println("e：退出程序");
            System.out.println("a：添加数据");
            System.out.println("p：从队列取出数据");
            System.out.println("h：查看队列头");
            key = scan.next().charAt(0);
            switch (key) {
                case 's':
                    try {
                        arr.showQueue();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'a':
                    System.out.println("输入一个数");
                    int value = scan.nextInt();
                    arr.add(value);
                    break;
                case 'p':
                    try {
                        int value1 = arr.pop();
                        System.out.printf("取出一个值：%d", value1);
                        System.out.println();
                    } catch (Exception e) {
                        //e.printStackTrace();
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int value2 = arr.peek();
                        System.out.printf("队列头的值：%d", value2);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scan.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
    }
}

class CircleArrQueue{
    private int maxSize;
    private int front;  //队头
    private int rear;   //队尾
    private int[] arr;

    public CircleArrQueue(int max_Size){
        maxSize = max_Size;
        arr = new int[maxSize];
        front = 0;
        rear = 0;
    }

    public boolean isFull(){
        return (rear + 1) % maxSize == front;
    }

    public boolean isEmpty(){
        return front == rear;
    }

    public void add(int n){
        if (isFull()){
            System.out.println("队列满");
            return;
        }
        arr[rear] = n;
        rear = (rear + 1) % maxSize;
    }

    public int pop(){
        if (isEmpty()){
            throw new RuntimeException("队列空");
        }
        int a = arr[front];
        front = (front + 1) % maxSize;
        return a;
    }

    public void showQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列空");
        }
        for (int i = front;i < size();i++) {
            System.out.printf("arr[%d]=%d\t",i%maxSize,arr[i%maxSize]);
        }
        System.out.println();
    }

    public int size(){
        return (rear+maxSize-front)%maxSize;
    }

    public int peek(){
        if (isEmpty()){
            throw new RuntimeException("队列空");
        }
        return arr[front];
    }
}