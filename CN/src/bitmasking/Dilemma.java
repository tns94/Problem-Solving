package bitmasking;

import java.nio.file.LinkPermission;
import java.util.Scanner;

public class Dilemma {
    public static int minimumTouchRequired(int n, String[] input) {

        int stringLength=input[0].length();
        int[][] dp=new int[1<<(n+1)][stringLength];
        for (int i = 0; i < (1 << (n+1)); i++) {
            for (int j = 0; j < stringLength; j++) {
                dp[i][j]=-1;
            }
        }
        int mask=(1<<n)-1;
        return solve(input,mask,0,n,stringLength,dp);
    }
    static int solve(String[] input,int mask,int pos,int n,int stringLength,int[][] dp){

        if((mask&(mask-1))==0)
            return 0;
        if(pos==stringLength)
            return 10000;

        if(dp[mask][pos]!=-1)
            return dp[mask][pos];

        int mask1=0,mask2=0;
        int touches=0;
        for (int i = 0; i < n; i++) {
            if((mask&(1<<i))!=0) {
                if (input[i].charAt(pos) == '1')
                    mask1 = mask1 | (1 << i);
                if (input[i].charAt(pos) == '0')
                    mask2 = mask2 | (1 << i);
                touches++;
            }
        }
        return dp[mask][pos]=Math.min(touches+
                solve(input,mask1,pos+1,n,stringLength,dp)+
                solve(input,mask2,pos+1,n,stringLength,dp),
                solve(input,mask,pos+1,n,stringLength,dp));
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        String[] arr=new String[n];
        for (int i = 0; i < n; i++) {
            arr[i]=sc.next();
        }
        System.out.println(minimumTouchRequired(n, arr));
    }
}
