package dp1;

import java.util.Scanner;

public class AlyonaSpreadsheet {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int m=sc.nextInt();
        int n=sc.nextInt();
        int arr[][]=new int[m][n];
        int dp[]=new int[n];
        int bk[]=new int[n];
        int min[]=new int[m];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j]=sc.nextInt();
            }
        }


        for (int i = 1; i < m; i++) {
            min[i]=Integer.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                if(arr[i][j]>=arr[i-1][j])
                    bk[j]=dp[j];
                else
                    bk[j]=i;
                min[i]=min[i]>bk[j]?bk[j]:min[i];
            }
            dp=bk;
        }




        int k=sc.nextInt();
        int l,r;
        StringBuilder str=new StringBuilder();
        for (int i = 0; i < k; i++) {
            l=sc.nextInt()-1;
            r=sc.nextInt()-1;
            if(min[r]<=l)
                str.append("Yes").append("\n");
            else
                str.append("No").append("\n");

        }
        System.out.print(str);
    }
}
