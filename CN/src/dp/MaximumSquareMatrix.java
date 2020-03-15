package dp;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
//Find maximum Square in matrix with all Zeros
public class MaximumSquareMatrix {


    public static int findMaxSquareWithAllZeros(int[][] input){
        int m=input.length;
        int n=input[0].length;
        int max=0;
        int dp[][] =new int[m][n];
        for (int i = 0; i < m; i++)
            dp[i][0]=input[i][0]==0?1:0;
        for (int j = 0; j < n; j++)
            dp[0][j]=input[0][j]==0?1:0;


        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if(input[i][j]==0) {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                    max = dp[i][j] > max ? dp[i][j] : max;
                }
                else
                    dp[i][j]=0;
            }
        }
        return max;

    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int m=sc.nextInt();
        int n=sc.nextInt();
            int [][] mat=new  int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                mat[i][j]=sc.nextInt();
            }
        }
        System.out.println(findMaxSquareWithAllZeros(mat));
    }

}
