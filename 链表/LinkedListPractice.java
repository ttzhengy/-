package 链表;

import java.util.List;
import java.util.Stack;

public class LinkedListPractice {
    public static void main(String[] args) {
        // SingleLinkedList LinkedList = new SingleLinkedList();
        // for (int i = 1; i <= 5; i++) {
        //     LinkedList.add(new HeroNode(i,"abc","cba"));
        // }
        // int length = getLength(LinkedList.getHead());
        // System.out.printf("链表长度为 %d\n",length);
        // LinkedList.list();

        // System.out.println(findLastIndexNode(LinkedList,11));

        // reverse(LinkedList);
        // LinkedList.list();

        // LinkedList.list();
        // reversePrint(LinkedList);

        SingleLinkedList L1 = new SingleLinkedList();
        SingleLinkedList L2 = new SingleLinkedList();
        for (int i = 1; i <= 5; i++) {
            L1.add(new HeroNode(i*2,"abc","cba"));
        }
        for (int i = 1; i <= 3; i++) {
            L2.add(new HeroNode(i*3,"def","fed"));
        }
        SingleLinkedList L3 = mergeTwoList(L1, L2);
        L3.list();
    }

    // 得到链表的总长度
    public static int getLength(HeroNode head){
        if (head.next == null){
            return 0;
        }
        HeroNode temp = head;
        int count = 0;
        while (temp.next != null){
            count++;
            temp = temp.next;
        }
        return count;
    }

    // 返回倒数第index个节点
    public static HeroNode findLastIndexNode(SingleLinkedList L,int index){
        HeroNode head = L.getHead();
        if (head.next == null){
            return null;
        }
        int length = getLength(head);
        HeroNode temp = head.next;
        if (index<0 || index>length){
            return null;
        }
        for (int i = 0; i < length-index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    // 反转单链表
    public static void reverse(SingleLinkedList L){
        HeroNode head = L.getHead();
        if (head.next == null || head.next.next == null){
            return;
        }
        int length = getLength(head);
        HeroNode reverseHead = new HeroNode(-1,"","");
        HeroNode temp = new HeroNode(-1,"","");
        for (int i = 0; i < length; i++) {
            temp.next = head.next;
            head.next = head.next.next;
            temp.next.next = reverseHead.next;
            reverseHead.next = temp.next;
        }
        head.next = reverseHead.next;
    }

    // 反转输出链表
    public static void reversePrint(SingleLinkedList L){
        HeroNode head = L.getHead();
        if (head.next == null){
            return;
        }
        Stack<HeroNode> stack = new Stack<>();
        HeroNode temp = head.next;
        while (temp != null){
            stack.add(temp);
            temp = temp.next;
        }
        while (!stack.isEmpty()){
            System.out.println(stack.pop());
        }
    }

    // 合并两个升序链表成一个升序链表
    public static SingleLinkedList mergeTwoList(SingleLinkedList L1,SingleLinkedList L2){
        HeroNode head1 = L1.getHead();
        HeroNode head2 = L2.getHead();
        if (head1.next == null){
            return L2;
        }
        if (head2.next == null){
            return L1;
        }
        HeroNode temp1 = head1.next;
        HeroNode temp2 = head2.next;
        SingleLinkedList returnL = new SingleLinkedList();
        HeroNode rTemp = returnL.getHead();

        // 其中一个链表为空之前，一直循环
        while (temp1 != null && temp2 != null){
            if (temp1.Number < temp2.Number){
                rTemp.next = temp1;
                temp1 = temp1.next;
                rTemp = rTemp.next;
            }else if (temp1.Number > temp2.Number){
                rTemp.next = temp2;
                temp2 = temp2.next;
                rTemp = rTemp.next;
            }else {
                rTemp.next = temp2;
                temp2 = temp2.next;
                temp1 = temp1.next;
                rTemp = rTemp.next;
            }
        }
        if (temp1 == null){
            rTemp.next = temp2;
        }else if (temp2 == null){
            rTemp.next = temp1;
        }
        return returnL;
    }
}
