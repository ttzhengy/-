package 链表;

public class CycleLinkedList {
    public static void main(String[] args) {
        CycleLinkerList List = new CycleLinkerList();
        List.addBoy(10);
        List.Josephus(1,7);

    }
}

class CycleLinkerList{
    private Boy first = null;
    private int boyNum;

    public Boy getHead(){
        return first.next;
    }

    public void addBoy(int n){
        if (n<1){
            System.out.println("输入的值不正确");
        }
        boyNum = n;
        Boy cur = null;
        for (int i = 1; i <= n; i++) {
            Boy boy = new Boy(i);
            if (i == 1){
                first = boy;
                first.next = first;
                cur = boy;
            }else {
                cur.next = boy;
                cur = boy;
                cur.next = first;
            }
        }
    }

    /**
     *
     * @param startNum 从第几个开始数
     * @param countNum 数几下出队
     */
    public void Josephus(int startNum,int countNum){
        if (first == null || startNum < 1 || startNum > boyNum){
            System.out.println("输入参数错误");
        }
        // 将first移动到开始报数的人上
        for (int i = 0; i < startNum-1; i++) {
            first = first.next;
        }
        // 设置helper指针，并移动到first前一位
        Boy helper = first;
        while (!helper.next.equals(first)){
            helper = helper.next;
        }

        // 开始出圈
        int count = 1;
        while (first != null){
            if (first.equals(helper)){
                break;
            }
            for (int i = 0; i < countNum-1; i++) {
                first = first.next;
                helper = helper.next;
            }
            Boy out = first;
            first = first.next;
            helper.next = first;
            System.out.printf("第 %d 个出队的是",count++);
            System.out.println(out);
        }
        System.out.println("最后留在圈中的是"+first);
    }

    public void list(){
        if (first == null){
            System.out.println("链表为空");
            return;
        }
        Boy cur = first;
        while (true){
            System.out.println(cur);
            if (first.equals(cur.next)){
                break;
            }
            cur = cur.next;
        }
    }
}

class Boy{
    private int num;
    public Boy next;
    
    public Boy(int n){
        this.num = n;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "num=" + num +
                '}';
    }
}
