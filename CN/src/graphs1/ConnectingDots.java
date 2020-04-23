package graphs1;

import java.util.Scanner;
/*
If cycle having Minimum 4 same characters
 */
public class ConnectingDots {
    static int[][] cor={{-1,0},{1,0},{0,-1},{0,1}};
    static boolean isValid(int i,int j,int n,int m)
    {
        if(i>=0 && i<n && j>=0 && j<m)
            return true;
        return false;
    }
    static boolean DFS(String[] graph,boolean[][] visited,int[][] count,int n,int m,int i,int j,char color,int k)
    {
        int a,b;
        for (int l = 0; l < 4; l++) {
            a=i-cor[l][0];
            b=j-cor[l][1];
            if (isValid(a, b, n, m)) {
                if (graph[a].charAt(b) == color) {
                    if(visited[a][b] && (k+1)-count[a][b]>=4)
                        return true;
                    if(!visited[a][b]) {
                        visited[a][b] = true;
                        count[a][b]=k+1;
                        if (DFS(graph, visited, count, n, m, a, b, color, k + 1))
                            return true;
                    }
                }
            }
        }
        return false;
    }

   static int solve(String[] board , int n, int m)
    {
        boolean[][] visited=new boolean[n][m];
        int[][] count=new int[n][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(visited[i][j]) continue;
                count[i][j]=1;
                visited[i][j]=true;
                if(DFS(board,visited,count,n,m,i,j,board[i].charAt(j),1))return 1;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int m=sc.nextInt();
        String[] board=new String[n];
        for (int i = 0; i < n; i++) {
            board[i]=sc.next();
        }
        System.out.println(solve(board,n,m));
    }
}