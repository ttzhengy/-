package 树;

import java.util.*;

public class HuffmanCodeDemo {
    public static void main(String[] args) {
        String content = "i like like like java do you like a java";
        byte[] bytes = content.getBytes();
        HuffmanCodeTree huffmanCodeTree = new HuffmanCodeTree(bytes);
        // huffmanCodeTree.root.preOrder();
        byte[] encodeByte = huffmanCodeTree.encode(bytes);
        System.out.println(Arrays.toString(huffmanCodeTree.encode(bytes)));
        // huffmanCodeTree.decode(encodeByte[0]);
    }


}

class HuffmanCodeTree{
    List<Node2> list = new ArrayList<Node2>();
    Node2 root;
    Map<Byte,String> huffmanCodes = new HashMap<>();

    public HuffmanCodeTree(byte[] bytes){
        root = createHuffmanTree(bytes);
        getCodes();
    }

    // 创建赫夫曼树
    public Node2 createHuffmanTree(byte[] bytes){
        list = getWeight(bytes);
        Collections.sort(list);

        // 将权值最小的两个节点取出，合并到一个父节点上，将父节点放回并排序
        while (list.size()>1){
            Node2 left = list.get(0);
            Node2 right = list.get(1);
            Node2 parent = new Node2(null,left.weight+right.weight);
            parent.left = left;
            parent.right = right;
            list.remove(left);
            list.remove(right);
            list.add(parent);
            Collections.sort(list);
        }
        return list.get(0);
    }

    // 统计原字符串权重
    public List<Node2> getWeight(byte[] bytes){
        Map<Byte,Integer> counts = new HashMap<>();
        List<Node2> nodes = new ArrayList<>();
        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null){
                counts.put(b,1);
            }else {
                counts.put(b,count+1);
            }
        }
        for (Map.Entry<Byte,Integer> entry : counts.entrySet()) {
            nodes.add(new Node2(entry.getKey(),entry.getValue()));
        }
        return nodes;
    }

    // 生成赫夫曼编码表
    public void getCodes(){
        if (root != null){
            root.getCodes("", huffmanCodes);
        }
    }

    public byte[] encode(byte[] bytes){
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bytes) {
            stringBuffer.append(huffmanCodes.get(b));
        }

        // 压缩后的字节数
        byte countToEight = (byte) (stringBuffer.length()%8);
        // 将字符串转为字节码,并补足到8的整倍数长度
        int len = (stringBuffer.length() + 7) / 8;
        while (stringBuffer.length()%8!=0){
            stringBuffer.append("0");
        }

        // 编码后byte数组长度为len+1，最后一位存放编码字符串长度
        byte[] encodeBytes = new byte[len+1];
        encodeBytes[len] = countToEight;
        int index = 0;
        for (int i = 0; i < stringBuffer.length(); i+=8) {
            String str2Byte;
            str2Byte = stringBuffer.substring(i,i+8);
            // 将str2Byte转为一个byte，放入到byte[]
            encodeBytes[index++] = (byte) Integer.parseInt(str2Byte, 2);
        }
        return encodeBytes;
    }

    // TODO: 2021/5/5 解码部分
    public void decode(byte encodedByte){
        int temo = encodedByte;

        String str = Integer.toBinaryString(temo);
        System.out.println(str);
    }

    public int WPL(){
        return root.HuffmanTreeWPL(0);
    }

}

class Node2 implements Comparable<Node2> {
    Byte data;
    int weight;
    Node2 left;
    Node2 right;

    public Node2(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
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

    public void getCodes(String code, Map<Byte,String> huffmanCodes){
        if (data == null){
            left.getCodes(code+"0",huffmanCodes);
            right.getCodes(code+"1",huffmanCodes);
        }else {
            huffmanCodes.put(data,code);
        }
    }

    public int HuffmanTreeWPL(int depth) {
        if (this.left == null || this.right == null) {
            return this.weight * depth;
        } else {
            return this.left.HuffmanTreeWPL(depth + 1) + this.right.HuffmanTreeWPL(depth + 1);
        }
    }

    @Override
    public int compareTo(Node2 o) {
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Node2{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }
}