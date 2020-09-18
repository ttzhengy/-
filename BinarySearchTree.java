import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class BinarySearchTree{

    BinaryNode root;
    Deque<BinaryNode> stack = new LinkedList<>();

    public class BinaryNode{
        int element;
        BinaryNode parentNode;
        BinaryNode leftNode;
        BinaryNode rightNode;
        boolean Flag = false;
         
        public BinaryNode(){
            element = 0;
            parentNode = null;
            leftNode = null;
            rightNode = null;
        }

        public BinaryNode(int e){
            element = e;
            parentNode = null;
            leftNode = null;
            rightNode = null;
        }

        public BinaryNode(int e, BinaryNode pNode){
            element = e;
            parentNode = pNode;
            leftNode = null;
            rightNode = null;
        }

        public void addElement(int e){
            if(element > e){
                if(leftNode == null){
                    leftNode = new BinaryNode(e, this);
                }else{
                    leftNode.addElement(e);
                }
            }else if(element < e){
                if(rightNode == null){
                    rightNode = new BinaryNode(e,this);
                }else{
                    rightNode.addElement(e);
                }
            }
        }

        public void nestingInorderTraversal(){
            if(leftNode != null){
                leftNode.nestingInorderTraversal();;
            }
            System.out.println(element);
            if(rightNode != null){
                rightNode.nestingInorderTraversal();;
            }
         }

        public void nestingPreorderTraversal(){
            System.out.println(element);
            if(leftNode != null){
                leftNode.nestingPreorderTraversal();
            }
            if(rightNode != null){
                rightNode.nestingPreorderTraversal();
            }
        }

        public void nestingPostorderTraversal(){
            if(leftNode != null){
                leftNode.nestingPostorderTraversal();
            }
            if(rightNode != null){
                rightNode.nestingPostorderTraversal();
            }
            System.out.println(element);
        }

        public void buildTree(){
            Scanner sc = new Scanner(System.in);
            int num = sc.nextInt();
            if(num != 999){
                element = num;
                leftNode = new BinaryNode();
                leftNode.buildTree();
                rightNode = new BinaryNode();
                rightNode.buildTree();
            }else{
                
            }
        }

        public void setFlag(boolean b){
            Flag = b;
        }
    }

    public void stackInorderTraversal(){
        BinaryNode node = root;
        while(node != null || !stack.isEmpty()){
            if(node != null){
                stack.push(node);
                node = node.leftNode;
            }else{
                node = stack.pop();
                System.out.println(node.element);
                node = node.rightNode;
            }
        }
    }

    public void stackPreorderTraversal(){
        BinaryNode node = root;
        while(node != null || !stack.isEmpty()){
            if(node != null){
                stack.push(node);
                System.out.println(node.element);
                node = node.leftNode;
            }else{
                node = stack.pop();
                node = node.rightNode;
            }
        }
    }

    public void stackPostorderTraversal(){
        BinaryNode node = root;
        BinaryNode lastPop = null;
        stack.push(node);
        while(node != null || !stack.isEmpty()){
            if(node.leftNode != null && !node.leftNode.equals(lastPop) && !node.rightNode.equals(lastPop)){
                node = node.leftNode;
                stack.push(node);
            }else if(node.rightNode != null && !node.rightNode.equals(lastPop)){
                node = node.rightNode;
                stack.push(node);
            }else{
                System.out.println(node.element);
                stack.pop();
                lastPop = node;
                node = stack.peek();
            }
        }
    }

    //按二叉搜索树规律写入
    public void add(int e){
        if(root == null){
            root = new BinaryNode(e);
        }else{
            root.addElement(e);
        }
    }

    //嵌套遍历
    public void nestingTraversal(){
        if(root == null){
            System.out.println("null");
        }else{
        root.nestingInorderTraversal();
        }
    }

    //按前序顺序写入，写入99为null
    public BinaryNode buildTree(BinaryNode node){
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        if(num != 99){
            node = new BinaryNode(num);
            node.leftNode = buildTree(node.leftNode);
            node.rightNode = buildTree(node.rightNode);
        }
        return node;
    }

    public int getDepth(BinaryNode node){
        stack.offer(node);
        BinaryNode bitTree = null;
        int depth = 0;
        while(!stack.isEmpty()){
            int length = stack.size();                  //获取当前层节点个数
            while (length > 0){
                bitTree = stack.poll();                 //弹出节点并将该节点的子节点加入队列
                if(bitTree.leftNode != null){
                    stack.offer(bitTree.leftNode);
                }
                if(bitTree.rightNode != null){
                    stack.offer(bitTree.rightNode);
                }
                length--;
            }
            depth++;                                    //弹出当前层所有节点后深度+1
        }
        return depth;
    }

    public BinaryNode BSTFind(int x){
        BinaryNode node = root;
        while(node != null){
            if(x > node.element){
                node = node.rightNode;
            }else if(x < node.element){
                node = node.leftNode;
            }else{
                return node;
            }
        }
        return null;
    }

    public BinaryNode BSTFindMin(){
        BinaryNode node = root;
        if(node != null){
            while(node.leftNode != null){
                node = node.leftNode;
            }
            return  node;
        }
        return null;
    }

    public BinaryNode BSTFindMax(){
        BinaryNode node = root;
        if(node != null){
            while(node.rightNode != null){
                node = node.rightNode;
            }
            return node;
        }
        return null;
    }

    public void BSTInsert(int x){
        // BinaryNode newNode = new BinaryNode(x);
        if(root == null){
            root = new BinaryNode(x);
        }else{
            BinaryNode cur = root;
            while(cur != null){
                if(x > cur.element){
                    if(cur.rightNode == null){
                        cur.rightNode = new BinaryNode(x, cur);
                        return;
                    }else{
                        cur = cur.rightNode;
                    }
                }else if(x < cur.element){
                    if(cur.leftNode == null){
                        cur.leftNode = new BinaryNode(x, cur);
                        return;
                    }else{
                        cur = cur.leftNode;
                    }
                }
            }
        }
    }

    public void BSTDelete(int x){
        BinaryNode cur = root;
        BinaryNode parent = root;
        boolean isLeftNode = true;
        if(root == null){
            return;
        }
        while(cur.element != x){        //寻找待删除元素
            parent = cur;
            if(x > cur.element){
                cur = cur.rightNode;
                isLeftNode = false;
            }else{
                cur = cur.leftNode;
                isLeftNode = true;
            }
            if(cur == null){
                System.out.println("error");
            }
        }
        if(cur == root){
            root = null;
        }
        if(cur.leftNode == null && cur.rightNode == null){      //叶节点
            if(isLeftNode){
                parent.leftNode = null;
            }else{
                parent.rightNode = null;
            }
        }else if(cur.leftNode == null){                         //无左子树
            if(isLeftNode){
                parent.leftNode = cur.rightNode;
            }else{
                parent.rightNode = cur.rightNode;
            }
        }else if(cur.rightNode == null){                         //无右子树
            if(isLeftNode){
                parent.leftNode = cur.leftNode;
            }else{
                parent.rightNode = cur.leftNode;
            }
        }else{                                                  //有左右子树
            BinaryNode successor = getSuccessor(cur);
            if(isLeftNode){
                parent.leftNode = successor;
            }else{
                parent.rightNode = successor;
            }
            successor.leftNode = cur.leftNode;
        }
    }

    public BinaryNode getSuccessor(BinaryNode delNode){         //取右子树的最小元素
        BinaryNode successor = delNode;
        BinaryNode successorParent = delNode;
        BinaryNode cur = delNode.rightNode;
        while(cur != null){
            successorParent = successor;
            successor = cur;
            cur = cur.leftNode;
        }
        if(successor != delNode.rightNode){
            successorParent.leftNode = successor.rightNode;     //最小元素的右子树接到父节点上
            successor.rightNode = delNode.rightNode;            //将删除节点的右子树接到最小元素上
        }
        return successor;
    }


    public static void main(String[] args) {
        int[] aaa = {8, 4, 12, 6, 2, 14, 10, 3, 5, 7, 1, 11, 9, 13, 15};
        BinarySearchTree Tree = new BinarySearchTree();
        for (int i : aaa) {
            Tree.BSTInsert(i);
            // Tree.stackPreorderTraversal();
            // try {
            //     Thread.sleep(1000);
            // } catch (Exception e) {
            //     //TODO: handle exception
            //     e.printStackTrace();
            // }
        }
        Tree.stackInorderTraversal();

        Tree.BSTDelete(12);
        Tree.stackInorderTraversal();
        Tree.stackPreorderTraversal();
    }
}