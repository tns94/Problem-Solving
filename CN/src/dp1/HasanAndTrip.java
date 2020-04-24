package dp1;

import java.util.Scanner;

public class HasanAndTrip {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int arr[][]=new int[n][3];
        double dp[]=new double[n];
        for (int i = 0; i < n; i++) {
            arr[i][0]=sc.nextInt();
            arr[i][1]=sc.nextInt();
            arr[i][2]=sc.nextInt();
        }
        dp[0]=arr[0][2];
        double max;

        for (int i = 1; i < n; i++) {
            max=Long.MIN_VALUE;
            for (int j = 0; j < i; j++) {
                dp[i]=dp[j] + arr [i][2]-  ( Math.sqrt(   Math.pow(arr[j][0] - arr[i][0],2)+ Math.pow( arr[j][1]-arr[i][1],2)   ) );
                max=dp[i]<max?max:dp[i];
            }
            dp[i]=max;
        }
        System.out.printf("%1$.6f",dp[n-1]);


    }
}
