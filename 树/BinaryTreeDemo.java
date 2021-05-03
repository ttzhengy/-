package 树;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();

        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");

        // 说明，我们先手动创建该二叉树，后面我们学习递归的方式创建二叉树
        binaryTree.setRoot(root);
        root.setLeft(node2);
        root.setRight(node3);
        node3.setLeft(node4);
        node3.setRight(node5);

        // binaryTree.preOrderSearch(8);
        // binaryTree.infixOrderSearch(5);
        // binaryTree.postOrderSearch(2);

        binaryTree.delete(5);
        binaryTree.preOrder();
        binaryTree.delete(3);
        binaryTree.preOrder();
    }
}

class BinaryTree{
    private HeroNode root;

    public void setRoot(HeroNode root){
        this.root = root;
    }

    public void delete(int no){
        if (root!=null){
            if (root.getNo()==no){
                root=null;
            }
            root.deleteNode(no);
        }else {
            System.out.println("二叉树为空");
        }
    }

    public void preOrder(){
        if (root!=null){
            System.out.println("前序");
            root.preOrder();
        }else {
            System.out.println("二叉树为空");
        }
    }

    public void preOrderSearch(int no){
        if (root!=null){
            System.out.println("前序");
            System.out.println(root.preOrderSearch(no));
        }else {
            System.out.println("二叉树为空");
        }
    }

    public void infixOrder(){
        if (root!=null){
            System.out.println("中序");
            root.infixOrder();
        }else {
            System.out.println("二叉树为空");
        }
    }

    public void infixOrderSearch(int no){
        if (root!=null){
            System.out.println("中序");
            System.out.println(root.infixOrderSearch(no));
        }else {
            System.out.println("二叉树为空");
        }
    }

    public void postOrder(){
        if (root!=null){
            System.out.println("后序");
            root.postOrder();
        }else {
            System.out.println("二叉树为空");
        }
    }

    public void postOrderSearch(int no){
        if (root!=null){
            System.out.println("后序");
            System.out.println(root.postOrderSearch(no));
        }else {
            System.out.println("二叉树为空");
        }
    }
}

class HeroNode{
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public void deleteNode(int no){
        // 判断左子节点，符合则删除并结束递归
        if (left!=null && left.no==no){
            if (left.left!=null){
                left = left.left;
            }else if (left.right!=null){
                left = left.right;
            }else {
                left = null;
            }
            return;
        }
        // 判断右子节点，符合则删除并结束递归
        if (right!=null && right.no==no){
            if (right.left!=null){
                right = right.left;
            }else if (right.right!=null){
                right = right.right;
            }else {
                right = null;
            }
            return;
        }
        if (left!=null){
            left.deleteNode(no);
        }
        if (right!=null){
            right.deleteNode(no);
        }
    }

    public void preOrder(){
        System.out.println(this);
        if (left!=null){
            left.preOrder();
        }
        if (right!=null){
            right.preOrder();
        }
    }

    public HeroNode preOrderSearch(int no){
        // 前序，先判断父节点，当前节点符合则返回
        System.out.println("前序查找");
        if (this.no==no){
            return this;
        }

        HeroNode temp = null;
        // 递归左子树，子树中有符合的节点则返回
        if (left!=null){
            temp = left.preOrderSearch(no);
        }
        if (temp!=null){
            return temp;
        }

        // 否则递归右子树
        if (right!=null){
            temp = right.preOrderSearch(no);
        }
        return temp;
    }

    public void infixOrder(){
        if (left!=null){
            left.infixOrder();
        }
        System.out.println(this);
        if (right!=null){
            right.infixOrder();
        }
    }

    public HeroNode infixOrderSearch(int no){
        HeroNode temp = null;
        if (left!=null){
            temp = left.infixOrderSearch(no);
        }
        if (temp!=null){
            return temp;
        }

        System.out.println("中序查找");
        if (this.no==no){
            return this;
        }

        if (right!=null){
            temp = right.infixOrderSearch(no);
        }
        return temp;
    }

    public void postOrder(){
        if (left!=null){
            left.postOrder();
        }
        if (right!=null){
            right.postOrder();
        }
        System.out.println(this);
    }

    public HeroNode postOrderSearch(int no){
        HeroNode temp = null;
        if (left!=null){
            temp = left.postOrderSearch(no);
        }
        if (temp!=null){
            return temp;
        }

        if (right!=null){
            temp = right.postOrderSearch(no);
        }
        if (temp!=null){
            return temp;
        }

        System.out.println("后序查找");
        if (this.no==no){
            return this;
        }
        return temp;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name +
                '}';
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }
}

