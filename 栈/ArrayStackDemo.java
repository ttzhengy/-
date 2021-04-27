package 栈;

import java.util.Scanner;

public class ArrayStackDemo {
    public static void main(String[] args) {
        // ArrayStack<String> stack = new ArrayStack<>(5);
        LinkedListStack<String> stack = new LinkedListStack<>();
        String key = "";
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);

        while (loop){
            System.out.println("show：显示栈");
            System.out.println("exit：退出程序");
            System.out.println("push：入栈");
            System.out.println("pull：入栈");
            System.out.println("输入你的选择");
            key = scanner.next();
            switch(key){
                case "show":
                    stack.list();
                    break;
                case "exit":
                    loop = false;
                    scanner.close();
                    break;
                case "push":
                    System.out.println("请输入要入栈的值");
                    String value = scanner.next();
                    stack.push(value);
                    break;
                case "pull":
                    try {
                        System.out.println(stack.pull());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                default:
                    break;
            }
        }
    }
}

class ArrayStack<E>{
    private int maxSize;
    private E[] stack;
    private int top = -1;

    public ArrayStack(int maxSize){
        this.maxSize = maxSize;
        stack = (E[]) new Object[this.maxSize];
    }

    public void push(E value){
        if (isFull()){
            System.out.println("栈已满");
        }else {
            stack[++top] = value;
        }
    }

    public E pull(){
        if (isEmpty()){
            throw new RuntimeException("栈已空");
        }else {
            return stack[top--];
        }
    }

    public void list(){
        if (isEmpty()){
            System.out.println("栈空");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%s\n",i,stack[i]);
        }
    }

    public boolean isFull(){
        return top == maxSize - 1;
    }

    public boolean isEmpty(){
        return top == -1;
    }
}

// 链表实现栈
class LinkedListStack<E>{

    static class Node<E>{
        private E data;
        private Node<E> next;

        public Node(E data){
            this.data = data;
            this.next = null;
        }

    }

    // 用top节点表示栈顶
    private Node<E> top;

    public void push(E data){
        Node<E> node = new Node<>(data);
        if (top == null){
            top = node;
        }else {
            // 头插法
            node.next = top;
            top = node;
        }
    }

    public E pull(){
        if (isEmpty()){
            throw new RuntimeException("栈空");
        }
        E data = top.data;
        top = top.next;
        return data;
    }

    public void list(){
        if (isEmpty()){
            System.out.println("栈空");
        }
        Node<E> temp = top;
        while (temp != null){
            System.out.println(temp.data);
            temp =temp.next;
        }
    }

    public boolean isEmpty(){
        return top == null;
    }
}