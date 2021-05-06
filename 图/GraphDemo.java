package 图;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class GraphDemo {
    public static void main(String[] args) {
        String[] Vertexs = {"A", "B", "C", "D", "E"};
        int n = Vertexs.length;

        Graph graph = new Graph(n);
        for (String vertex : Vertexs) {
            graph.insertVertex(vertex);
        }

        //添加边
        //A-B A-C B-C B-D B-E
        graph.insertEdge(0, 1, 1); // A-B
        graph.insertEdge(0, 2, 1); // A-C
        graph.insertEdge(1, 2, 1); // B-C
        graph.insertEdge(1, 3, 1); // B-D
        graph.insertEdge(1, 4, 1); // B-E
        //显示邻结矩阵
        graph.showGraph();
        graph.DFS();
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

    // public int getFirstNeighbor(int i){
    //     for (int j = 0; j < vertexList.size(); j++) {
    //         if (edges[i][j]>0){
    //             return j;
    //         }
    //     }
    //     return -1;
    // }
    //
    // public int getNextNeightbor(int i,int j){
    //     for (int k = j+i; k < vertexList.size(); k++) {
    //         if (edges[i][k]>0){
    //             return k;
    //         }
    //     }
    //     return -1;
    // }

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
