package 十大算法;

public class Hanoitower {
    public static void main(String[] args) {
        hanoiTower(9,'A','B','C');
    }

    public static void hanoiTower(int num, char a, char b, char c){
        if (num==1){
            System.out.println("第1个盘从 " + a + "->" + c);
        }else {
            // 上面的n-1个盘从a经过c到b
            hanoiTower(num-1,a,c,b);
            // 最下面的一个盘到c
            System.out.println("第" + num + "个盘从 " + a + "->" + c);
            // 上面的n-1个盘从b经过a到c
            hanoiTower(num-1,b,a,c);
        }
    }
}
