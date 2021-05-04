package 树;

import java.util.*;
import java.util.Comparator;

public class HuffmanTreeDemo {
    public static void main(String[] args) {
        int[] arr =  {13,7,8,3,29,6,1};
        HuffmanTree huffmanTree = new HuffmanTree(arr);
        huffmanTree.root.preOrder();
        System.out.println("WPL="+huffmanTree.WPL());
    }
}

class HuffmanTree{
    List<Node> list = new ArrayList<Node>();
    Node root;

    public HuffmanTree(int[] arr){
        root = createHuffmanTree(arr);
    }

    // 创建赫夫曼树
    public Node createHuffmanTree(int[] arr){
        for (int a : arr) {
            list.add(new Node(a));
        }
        Collections.sort(list);

        // 将权值最小的两个节点取出，合并到一个父节点上，将父节点放回并排序
        while (list.size()>1){
            Node left = list.get(0);
            Node right = list.get(1);
            Node parent = new Node(left.value+right.value);
            parent.left = left;
            parent.right = right;
            list.remove(left);
            list.remove(right);
            list.add(parent);
            Collections.sort(list);
        }
        return list.get(0);
    }

    public int WPL(){
        return root.HuffmanTreeWPL(0);
    }

    public void list(){
        for (Node n : list) {
            if (n.left==null) {
                System.out.println(n);
            }
        }
    }
}

// 为了让Node对象可以排序，实现Comparable接口
class Node implements Comparable<Node>{
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    public void preOrder() {
        if (this.left == null) {
            System.out.println(this);
        }
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    public int HuffmanTreeWPL(int depth){
        if (this.left == null && this.right == null){
            return this.value*depth;
        }else{
            return this.left.HuffmanTreeWPL(depth+1)+this.right.HuffmanTreeWPL(depth+1);
        }
    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}