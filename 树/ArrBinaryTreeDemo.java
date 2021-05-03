package 树;

public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        // int[] arr = {1,2,3,4,5,6,7};
        // ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        // arrBinaryTree.preOrder();
        // arrBinaryTree.infixOrder();
        // arrBinaryTree.postOrder();

        int[] arr = new int[8];
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        arrBinaryTree.preOrder();
        System.out.println(arrBinaryTree.getDepth()+" "+arrBinaryTree.getSize());
    }
}

class ArrBinaryTree{
    private int[] tree;
    private int size = 0;
    private int depth = 0;

    public ArrBinaryTree(int depth){
        tree = new int[2^(depth-1)];
        this.depth = depth;
        size = (int)Math.pow(2,depth) - 1;
    }

    public ArrBinaryTree(int[] arr){
        tree = arr;
        if (tree.length == 0){
            return;
        }
        while (((int)Math.pow(2,depth)-1)<tree.length){
            depth++;
        }
        size = tree.length;
    }

    public void preOrder(){
        System.out.println("前");
        preOrder(0);
    }

    public void preOrder(int index){
        if (tree==null || size == 0){
            System.out.println("数组为空");
            return;
        }
        System.out.println(tree[index]);
        if (index*2+1<size){
            preOrder(index*2+1);
        }
        if (index*2+2<size){
            preOrder(index*2+2);
        }
    }

    public void infixOrder(){
        System.out.println("中");
        infixOrder(0);
    }

    public void infixOrder(int index){
        if (tree==null || size == 0){
            System.out.println("数组为空");
            return;
        }
        if (index*2+1<size){
            infixOrder(index*2+1);
        }
        System.out.println(tree[index]);
        if (index*2+2<size){
            infixOrder(index*2+2);
        }
    }

    public void postOrder(){
        System.out.println("后");
        postOrder(0);
    }

    public void postOrder(int index){
        if (tree==null || size == 0){
            System.out.println("数组为空");
            return;
        }
        if (index*2+1<size){
            postOrder(index*2+1);
        }
        if (index*2+2<size){
            postOrder(index*2+2);
        }
        System.out.println(tree[index]);
    }

    public int getSize() {
        return size;
    }

    public int getDepth() {
        return depth;
    }
}
