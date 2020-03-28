package dp2;

import java.util.Scanner;

public class CharlieAndPilot {

    static int minSalary(int[] cap,int[] ast,int x,int si,int n,int dp[][])
    {
        if(si==ast.length)
            return 0;

        if(dp[n][x]!=0)
            return dp[n][x];
        if(x==0)
            return  dp[n][x]=minSalary(cap,ast,x+1,si+1,n-1,dp)+ast[si];
        else if(x==n)
            return  dp[n][x]=minSalary(cap,ast,x-1,si+1,n-1,dp)+cap[si];
        else
            return dp[n][x]=Math.min(minSalary(cap,ast,x-1,si+1,n-1,dp)+cap[si],minSalary(cap,ast,x+1,si+1,n-1,dp)+ast[si]);
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int[] cap=new int[n];
        int[] ast=new int[n];

        for (int i = 0; i < n; i++) {
            cap[i]=sc.nextInt();
            ast[i]=sc.nextInt();
        }
        int dp[][]=new int[n+1][n+1];
        System.out.println(minSalary(cap,ast,0,0,n,dp));
    }
}
