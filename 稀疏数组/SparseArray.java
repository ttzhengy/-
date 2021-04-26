package 稀疏数组;

import java.util.Arrays;

public class SparseArray {
    public static void main(String[] args) {
        // 创建原始二维数组11*11
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = -1;
        for (int[] row : chessArr1) {
            for (int i : row) {
                System.out.printf("%d\t",i);
            }
            System.out.println();
        }

        int sum = 0;
        for(int i=0;i< chessArr1.length;i++){
            for (int j = 0; j < chessArr1[i].length; j++) {
                if (chessArr1[i][j] != 0){
                    sum++;
                }
            }
        }

        int[][] sparseArr = new int[sum+1][3];
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;

        int k = 1;
        for(int i=0;i< chessArr1.length;i++){
            for (int j = 0; j < chessArr1[i].length; j++) {
                if (chessArr1[i][j] != 0){
                    sparseArr[k][0] = i;
                    sparseArr[k][1] = j;
                    sparseArr[k][2] = chessArr1[i][j];
                    k++;
                }
            }
        }
        //System.out.println(Arrays.toString(sparseArr));

        int[][] chessArr2 = new int[sparseArr[0][0]][sparseArr[0][1]];
        for (int i = 1; i < sparseArr.length; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        for (int[] row : chessArr2) {
            System.out.println(Arrays.toString(row));
        }
    }
}
