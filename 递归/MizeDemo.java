package 递归;

public class MizeDemo {
    public static void main(String[] args) {
        // 使用二维数组表示迷宫
        int map[][] = new int[8][7];
        // 1表示墙
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        for (int i = 0; i < 7; i++) {
            if (i==3)continue;
            map[7-i][i] = 1;
        }
        printMap(map);

        mize(map,1,1);
        printMap(map);
    }

    /**
     * 寻找走出迷宫的可能性，迷宫中0为未走过，1为墙，2为走过的通路，3为走过的死路
     * @param map 地图
     * @param i 起始位置
     * @param j
     * @return 能否走到终点
     */
    public static boolean mize(int[][] map, int i, int j){
        // 终点为map[6][5]
        if (map[6][5] == 2){
            return true;
        }else {
            if (map[i][j] == 0){
                map[i][j] = 2;
                // 当前策略为下右上左
                if (mize(map,i+1,j)){
                    return true;
                }else if (mize(map,i,j+1)){
                    return true;
                }else if (mize(map,i-1,j)){
                    return true;
                }else if (mize(map,i,j-1)){
                    return true;
                }else {
                    map[i][j] = 3;
                    return false;
                }
            }else {
                return false;
            }
        }
    }

    public static void printMap(int[][] map){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j]+"\t");
            }
            System.out.println();
        }
        System.out.println();
    }
}
