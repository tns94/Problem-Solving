package dp;

import java.util.Scanner;
/*
Minimum integer to start with at [0][0], so that in path to [m][n], sum of path should be >0.
 */
public class MagicGrid {
    static int magiccGrid(int[][] grid,int si,int sj,int ei,int ej,int[][] op,boolean [][] opb)
    {
        if (si==ei && sj==ej )
            return 1;

        if  (si>ei || sj > ej)
            return Integer.MAX_VALUE;

        if (opb[si][sj])
            return op[si][sj];

        int ri=magiccGrid(grid,si+1,sj,ei,ej,op,opb);
        int dow=magiccGrid(grid,si,sj+1,ei,ej,op,opb);


        int ans=Math.min(ri,dow);

        ans=ans-grid[si][sj];
        if (ans<=0)
            ans=1;
        opb[si][sj]=true;
        op[si][sj]=ans;
        return  ans;


    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();
        int r,c,grid[][],op[][];
        boolean opb[][];
        for (int i = 0; i < t; i++) {
            r=sc.nextInt();
            c=sc.nextInt();
            grid=new int[r][c];
            op=new int[r][c];
            opb=new boolean[r][c];

            for (int j = 0; j < r; j++) {
                for (int k = 0; k < c; k++) {
                    grid[j][k]=sc.nextInt();
                }
            }
            int ans=magiccGrid(grid,0,0,r-1,c-1,op,opb);
            System.out.println(ans);

        }
    }


}
