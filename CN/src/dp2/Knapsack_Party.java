package dp2;

import java.util.Scanner;

public class Knapsack_Party {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);


        while(true) {

            int w=sc.nextInt();
            int n=sc.nextInt();
            if(w==0 && n==0) break;
            int[] value = new int[n];
            int[] weight = new int[n];


            for (int i = 0; i < n; i++) {
                weight[i] = sc.nextInt();
                value[i] = sc.nextInt();
            }

            int[][] dp = new int[2][w + 1];
            int[][] bk = new int[2][w + 1];
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= w; j++) {
                    if (weight[i - 1] <= j) {
                        if (bk[0][j]> value[i - 1] + bk[0][j - weight[i - 1]]) {
                            dp[0][j] = bk[0][j];
                            dp[1][j] = bk[1][j];
                        }
                        else if(bk[0][j]< value[i - 1] + bk[0][j - weight[i - 1]]) {
                            dp[0][j] =  value[i - 1] + bk[0][j - weight[i - 1]];
                            dp[1][j] =  weight[i - 1] + bk[1][j - weight[i - 1]];
                        }
                        else{
                            if (bk[1][j]< weight[i - 1] + bk[1][j - weight[i - 1]]) {
                                dp[0][j] = bk[0][j];
                                dp[1][j] = bk[1][j];
                            }
                            else {
                                dp[0][j] =  value[i - 1] + bk[0][j - weight[i - 1]];
                                dp[1][j] =  weight[i - 1] + bk[1][j - weight[i - 1]];
                            }
                        }

                    }
                    else {
                        dp[0][j] = bk[0][j];
                        dp[1][j] = bk[1][j];
                    }

                }
                bk = dp;
                dp = new int[2][w + 1];
            }
            System.out.println(bk[1][w]+" "+bk[0][w]);
        }
    }
}
