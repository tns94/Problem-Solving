package dp2;

import java.util.Arrays;
import java.util.Scanner;

public class Knapsack_SubsetSum_Iterative {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int[] arr=new int[n];
        for (int i = 0; i < n; i++) {
            arr[i]=sc.nextInt();
        }
        int k=sc.nextInt();
        boolean[] dp=new boolean[k+1];
        boolean[] bk=new boolean[k+1];
        Arrays.sort(arr);
        bk[0]=true;
        for (int i = 1; i <= n; i++) {

            for (int j = 1; j <= k; j++) {
                dp[j]=bk[j];
                if(arr[i-1]<=j)
                    dp[j]=bk[j] || bk[j-arr[i-1]];
                }
            bk=dp;
            dp=new boolean[k+1];
        }
        if(bk[k])
            System.out.println("Yes");
        else
            System.out.println("No");
    }

}
