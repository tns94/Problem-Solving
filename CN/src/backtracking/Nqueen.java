package backtracking;

import java.util.Scanner;

public class Nqueen {
    static boolean isPossible(int[][] arr,int n, int x,int y)
    {
        int i=x-1,j=y-1;
        while(i>=0 && j>=0 && i<n && j<n)
        {
            if(arr[i][j]==1)
                return false;
            i-=1;j-=1;
        }
        i=x-1;j=y;
        while(i>=0 && j>=0 && i<n && j<n)
        {

            if(arr[i][j]==1)
                return false;
            i-=1;
        }
        i=x-1;j=y+1;
        while(i>=0 && j>=0 && i<n && j<n)
        {

            if(arr[i][j]==1)
                return false;
           i-=1; j+=1;
        }
        return true;
    }
    static void print(int[][] arr,int n)
    {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(arr[i][j]+" ");
            }
        }
        System.out.println();
    }
    static void nQueen(int[][] arr, int n,int row)
    {
        if (row==n)
        {
            print(arr,n);
            return;
        }

        for (int i = 0; i < n; i++) {
            if(isPossible(arr,n,row,i))
            {
                arr[row][i]=1;
                nQueen(arr,n,row+1);
                arr[row][i]=0;
            }
        }

    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int[][] arr=new int[n][n];
        nQueen(arr,n,0);
    }
}
