package dp;

import java.util.Scanner;

//Find number of increasing  subsequences in array with gcd==1
public class TotalSubsequence {

    static int gcd(int a,int b)
    {
        int ans = 1;
        for(int i = 1; i <= a && i <= b; i++)
        {
            if(a%i==0 && b%i==0)
                ans = i;
        }
        return ans;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int[] arr=new int[n];
        long[][] dp =new long[n][101];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();

        int ng;
        for (int i = 0; i < n; i++) {
            dp[i][arr[i]]=1;
            for (int j = 0; j < i; j++) {
                if(arr[i]>arr[j]) {
                    dp[i][1]=dp[i][1]+dp[j][1];
                    for (int k = 2; k <= arr[i]; k++) {
                        ng = gcd(k, arr[i]);
                        dp[i][ng] = (dp[i][ng] + dp[j][k]) % 1000000007;
                    }
                }

            }
        }
        long total=0;
        for (int i = 0; i < n; i++) {
            total=(total+dp[i][1])%1000000007;
        }
        System.out.println(total);
    }
}
