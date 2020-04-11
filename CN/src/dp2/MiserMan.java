package dp2;

import java.util.Scanner;

public class MiserMan {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int m=sc.nextInt();
        int n=sc.nextInt();
        int[][] arr=new int[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                arr[i][j]=sc.nextInt();
        int[] dp=new int[n];
        int[] bk=new int[n];
        for (int i = 0; i < n; i++)
            bk[i]=arr[0][i];
        int ans;
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {

                ans=bk[j]+arr[i][j];
                if(j>0)
                    ans=Math.min(ans,arr[i][j]+bk[j-1]);
                if(j<n-1)
                    ans=Math.min(ans,arr[i][j]+bk[j+1]);
                dp[j]=ans;
            }
            bk=dp;
            dp=new int[n];
        }
        ans=Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            ans=Math.min(bk[i],ans);
        }
        System.out.println(ans);

    }
}
