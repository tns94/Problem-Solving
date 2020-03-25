package dp2;

import java.util.Scanner;

/*
Return sum A in Common Subsequence of length K of 2 strings
A - Maximum sum of ASCIIs of common subsequence of length K
 */
public class CommonSubsequenceK {
    public static void main(String[] args)
        {
            Scanner sc=new Scanner(System.in);
            int t=sc.nextInt();
            String str1;
            String str2;
            int K;
            for (int l = 0; l < t; l++) {
                str1=sc.next();
                str2=sc.next();
                K=sc.nextInt();

                int m = str1.length();
                int n = str2.length();

                int[][] dp1=new int[m+1][n+1];
                int[][][] dp = new int[m + 1][n + 1][K+1];

                for (int i = 1; i <= m; i++) {
                    for (int j = 1; j <= n; j++) {

                            if(str1.charAt(m-i)==str2.charAt(n-j)){
                                dp1[i][j]=dp1[i-1][j-1]+1;
                                for (int k = 1; k <= K; k++)
                                    dp[i][j][k]=Math.max(dp[i-1][j-1][k-1]+(int) str1.charAt(m-i),Math.max(dp[i-1][j][k],dp[i][j-1][k]));
                            }
                            else
                            {
                                dp1[i][j]=dp1[i-1][j]>dp1[i][j-1]?dp1[i-1][j]:dp1[i][j-1];
                                for (int k = 1; k <= K; k++)
                                dp[i][j][k]=Math.max(dp[i-1][j][k],dp[i][j-1][k]);
                            }


                    }

                }


                if (dp1[m][n]<K)
                    System.out.println(0);
                else
                    System.out.println(dp[m][n][K]);

            }
    }
}
