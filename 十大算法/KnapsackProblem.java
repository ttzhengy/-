package 十大算法;

public class KnapsackProblem {
    public static void main(String[] args) {
        int[] w = {1,4,3};
        int[] v = {1500,3000,2000};
        int n = w.length;
        int m = 4;
        int[][] value = new int[n+1][m+1];

        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < m+1; j++) {
                // 如果当前物品的重量大于负重，则在当前物品和负重下，价值与上一物品的相同负重时相同
                if (w[i-1]>j){
                    value[i][j] = value[i-1][j];
                }else {
                    // 如果当前物品的重量小于等于负重，则在当前物品和负重下比较：
                    // 1.装入当前物品后，剩余部分负重可装入的最高价值
                    // 2.上一物品在当前负重时的价值
                    // 最大值为当前状态的价值
                    value[i][j] = Math.max(value[i-1][j],value[i-1][j-w[i-1]]+v[i-1]);
                }

            }
        }
        for (int i = 1; i < n+1; i++) {
            System.out.println(value[i][m]);
        }
    }
}
