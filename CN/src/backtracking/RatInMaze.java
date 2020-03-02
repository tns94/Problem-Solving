package backtracking;

public class RatInMaze {
        static void print(int[][] arr,int n)
        {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(arr[i][j]+" ");
                }
            }
            System.out.println();
        }

        static void rat(int[][] arr,int op[][],int n,int i,int j)
        {

            if (!(i>=0 && j>=0 && i<n && j<n))
                return;
            if (arr[i][j]==0 || op[i][j]==1)
                return;
            if (i==n-1 && j==n-1)
            {
                op[i][j]=1;
                print(op,n);
                op[i][j]=0;
                return;
            }

            op[i][j]=1;

            rat(arr,op,n,i+1,j);

            rat(arr,op,n,i,j+1);

            rat(arr,op,n,i-1,j);

            rat(arr,op,n,i,j-1);
            op[i][j]=0;

        }

    public static void main(String[] args) {
            int maze[][]={{1,0,1},{1,1,1},{1,1,1}};
            int n=maze[0].length;
            int[][] op=new int[n][n];
            rat(maze,op,n,0,0);


    }

}
