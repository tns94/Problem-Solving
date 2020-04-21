package graphs1;

public class CodingNinja {
    static boolean DFS(String[] graph,int n,int m,int i,int j,boolean[][] visited,int start,char[] searchString)
    {
        if(start==searchString.length)
            return true;
        int a=i-1,b=i+1,c=j-1,d=j+1;
        char ch;
        if(a>=0 && c>=0 && !visited[a][c])
        {
            ch=graph[a].charAt(c);
            if(ch==searchString[start]) {
                visited[a][c]=true;
                if (DFS(graph, n, m, a, c, visited, start + 1, searchString))
                    return true;
                visited[a][c]=false;
            }
        }
        if(a>=0 && !visited[a][j])
        {
            ch=graph[a].charAt(j);
            if(ch==searchString[start]) {
                visited[a][j]=true;
                if (DFS(graph, n, m, a, j, visited, start + 1, searchString))
                    return true;
                visited[a][j]=false;
            }
        }
        if(a>=0 && d<m && !visited[a][d])
        {
            ch=graph[a].charAt(d);
            if(ch==searchString[start]) {
                visited[a][d]=true;
                if (DFS(graph, n, m, a, d, visited, start + 1, searchString))
                    return true;
                visited[a][d]=false;
            }
        }

        if(d<m && !visited[i][d])
        {
            ch=graph[i].charAt(d);
            if(ch==searchString[start]) {
                visited[i][d]=true;
                if (DFS(graph, n, m, i, d, visited, start + 1, searchString))
                    return true;
                visited[i][d]=false;
            }
        }

        if(b<n && d<m &&!visited[b][d])
        {
            ch=graph[b].charAt(d);
            if(ch==searchString[start]) {
                visited[b][d]=true;
                if (DFS(graph, n, m, b, d, visited, start + 1, searchString))
                    return true;
                visited[b][d]=false;
            }
        }


        if(b<n  && !visited[b][j])
        {
            ch=graph[b].charAt(j);
            if(ch==searchString[start]) {
                visited[b][j]=true;
                if (DFS(graph, n, m, b, j, visited, start + 1, searchString))
                    return true;
                visited[b][j]=false;
            }
        }
        if(b<n && c>=0 && !visited[b][c])
        {
            ch=graph[b].charAt(c);
            if(ch==searchString[start]) {
                visited[b][c]=true;
                if (DFS(graph, n, m, b, c, visited, start + 1, searchString))
                    return true;
                visited[b][c]=false;
            }
        }
        if(c>=0  && !visited[i][c])
        {
            ch=graph[i].charAt(c);
            if(ch==searchString[start]) {
                visited[i][c]=true;
                if (DFS(graph, n, m, i, c, visited, start + 1, searchString))
                    return true;
                visited[i][c]=false;
            }
        }
        return false;
    }
    static int solve(String[] graph , int n, int m)
    {
        boolean[][] visited=new boolean[n][m];
        char[] searchString={'C','O','D','I','N','G','N','I','N','J','A'};
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(graph[i].charAt(j)=='C')
                if(DFS(graph,n,m,i,j,visited,1,searchString))
                    return 1;
            }
        }
        return 0;
    }
}
