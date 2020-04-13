package dp2;

import java.util.Scanner;
/*
Length of the smallest super-sequence of given two strings.
 */
public class SmallestSuperSequence {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String str1=sc.next();
        String str2=sc.next();
            int l1= str1.length();
            int l2=str2.length();

        int[] dp=new int[l2+1];
        int[] bk=new int[l2+1];
        for (int i = 0; i <= l2; i++)
            bk[i]=i;
        for (int i = 1; i <= l1; i++) {
            dp[0]=i;
            for (int j = 1; j <= l2; j++) {
                if (str1.charAt(l1-i)==str2.charAt(l2-j))
                    dp[j]=bk[j-1]+1;
                else
                    dp[j]=Math.min(bk[j],dp[j-1])+1;
            }
            bk=dp;
            dp=new int[l2+1];
        }
        System.out.println(bk[l2]);

    }
}
