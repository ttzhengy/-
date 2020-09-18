import java.util.*;

public class Polynomial {
    public static void main(String[] args) {
        // Linked L1 = new Linked();
        // Linked L2 = new Linked();
        // L1.createPoly();
        // L2.createPoly();
        // PloyItem AAA = new PloyItem();
        // Linked L3 = AAA.addPolyItem(L1, L2);
        // System.out.println(L3.toString()); 

        Linked L1 = new Linked();
        L1.createPoly();
        // L1.unionPloy();
        L1.sort();
        System.out.println(L1.toString()); 
    }
}

class Linked{
    private int size = 0;
    Node head = null;
    

    class Node{
        int coef;
        int time;
        Node next = null;

        public Node(int c,int t) {
            coef = c;
            time = t;
        }

        public Node(int c,int t, Node n) {
            coef = c;
            time = t;
            next = n;
        }
    }

    public void add(int c, int t){
        Node newNode = new Node(c, t);
        if(head == null){
            head = newNode;
        }else{
            Node pre = head; 
            while(pre.next != null){
                pre = pre.next;
            }
            pre.next = newNode;
        }
        size++;
    }

    public void createPoly(){
        Scanner sc = new Scanner(System.in);
        int length = sc.nextInt();
        while(length-- != 0){
            this.add(sc.nextInt(), sc.nextInt());
        }
    }

    public String toString(){
        String s = "p(x)=";
        Node p = head;
        while(p != null){
            s = s + p.coef + "x^" + p.time + "+";
            p = p.next;
        }
        return s;
    }

    public void unionPloy(){
        Node p = head;
        while(p != null){
            Node q = p;
            while(q.next != null){
                if(p.time == q.next.time){
                    p.coef += q.next.coef;
                    q.next = q.next.next;
                }else{
                    q = q.next;
                }
            }
            p = p.next;
        }
    }

    public void sort(){
        Node p = head;
        Node q;
        int temp_c, temp_t;
        while(p != null){
            q = p.next;
            while(q != null){
                if(q.time > p.time){
                    temp_t = q.time;
                    temp_c = q.coef;
                    q.time = p.time;
                    q.coef = p.coef;
                    p.time = temp_t;
                    p.coef = temp_c;
                }
                q = q.next;
            }
            p = p.next;
        }
    }
}

class PloyItem{

    Linked sum = new Linked();
    Linked mul = new Linked();

    public Linked addPolyItem(Linked L1, Linked L2){
        Linked.Node p = L1.head;
        Linked.Node q = L2.head;
        
        while(p != null || q != null){
            if (p != null && q != null){
                if(p.time == q.time){
                    sum.add(p.coef + q.coef, p.time);
                    p = p.next;
                    q = q.next;
                }else if(p.time > q.time){
                    sum.add(p.coef, p.time);
                    p = p.next;
                }else{
                    sum.add(q.coef, q.time);
                    q = q.next;
                }
            }
            while(p != null){
                sum.add(p.coef, p.time);
                p = p.next;
            }
            while(q != null){
                sum.add(q.coef, q.time);
                q = q.next;
            }
        }
        return sum;
    }

    public Linked mulPolyItem(Linked L1, Linked L2){
        Linked.Node p = L1.head;
        Linked.Node q = L2.head;

        while(p != null){
            q = L2.head;
            while(q != null){
                mul.add(p.coef*q.coef, p.time+q.time);
                q = q.next;
            }
            p = p.next;
        }
        mul.unionPloy();
        return mul;
    }
}
