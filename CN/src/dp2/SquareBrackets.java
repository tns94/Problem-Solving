package dp2;

import java.util.Scanner;

public class SquareBrackets {

    static int count(boolean[] bool,int si,int open,int close,int n, int[][] dp)
    {
        if(close>n || open>n)return 0;
        if(close==n && open==n) return 1;

        if(dp[open][close]!=0)
            return dp[open][close];
        if (open==close || bool[si])
            return dp[open][close]=count(bool,si+1,open+1,close,n,dp);
        else if(open==n)
            return dp[open][close]=count(bool,si+1,open,close+1,n,dp);
        else
            return dp[open][close]= count(bool,si+1,open+1,close,n,dp)+ count(bool,si+1,open,close+1,n,dp);
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();
        for (int i = 0; i < t; i++) {
            int n=sc.nextInt();
            int k=sc.nextInt();
            boolean[] bool=new boolean[2*n+1];
            int pos;
            bool[0]=true;
            for (int j = 0; j < k; j++) {
                pos=sc.nextInt();
                if(pos!=0)
                bool[pos-1] = true;
            }
            int[][] dp=new int[n+1][n+1];
            System.out.println(count(bool,0,0,0,n,dp));
        }

    }
}
