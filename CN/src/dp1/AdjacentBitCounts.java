package dp;

import java.util.Scanner;

/*
Return number of Binary Strings of length n that have k number of times a 1 bit is adjacent to another 1 bit.
 */

public class AdjacentBitCounts {


    static int getCount(int n,int k,int bit,long dp[][][],boolean dpb[][][])
    {
        if (n==1)
        {
            if (k==0)
                return 1;
            else
                return 0;
        }
        if (k<0)
            return 0;
        if(dpb[n][k][bit])
            return (int)dp[n][k][bit];
        long co1=0;
        long co0=0;
        co1=(int)(getCount(n-1, k,0,dp,dpb)+getCount(n-1, k-1, 1,dp,dpb))%1000000007;

        co0=(int)(getCount(n-1, k,0,dp,dpb)+getCount(n-1, k, 1,dp,dpb))%1000000007;
        dp[n][k][1]=co1;
        dp[n][k][0]=co0;
        dpb[n][k][1]=dpb[n][k][0]=true;




        //return (int)((co1+co0)%1000000007);
        return (int)dp[n][k][bit];
    }





    static  int getCount(int pb,int k,int bc,int n,int cur){
        if(bc==k)
        {
            //System.out.println(str+"--"+ (n-cur));
            if (n==cur)
                return 1;
            return n-cur;
        }

        if(n==cur )
        {
            return 0;
        }

        long co=0;
        if(pb==1)
            co= (int)(co+getCount(1,k,bc+1,n,cur+1))%1000000007;

        else
            co= (int)(co+ getCount(1,k,bc,n,cur+1))%1000000007;

        co= (int)(co+getCount(0,k,bc,n,cur+1))%1000000007;


        return (int)co;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();
        int n,k;
        long[][][] dp;
        boolean[][][] dpb;

        for(int i=1;i<=t;i++){
            sc.nextInt();
            n=sc.nextInt();
            k=sc.nextInt();
            dp=new long[n+2][k+1][2];
            dpb=new boolean[n+2][k+1][2];



            System.out.println(i+" "+getCount(n+1, k, 0, dp,dpb));

        }
    }
}