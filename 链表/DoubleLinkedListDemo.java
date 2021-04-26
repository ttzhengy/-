package 链表;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        DoubleLinkedList List = new DoubleLinkedList();
        List.add(new DoubleHeroNode(1,"aaa","a"));
        List.add(new DoubleHeroNode(3,"ccc","c"));
        List.addByOrder(new DoubleHeroNode(2,"bbb","b"));
        // List.list();

        List.add(new DoubleHeroNode(4,"ddd","d"));
        List.add(new DoubleHeroNode(5,"eee","e"));
        List.delete(5);
        List.list();
    }
}

class DoubleLinkedList{
    //先新建头结点
    private DoubleHeroNode head = new DoubleHeroNode(-1,"","");

    public DoubleHeroNode getHead(){
        return head;
    }

    // 增
    public void add(DoubleHeroNode N){
        DoubleHeroNode temp = head;
        while (temp.next!=null){
            temp = temp.next;
        }
        temp.next = N;
        // 双向链表新增
        N.pre = temp;
    }

    public void addByOrder(DoubleHeroNode N){
        DoubleHeroNode temp = head;
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
            // 双向链表新增
            N.next.pre = N;
            temp.next = N;
            // 双向链表新增
            N.pre = temp;
        }
    }

    // 删
    public void delete(int num){
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        DoubleHeroNode temp = head.next;
        boolean flag = false;
        while (true){
            // 下面的判断标准改了，此处也要改
            if(temp == null){
                break;
            }
            // 可以自我删除，此处改动
            if (temp.Number == num){
                temp.pre.next = temp.next;
                // 考虑temp.next为空的情况
                if (temp.next != null){
                    temp.next.pre = temp.pre;
                }
                System.out.printf("已删除编号为 %d 的节点\n",num);
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (!flag){
            System.out.printf("找不到编号为 %d 的节点\n",num);
        }
    }

    // 修改指定节点内容
    public void update(DoubleHeroNode N){
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }

        DoubleHeroNode temp = head.next;
        boolean flag = false;
        while (true){
            if (temp == null){
                break;
            }
            if (temp.Number == N.Number){
                flag = true;
                temp.name = N.name;
                temp.nickname = N.nickname;
                System.out.println("修改成功");
                break;
            }
            temp = temp.next;
        }
        if (!flag){
            System.out.printf("找不到编号为 %d 的节点\n",N.Number);
        }

    }

    public void list(){
        //判断为空
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }

        DoubleHeroNode temp = head;
        while (temp.next!=null){
            temp = temp.next;
            System.out.println(temp);
        }
    }
}

class DoubleHeroNode{
    public int Number;
    public String name;
    public String nickname;
    public DoubleHeroNode next;
    public DoubleHeroNode pre;

    public DoubleHeroNode(int no, String n, String nick_n){
        Number = no;
        name = n;
        nickname = nick_n;
    }

    @Override
    public String toString() {
        return "DoubleHeroNode{" +
                "Number=" + Number +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
