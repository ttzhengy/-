package 哈希表;

public class HashTabDemo {
    public static void main(String[] args) {
        HashTab hashTab = new HashTab(5);
        for (int i = 0; i < 10; i++) {
            hashTab.add(new EmpNode(i,"abc","abc"));
        }
        try {
            hashTab.find(11);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

class HashTab{
    private EmpLinkedList[] empLinkedLists;
    private int size;

    public HashTab(int s){
        size = s;
        empLinkedLists = new EmpLinkedList[size];
        for (int i = 0; i < size; i++) {
            empLinkedLists[i] = new EmpLinkedList();
        }
    }

    public void add(EmpNode node){
        int empLinkedListNo = hash(node.getId());
        empLinkedLists[empLinkedListNo].add(node);
    }

    public void list(){
        for (int i = 0; i < empLinkedLists.length; i++) {
            empLinkedLists[i].list(i);
        }
    }

    public EmpNode find(int id){
        int empLinkedListNo = hash(id);
        return empLinkedLists[empLinkedListNo].find(id);
    }

    public void delete(int id){
        int empLinkedListNo = hash(id);
        empLinkedLists[empLinkedListNo].delete(id);
    }

    public int hash(int id){
        return id%size;
    }
}

class EmpLinkedList{
    private EmpNode head = new EmpNode(-1,"","");

    public void add(EmpNode node){
        if (isEmpty()){
            head.next = node;
            return;
        }
        EmpNode temp = head.next;
        while (temp.next != null){
            temp = temp.next;
        }
        temp.next = node;
    }

    public void list(int No){
        if (isEmpty()){
            System.out.printf("第%d链表为空\n",No+1);
            return;
        }
        EmpNode temp = head;
        System.out.printf("第%d链表信息为\n",No+1);
        while (temp.next!=null){
            temp = temp.next;
            System.out.println(temp);
        }
    }

    public EmpNode find(int id){
        if (isEmpty()){
            throw new RuntimeException("链表为空");
        }
        EmpNode temp = head;
        while (temp.next!=null){
            if (temp.getId()==id){
                return temp;
            }
            temp = temp.next;
        }
        throw new RuntimeException("找不到对应Emp");
    }

    public void delete(int id){
        if (isEmpty()){
            throw new RuntimeException("链表为空");
        }
        EmpNode temp = head;
        while (temp.next!=null){
            if (temp.getId()==id){
                temp.next = temp.next.next;
            }
            temp = temp.next;
        }
        throw new RuntimeException("找不到对应Emp");
    }

    public boolean isEmpty(){
        return head.next == null;
    }
}

class EmpNode{
    private int id;
    private String name;
    private String address;
    public EmpNode next;

    public EmpNode(int id, String name, String add){
        this.id = id;
        this.name = name;
        this.address = add;
    }

    @Override
    public String toString() {
        return "EmpNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

