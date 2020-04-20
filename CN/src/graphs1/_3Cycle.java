package graphs1;

import java.util.Scanner;

public class _3Cycle {
    public int solve(int n,int m,int U[],int V[]){
        int[][] matrix=new int[n+1][n+1];
        for (int j = 0; j < m; j++) matrix[U[j]][V[j]]=matrix[V[j]][U[j]]=1;
        int co=0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if(matrix[i][j]==1)
                {
                    for (int k = 1; k <= n; k++) {
                        if(k!=i)
                            if(matrix[j][k]==1 && matrix[i][k]==1)
                                co++;
                    }
                }

            }
        }

        return co/6;
    }

    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        int n=sc.nextInt();
        int m=sc.nextInt();
        int[] u=new int[m];
        int[] v=new int[m];
        for (int i = 0; i < m; i++) {

        }

    }
}
