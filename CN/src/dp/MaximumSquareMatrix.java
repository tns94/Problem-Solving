package dp;

import java.util.Scanner;
//Find maximum Square in matrix with all Zeros
public class MaximumSquareMatrix {
    static boolean isValidZeroMarix(int[][] input , int ii,int jj,int kk,int ll)
    {
        for (int i = ii; i <= kk; i++) {
            for (int j = jj; j <= ll; j++) {
                if (input[i][j]==1)
                    return false;
            }
        }
        return true;
    }

    public static int findMaxSquareWithAllZeros(int[][] input){
        int m=input.length;
        int n=input[0].length;
        int max=0;
        boolean[][] bool=new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                for (int k = i,l=j; k < m && l < n; k++,l++) {
                        if(isValidZeroMarix(input,i,j,k,l))
                            max=max<(k-i+1)?k-i+1:max;
                }
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
