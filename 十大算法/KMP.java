package 十大算法;

import java.util.Arrays;

public class KMP {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
        // int i = voilenceMatch(str1, str2);
        // System.out.println(i);

        int kmp = kmp(str1, str2);
        System.out.println(kmp);
    }

    private static int voilenceMatch(String str1, String str2){
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int s1Len = str1.length();
        int s2Len = str2.length();

        int i = 0;
        int j = 0;
        while (i < s1Len && j < s2Len){
            if (s1[i]==s2[j]){
                i++;
                j++;
            }else {
                i-=(j-1);
                j=0;
            }
        }
        if (j==s2Len){
            return i-j;
        }else {
            return -1;
        }
    }

    private static int kmp(String str1,String str2){
        int[] next = kmpNext(str2);
        for (int i = 0, j = 0; i < str1.length();i++){
            while (j>0 && str1.charAt(i)!=str2.charAt(j)) {
                // 记住。KMP核心
                j = next[j - 1];
            }
            if (str1.charAt(i)==str2.charAt(j)){
                j++;
            }
            if (j==str2.length()){
                return i-j+1;
            }
        }
        return -1;
    }

    private static int[] kmpNext(String dest){
        int[] next = new int[dest.length()];
        next[0] = 0;
        // i是next的下标，j代表字符串连续匹配的值
        for (int i = 1,j = 0; i < dest.length(); i++) {
            // dest.charAt(j)是字符串头的j个字符
            // 当dest.charAt(i)！=dest.charAt(j)，则从上一个匹配适合的next[i]
            // 例子：DABCDABCD
            while (j>0 && dest.charAt(i)!=dest.charAt(j)){
                j = next[j-1];
            }

            // 当前next下标字符与字符串字符相同
            if (dest.charAt(i)==dest.charAt(j)){
                j++;
            }
            next[i]=j;
        }
        return next;
    }
}
