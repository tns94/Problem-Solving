package graphs1;
/*
maximum adjacent 1s in binary matrix
 */
public class LargestPiece {
    static int[][] cor = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static boolean isValid(int i, int j, int n, int m) {
        if (i >= 0 && i < n && j >= 0 && j < m)
            return true;
        return false;
    }

    static int DFS(String[] graph, boolean[][] visited, int n, int i, int j) {
        int a, b;
        int co=0;
        for (int l = 0; l < 4; l++) {
            a = i - cor[l][0];
            b = j - cor[l][1];
            if (isValid(a, b, n, n)) {
                if (graph[a].charAt(b) == '1' && !visited[a][b]) {
                    visited[a][b] = true;
                    co+= DFS(graph, visited, n, a, b) + 1;
                }
            }
        }
        return co;
    }

    public int solve(int n, String cake[]) {
        boolean[][] visited = new boolean[n][n];
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (cake[i].charAt(j) == '1' && !visited[i][j]) {
                    visited[i][j] = true;
                    max = Math.max(max, DFS(cake, visited, n, i, j)+1);
                }
            }
        }
        return max;
    }
}
