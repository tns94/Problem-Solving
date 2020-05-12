package advancedgraphs;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Connectedhorses {
    static int[][] arr={{-2,-1},{-2,1},{-1,-2},{1,-2},{2,-1},{2,1},{1,2},{-1,2}};

    static void factorial(long[] dp)
    {
        dp[1]=1;
        for (int i = 2; i < dp.length ; i++) {
            dp[i]= (dp[i-1]*i)%1000000007;
        }
    }
    static boolean isHorsePresent(int[][] board,int x,int y,int n,int m)
    {
        if(x>=1 && x<=n && y>=1 && y<=m && board[x][y]>0)
            return true;
        return false;
    }

    static int BFS(List<Integer>[] adjacancyList, boolean[] visited, int start)
    {
        Queue<Integer> queue=new PriorityQueue<>();
        queue.offer(start);
        visited[start]=true;
        int co=1;
        while (!queue.isEmpty()) {
            start = queue.poll();
            if(adjacancyList[start]!=null)
                for (int i:adjacancyList[start]) {
                    if (!visited[i]) {
                        queue.offer(i);
                        visited[i] = true;
                        co++;
                    }
                }
        }
        return co;
    }
    static List<Integer> getComponents(List<Integer>[] adjacancyList, int n)
    {
        List<Integer> component=new ArrayList<>();
        boolean[] visited=new boolean[n];
        for (int i = 1; i < n; i++) {
            if(!visited[i])
                component.add(BFS(adjacancyList,visited,i));//DFS(adjacancyList,visited,n,i));
        }
        return component;
    }
    public static void main(String[] args)throws Exception {
        Reader reader=new Reader();
        int t=reader.nextInt();
        int n,m,q,a,b;
        int[][] board;
        List<Integer>[] adjancancyList;
        long[] dp=new long[125001];
        factorial(dp);
        for (int i = 0; i < t; i++) {
            n=reader.nextInt();
            m=reader.nextInt();
            board=new int[n+1][m+1];
            q=reader.nextInt();
            adjancancyList=new List[q+1];
            for (int j = 1; j <= q; j++) {
                a=reader.nextInt();
                b=reader.nextInt();
                board[a][b]=j;
            }
            for (int j = 1; j <= n; j++) {
                for (int k = 1; k <= m; k++) {
                    if(board[j][k]>0)
                    for (int l = 0; l < 8; l++) {
                        a=j+arr[l][0];b=k+arr[l][1];
                        if(isHorsePresent(board,a,b,n,m))
                        {
                            b=board[a][b];a=board[j][k];
                            if(adjancancyList[a]==null)
                            {
                                ArrayList<Integer> list=new ArrayList<>();
                                list.add(b);
                                adjancancyList[a]=list;
                            }
                            else
                                adjancancyList[a].add(b);
                        }
                    }
                }
            }

            List<Integer> component=getComponents(adjancancyList,q+1);
            long ans=1;
            for (Integer co:component)
                ans= (ans*dp[co] )%1000000007;
            System.out.println(ans);
        }
    }
    static class Reader
    {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader()
        {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public int nextInt() throws IOException
        {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do
            {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }


        private void fillBuffer() throws IOException
        {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException
        {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException
        {
            if (din == null)
                return;
            din.close();
        }
    }
}
