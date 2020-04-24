package dp2;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TraderProfit {
    static int maxProfit(int[] arr,int si,int k,int w,int[][][] dp)
    {
        if(k==0 || si==arr.length)
            return 0;


        if(dp[si][k][w]!=0)
            return dp[si][k][w];


        int ans;
        if(w==0)
            ans = maxProfit(arr, si + 1, k,1, dp) - arr[si];
        else
            ans = maxProfit(arr,si+1,k-1,0,dp) + arr[si];

        ans=Math.max(ans,maxProfit(arr,si+1,k,w,dp));

        dp[si][k][w]=ans;
        return ans;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();
        for (int i = 0; i < t; i++) {
            int k=sc.nextInt();
            int n=sc.nextInt();
            int[] arr=new int[n];
            int[][][] dp=new int[n][k+1][2];

            for (int j = 0; j < n; j++)
                arr[j]=sc.nextInt();
            System.out.println(maxProfit(arr,0,k,0,dp));

        }
    }
}
