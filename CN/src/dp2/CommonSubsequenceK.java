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
            int k;
            for (int l = 0; l < t; l++) {
                str1=sc.next();
                str2=sc.next();
                k=sc.nextInt();

                int m = str1.length();
                int n = str2.length();

                int[][] dp = new int[m + 1][n + 1];

                String[][] dps = new String[m + 1][n + 1];
                for (int i = 0; i <= n; i++) {
                    dps[0][i] = "";
                }
                for (int i = 0; i <= m; i++) {
                    dps[i][0] = "";
                }

                for (int i = 1; i <= m; i++) {
                    for (int j = 1; j <= n; j++) {
                        if (str1.charAt(m - i) == str2.charAt(n - j)) {
                            dp[i][j] = dp[i - 1][j - 1] + 1;
                            dps[i][j] = str1.charAt(m - i) + dps[i - 1][j - 1];
                        } else if (dp[i - 1][j] > dp[i][j - 1]) {
                            dp[i][j] = dp[i - 1][j];
                            dps[i][j] = dps[i - 1][j];
                        } else {
                            dp[i][j] = dp[i][j - 1];
                            dps[i][j] = dps[i][j - 1];
                        }
                    }
                }

                int sum=0;
                String s = dps[m][n];
                System.out.println(s);
                if(!(s.length()<k)) {
                    int[] arr = new int[26];

                    for (int i = 0; i < s.length(); i++) {
                        arr[ s.charAt(i)-97]++;
                    }


                    for (int i = 25; i >=0;i--) {
                        if (arr[i]!=0)
                        {
                            sum+=(i+97)*(Math.min(arr[i],k));
                            k-=arr[i];
                            if (k<=0)
                                break;
                        }
                    }
                }
                System.out.println(sum);

            }
    }
}
