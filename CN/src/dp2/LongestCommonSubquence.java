package dp2;

import java.util.Scanner;

/*
length of Longest Common Subsequence in two strings
 */
public class LongestCommonSubquence {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String str1=sc.next();
        String str2=sc.next();

        int m= str1.length();
        int n= str2.length();

        int[][] dp=new int[m+1][n+1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (str1.charAt(m-i)==str2.charAt(n-j))
                    dp[i][j]=dp[i-1][j-1]+1;
                else
                    dp[i][j]=dp[i-1][j]>dp[i][j-1]?dp[i-1][j]:dp[i][j-1];
            }
        }
        System.out.print(dp[m][n]);

    }
}
