package 队列;
import java.util.Scanner;

//当前问题：当数组弹出数据后，前面的数组空间不可被重复利用
//解决方法：环形队列

public class ArrQueueDemo {
    public static void main(String[] args) {
        ArrQueue arr = new ArrQueue(5);
        //arr.pop();
        Scanner scan = new Scanner(System.in);
        char key = ' ';
        Boolean loop = true;
        while (loop){
            System.out.println("s：显示队列");
            System.out.println("e：退出程序");
            System.out.println("a：添加数据");
            System.out.println("p：从队列取出数据");
            System.out.println("h：查看队列头");
            key = scan.next().charAt(0);
            switch(key){
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
                        System.out.printf("取出一个值：%d",value1);
                        System.out.println();
                    } catch (Exception e) {
                        //e.printStackTrace();
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int value2 = arr.peek();
                        System.out.printf("队列头的值：%d",value2);
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

class ArrQueue{
    private int maxSize;
    private int front;  //队头
    private int rear;   //队尾
    private int[] arr;

    public ArrQueue(int max_Size){
        maxSize = max_Size;
        arr = new int[maxSize];
        front = -1;
        rear = -1;
    }

    public boolean isFull(){
        return rear == maxSize-1;
    }

    public boolean isEmpty(){
        return front == rear;
    }

    public void add(int n){
        if (isFull()){
            System.out.println("队列满");
            return;
        }
        arr[++rear] = n;
    }

    public int pop(){
        if (isEmpty()){
            throw new RuntimeException("队列空");
        }
        return arr[++front];
    }

    public void showQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列空");
        }
        int index = 0;
        for (int i = front+1;i < rear+1;i++) {
            System.out.printf("arr[%d]=%d\t",index++,arr[i]);
        }
        System.out.println();
    }

    public int peek(){
        if (isEmpty()){
            throw new RuntimeException("队列空");
        }
        return arr[front+1];
    }
}
