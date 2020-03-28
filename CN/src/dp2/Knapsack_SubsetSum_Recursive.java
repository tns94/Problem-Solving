package dp2;

import java.util.Scanner;
/*
If Subset of sum K is possible in array
 */
public class Knapsack_SubsetSum_Recursive {

   // static int co=0;
    static boolean isSubsetSum(int arr[], int si, int k,boolean[][] dp,boolean[][] dps)
    {

        if(k==0)
            return true;

        if(si<=0)
            return false;

        if(dps[si][k])return dp[si][k];
        //co++;
        boolean ans=false;
        if(k>=arr[si-1])
            ans=isSubsetSum(arr,si-1,k-arr[si-1],dp,dps);

            ans= ans || isSubsetSum(arr,si-1,k,dp,dps);
        dps[si][k]=true;
        dp[si][k]=ans;
       return ans;
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int[] arr=new int[n];
        for (int i = 0; i < n; i++) {
            arr[i]=sc.nextInt();
        }
        int k=sc.nextInt();
        boolean[][] dp=new boolean[n+1][k+1];boolean[][] dps=new boolean[n+1][k+1];
        if(isSubsetSum(arr,n,k,dp,dps))
            System.out.println("Yes");
        else
            System.out.println("No");
      //  System.out.println(co);
    }


}
