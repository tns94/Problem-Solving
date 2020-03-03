package backtracking;

import java.util.Scanner;

public class Sudoku {
    static boolean checkPart(int[][] board,int x, int y,int num)
    {
        if (x==0 && y==5 && num ==6)
            System.out.println();
        int p=0,q=0,r=0,s=0;
        for (int i = 0,j=0; i < 9; i+=3,j+=3) {
            if(x>=i && x<i+3)
            {
                p=i;
                r=i+3;
            }
            if (y>=j && y<j+3)
            {
                q=j;
                s=j+3;
            }
        }
        for (int i = p; i < r ; i++) {
             for (int j = q; j < s; j++)
                if (board[i][j]==num)
                return false;
        }
        return true;
    }

    static boolean isPossible(int[][] board,int i,int j,int num)
    {
        for (int k = 0; k < 9; k++) {
            if (board[k][j]==num || board[i][k]==num)
                return false;
        }
        return checkPart(board,i,j,num);
    }
    static void print(int[][] arr,int n)
    {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }


    public static boolean sudokuSolver(int board[][])
    {
        int i=0,j=0;boolean flag=false;

       loop: for ( i = 0; i < 9; i++) {
            for ( j = 0; j < 9; j++) {
                if (board[i][j]==0)
                {

                    flag=true;
                    break loop;
                }
            }
         }

        if(!flag)
            return true;

        for (int k = 1; k <=9 ; k++) {
            if (isPossible(board,i,j,k))
            {
                board[i][j]=k;
                if (sudokuSolver(board))
                    return true;
                board[i][j]=0;
             }
        }
        return false;
    }


    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int arr[][]=new int[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                arr[i][j]=sc.nextInt();
            }
        }
        sudokuSolver(arr);
        print(arr,9);
    }
}
