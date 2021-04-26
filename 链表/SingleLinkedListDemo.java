package 链表;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        SingleLinkedList LinkedList = new SingleLinkedList();
        //LinkedList.add(new HeroNode(1,"aaa","a"));
        //LinkedList.add(new HeroNode(2,"bbb","b"));
        //LinkedList.add(new HeroNode(3,"ccc","c"));
        //LinkedList.add(new HeroNode(4,"ddd","d"));

        LinkedList.addByOrder(new HeroNode(1,"aaa","a"));
        LinkedList.addByOrder(new HeroNode(4,"ddd","d"));
        LinkedList.addByOrder(new HeroNode(2,"bbb","b"));
        LinkedList.addByOrder(new HeroNode(3,"ccc","c"));
        LinkedList.list();
    }
}

class SingleLinkedList{
    //先新建头结点
    private HeroNode head = new HeroNode(0,"","");

    public void add(HeroNode N){
        HeroNode temp = head;
        while (temp.next!=null){
            temp = temp.next;
        }
        temp.next = N;
    }

    public void addByOrder(HeroNode N){
        HeroNode temp = head;
        boolean flag = false;   //预添加的编号是否存在
        while (temp.next!=null){
            if(temp.next.Number>N.Number){   //插入节点比next节点小。当前位置应该插入
                break;
            }else if (temp.Number==N.Number){   //已有相同节点
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag){
            System.out.printf("当前编号%d已存在",N.Number);
        }else { //满足条件再插入
            N.next = temp.next;
            temp.next = N;
        }
    }

    public void list(){
        //判断为空
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }

        HeroNode temp = head;
        while (temp.next!=null){
            System.out.println(temp);
            temp = temp.next;
        }
        System.out.println(temp);
    }
}

class HeroNode{
    public int Number;
    public String name;
    public String nickname;
    public HeroNode next;

    public HeroNode(int no, String n, String nick_n){
        Number = no;
        name = n;
        nickname = nick_n;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "Number=" + Number +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
