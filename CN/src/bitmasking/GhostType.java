package bitmasking;

import java.util.Scanner;

public class GhostType {
    static int permutation(int mask, int n,int[] dp)
    {
        int a=1<<n-1;
        if(mask==(a|a-1))
            return 1;
        if(dp[mask]!=-1)
            return dp[mask];

        int co=0;
        for (int i = 0; i < n; i++) {
            if((mask&(1<<i)) == 0)
            {
                boolean s=false;
                for (int j = 0; j < n; j++) {
                    if((mask&(1<<j)) > 0 && (j+1 & i+1) == i+1){
                        s=true;
                        break;
                    }
                }
                if(!s)
                {
                    co+=permutation(mask|(1<<i),n,dp);
                }
            }
        }
        return dp[mask]=co;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int[] dp=new int[1<<n];
        for (int i = 0; i < 1 << n; i++) {
            dp[i]=-1;
        }
        System.out.println(permutation(0,n,dp));
    }
}
