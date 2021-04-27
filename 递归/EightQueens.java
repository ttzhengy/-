package 递归;

public class EightQueens {
    static int[] array = new int[8];
    static int count = 0;
    static int judgeCount = 0;

    public static void main(String[] args) {
        EightQueens queen = new EightQueens();
        queen.check(0);
        System.out.println(judgeCount);
    }

    // 入口为check(0)
    private void check(int n){
        if (n==8){
            print();
            return;
        }
        for (int i = 0; i < 8;i++){
            // 皇后放到n行第一列
            array[n] = i;
            if (judge(n)){
                // 如果当前位置可行，则开始拜访下一个皇后
                check(n+1);
            }
            // 如果不可行，则进入下一次for循环，即皇后后移一列
        }
    }

    // 第n个皇后的判断,n从0开始
    public boolean judge(int n){
        judgeCount++;
        for (int i = 0; i < n; i++) {
            if(array[i]==array[n]||Math.abs(n-i)==Math.abs(array[i]-array[n])){
                return false;
            }
        }
        return true;
    }
    
    private void print(){
        count++;
        for (int i = 0; i < 8; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.printf("第%d个解\n",count);
    }
}
