package 树;

public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        // 测试一把中序线索二叉树的功能
        ThreadHeroNode root = new ThreadHeroNode(1, "tom");
        ThreadHeroNode node2 = new ThreadHeroNode(3, "jack");
        ThreadHeroNode node3 = new ThreadHeroNode(6, "smith");
        ThreadHeroNode node4 = new ThreadHeroNode(8, "mary");
        ThreadHeroNode node5 = new ThreadHeroNode(10, "king");
        ThreadHeroNode node6 = new ThreadHeroNode(14, "dim");

        // 二叉树，后面我们要递归创建, 现在简单处理使用手动创建
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        // 测试中序线索化
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedNode();

        // 测试: 以10号节点测试
        HeroNode leftNode = node5.getLeft();
        HeroNode rightNode = node5.getRight();
        System.out.println("10号结点的前驱结点是 =" + leftNode); // 3
        System.out.println("10号结点的后继结点是=" + rightNode); // 1

        // 当线索化二叉树后，不能再使用原来的遍历方法
        System.out.println("使用线索化的方式遍历 线索化二叉树");
        threadedBinaryTree.threadedList(); // 8, 3, 10, 1, 14, 6
    }
}

class ThreadedBinaryTree extends BinaryTree{
    private ThreadHeroNode pre = null;
    private ThreadHeroNode root = null;

    public void setRoot(ThreadHeroNode root) {
        this.root = root;
    }

    public void threadedNode(){
        threadedNode(root);
    }

    // 中序线索化
    public void threadedNode(ThreadHeroNode node){
        if (node == null){
            return;
        }
        // 线索化左子树
        threadedNode(node.getLeft());
        // 线索化当前节点
        // 线索化当前节点的前驱
        if (node.getLeft()==null){
            node.setLeft(pre);
            node.setLeftType(true);
        }
        // 线索化前驱节点的后继
        if (pre!=null && pre.getRight()==null){
            pre.setRight(node);
            pre.setRightType(true);
        }
        // 前驱指针指向当前
        pre = node;
        // 线索化右子树
        threadedNode(node.getRight());
    }

    // 遍历线索化二叉树的方法
    public void threadedList() {
        // 定义一个变量，存储当前遍历的结点，从root开始
        ThreadHeroNode node = root;
        while (node != null) {
            // 循环的找到leftType == ture的结点，第一个找到就是8结点
            // 后面随着遍历而变化,因为当leftType==true时，说明该结点是按照线索化处理后的有效结点
            while (!node.getLeftType()) {
                node = node.getLeft();
            }

            // 打印当前这个结点
            System.out.println(node);
            // 如果当前结点的右指针指向的是后继结点,就一直输出
            while (node.getRightType()) {
                // 获取到当前结点的后继结点
                node = node.getRight();
                System.out.println(node);
            }
            // 替换这个遍历的结点
            node = node.getRight();

        }
    }
}

class ThreadHeroNode extends HeroNode{
    private ThreadHeroNode left;
    private ThreadHeroNode right;
    // false表示指向左、右子树，ture表示指向前驱、后继节点
    private boolean leftType = false;
    private boolean rightType = false;

    public ThreadHeroNode(int no, String name){
        super(no,name);
    }

    @Override
    public ThreadHeroNode getLeft() {
        return left;
    }

    public void setLeft(ThreadHeroNode left) {
        this.left = left;
    }

    @Override
    public ThreadHeroNode getRight() {
        return right;
    }

    public void setRight(ThreadHeroNode right) {
        this.right = right;
    }

    public void setLeftType(boolean leftType) {
        this.leftType = leftType;
    }

    public void setRightType(boolean rightType) {
        this.rightType = rightType;
    }

    public boolean getLeftType() {
        return leftType;
    }

    public boolean getRightType() {
        return rightType;
    }
}