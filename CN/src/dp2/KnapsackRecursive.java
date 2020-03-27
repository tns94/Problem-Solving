package dp2;

import java.util.Scanner;

public class KnapsackRecursive {

    static int knapsack(int[] value,int[] weight,int si,int w,int[][] dp)
    {
        if(si==value.length || w<=0)
            return 0;
        if(dp[si][w]>-1)
            return dp[si][w];
        int ans1=0,ans2;
        if(weight[si]<=w)
            ans1=knapsack(value,weight,si+1,w-weight[si],dp)+value[si];
        ans2=knapsack(value,weight,si+1,w,dp);
        ans1=Math.max(ans1,ans2);
        dp[si][w]=ans1;
        return ans1;

    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();

        int[] value=new int[n];
        int[] weight=new int[n];
        int w;

        for (int i = 0; i < n; i++) weight[i]=sc.nextInt();
        for (int i = 0; i < n; i++) value[i]=sc.nextInt();
        w=sc.nextInt();
        int[][] dp=new int[n+1][w+1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= w; j++) {
                dp[i][j]=-1;
            }
        }
        System.out.println(knapsack(value,weight,0,w,dp));

    }
}
