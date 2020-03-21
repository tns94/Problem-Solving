package dp;

import java.util.Scanner;
/*
N coin boxes numbered from 1 to N.
M times Range [L,R] increase by 1
Q queries that How many coin boxes have atleast X coins.
 */
public class CoinBoxes {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int m=sc.nextInt();
        int[] earr=new int[n+1];
        int[] sarr=new int[n+1];
        int[] mDays=new int[n+1];
        int[] coinco=new int[n+1];
        int max=0;
        for (int i = 0; i < m; i++) {
            sarr[sc.nextInt()]++;
            earr[sc.nextInt()]++;
        }
        mDays[1]=sarr[1];
        coinco[mDays[1]]++;
        for (int i = 2; i <= n; i++) {
            mDays[i]=sarr[i]+mDays[i-1]-earr[i-1];
            coinco[mDays[i]]++;
            max=max<mDays[i]?mDays[i]:max;
        }
        int[] dp=new int[n+1];
        for (int i = max; i > 0 ; i--) {
            dp[i]=coinco[i]+dp[i+1];
        }
        int q=sc.nextInt();

        StringBuilder str=new StringBuilder();
        for (int i = 0; i < q; i++) {
            str.append(dp[sc.nextInt()]).append("\n");

        }
        System.out.print(str);
    }
}
