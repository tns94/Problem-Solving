package dp;

import java.util.Scanner;

public class MaximumSumRectangle {

    static int largestSubArray(int arr[])
    {
        int best=Integer.MIN_VALUE,curr=0;
        for (int i = 0; i < arr.length; i++) {
            curr+=arr[i];
            if(curr>best)
                best=curr;
            if(curr<0)
                curr=0;

        }
        return best;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int m=sc.nextInt(),n=sc.nextInt();
        int [][] mat=new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                mat[i][j]=sc.nextInt();
            }
        }

        int arr[];
        int max=Integer.MIN_VALUE,te=0;
        for (int l = 0; l < m; l++) {
            arr=new int[n];
            for (int r = l; r < n; r++) {

                for (int k = 0; k < m; k++) {
                    arr[k]+=mat[k][r];
                }
                te=largestSubArray(arr);
                if(te>max)
                    max=te;

            }
        }
        System.out.println(max);
    }
}
