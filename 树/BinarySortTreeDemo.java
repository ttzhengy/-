package 树;

public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = { 7, 3, 10, 12, 5, 1, 9, 2 };
        BinarySortTree tree = new BinarySortTree(arr);
        // tree.infixOrder();
        tree.delete(7);
        tree.infixOrder();
        // tree.delete(10);
        // tree.infixOrder();
    }
}

class BinarySortTree{
    BinarySortTreeNode root;

    public BinarySortTree(int[] arr){
        for (int i : arr) {
            if(root==null){
                root = new BinarySortTreeNode(i);
            }else {
                root.add(new BinarySortTreeNode(i));
            }
        }
    }

    public void add(int i){
        if (root==null){
            root = new BinarySortTreeNode(i);
        }else {
            root.add(new BinarySortTreeNode(i));
        }
    }

    public void add(BinarySortTreeNode node){
        if (root==null){
            root = node;
        }else {
            root.add(node);
        }
    }

    public BinarySortTreeNode search(int i){
        if (root==null){
            return null;
        }else {
            return root.search(i);
        }
    }

    public BinarySortTreeNode searchParent(int i){
        if (root==null){
            return null;
        }else {
            return root.searchParent(i);
        }
    }

    public void delete(int i){
        if (root==null){
            return;
        }else if (root.left==null && root.right==null){     //只有根节点
            root=null;
            return;
        }else if (root.value == i){     //删除根节点
            if (root.left!=null && root.right!=null){
                root.value =  delRightTreeMin(root.right);
            }else {
                if (root.left!=null){
                    root = root.left;
                }else {
                    root = root.right;
                }
            }
        }else {
            BinarySortTreeNode target = search(i);
            if (target==null){
                return;
            }
            BinarySortTreeNode parent = searchParent(i);
            // 叶子节点
            if (target.left==null && target.right==null){
                if (parent.left != null && parent.left.value == i) { // 是左子结点
                    parent.left = null;
                } else if (parent.right != null && parent.right.value == i) {// 是右子结点
                    parent.right = null;
                }
            // 两颗子树
            }else if (target.left!=null && target.right!=null){
                target.value =  delRightTreeMin(target.right);
            // 一颗子树
            }else {
                if (target.left!=null){     // target有左子树
                    if (parent.left.value == i){
                        parent.left = target.left;  //target是parent的左子树
                    }else {
                        parent.right = target.left; //target是parent的右子树
                    }
                }else {                     // target有右子树
                    if (parent.left.value == i){
                        parent.left = target.right;  //target是parent的左子树
                    }else {
                        parent.right = target.right; //target是parent的右子树
                    }
                }
            }
        }
    }

    public void infixOrder(){
        if (root==null){
            System.out.println("二叉树为空");
        }else {
            root.infixOrder();
        }
    }

    // 返回节点右子树的最小值，并删除该最小值节点
    public int delRightTreeMin(BinarySortTreeNode node) {
        BinarySortTreeNode target = node;
        // 循环的查找左子节点，就会找到最小值
        while (target.left != null) {
            target = target.left;
        }
        // 这时 target就指向了最小结点
        // 删除最小结点（该节点肯定是左叶子节点）
        delete(target.value);
        return target.value;
    }
}

class BinarySortTreeNode{
    int value;
    BinarySortTreeNode left;
    BinarySortTreeNode right;

    public BinarySortTreeNode(int v){
        value = v;
    }

    public void add(BinarySortTreeNode node){
        if (node == null){
            return;
        }
        if (node.value<=value){
            if (left==null){
                left = node;
            }else {
                left.add(node);
            }
        }else {
            if (right==null){
                right = node;
            }else {
                right.add(node);
            }
        }
    }

    public BinarySortTreeNode search(int i){
        // 当前节点就是待删除节点的父节点
        if (i == this.value) {
            return this;
        }else if (i<this.value){
            if (this.left == null){
                return null;
            }
            return this.left.search(i);
        }else {
            if (this.right == null){
                return null;
            }
            return this.right.search(i);
        }
    }

    // 返回待删除节点的父节点
    public BinarySortTreeNode searchParent(int i){
        // 当前节点就是待删除节点的父节点
        if ((this.left!=null&&this.left.value==i) || (this.right!=null&&this.right.value==i)) {
            return this;
        }else {
            // 小于当前
            if (i<this.value && this.left!=null){
                return this.left.searchParent(i);
            }else if (i>this.value && this.right!=null){
                return this.right.searchParent(i);
            }else {
                return null;
            }
        }
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

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}
