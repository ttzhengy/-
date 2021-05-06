package 图;

import java.util.*;

public class GraphDemo {
    public static void main(String[] args) {
        // 测试一把图是否创建ok
        String Vertexs[] = {"1", "2", "3", "4", "5", "6", "7", "8"};
        int n = Vertexs.length; // 结点的个数

        // 创建图对象
        Graph graph = new Graph(n);
        // 循环的添加顶点
        for (String vertex : Vertexs) {
            graph.insertVertex(vertex);
        }

        //更新边的关系
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.insertEdge(3, 7, 1);
        graph.insertEdge(4, 7, 1);
        graph.insertEdge(2, 5, 1);
        graph.insertEdge(2, 6, 1);
        graph.insertEdge(5, 6, 1);

        //显示一把邻结矩阵
        graph.showGraph();

        System.out.println("深度遍历");
        graph.DFS();
        System.out.println();
        System.out.println("广度遍历");
        graph.BFS();
    }
}

class Graph{
    private ArrayList<String> vertexList;
    private int[][] edges;
    private int numOfEdge;
    private boolean[] isVisited;

    public Graph(int n){
        vertexList = new ArrayList<>(n);
        edges = new int[n][n];
        numOfEdge = 0;
        isVisited = new boolean[n];
    }

    // 返回一个未被遍历的邻接节点
    public int getNextNeightbor(int i){
        for (int j = 0; j < vertexList.size(); j++) {
            if (edges[i][j]>0 && !isVisited[j]){
                return j;
            }
        }
        return -1;
    }

    public void DFS(){
        isVisited = new boolean[vertexList.size()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            // 防止非连通图
            if (!isVisited[i]){
                DFS(i);
            }
        }
    }

    private void DFS(int i){
        System.out.print(getValueByIndex(i)+"->");
        isVisited[i] = true;
        Stack<Integer> stack = new Stack<>();
        stack.push(i);
        int w = getNextNeightbor(i);
        // 将当前节点的下一个邻接节点入栈，即栈顶为邻接节点
        while (!stack.isEmpty()){
            // 当当前栈顶节点还有邻接节点，则将该邻接节点压栈，并Visited，输出
            if (w!=-1){
                isVisited[w] = true;
                stack.push(w);
                System.out.print(getValueByIndex(w)+"->");
            }else {     // 否则出栈
                stack.pop();
            }
            // 当栈不为空，获得栈顶元素的下一个邻接节点
            if (!stack.isEmpty()){
                w = getNextNeightbor(stack.peek());
            }
        }
    }

    public void BFS(){
        isVisited = new boolean[vertexList.size()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            // 防止非连通图
            if (!isVisited[i]){
                BFS(i);
            }
        }
    }

    private void BFS(int i){
        System.out.print(getValueByIndex(i)+"->");
        isVisited[i] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(i);
        // 当队列不为空，取出队列头，将这个节点的所有未遍历邻接节点入队
        while (!queue.isEmpty()){
            i = queue.poll();
            int w = getNextNeightbor(i);
            while (w!=-1){
                isVisited[w] = true;
                System.out.print(getValueByIndex(w)+"->");
                queue.add(w);
                w = getNextNeightbor(i);
            }
        }
    }

    public void insertVertex(String vertex){
        vertexList.add(vertex);
    }

    public int getNumOfVertex(){
        return vertexList.size();
    }

    public int getNumOfEdges(){
        return numOfEdge;
    }

    public String getValueByIndex(int index){
        return vertexList.get(index);
    }

    public int getWeight(int v1, int v2){
        return edges[v1][v2];
    }

    public void showGraph(){
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
    }

    public void insertEdge(int v1, int v2, int weight){
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdge++;
    }

}
