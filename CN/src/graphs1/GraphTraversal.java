package graphs1;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class GraphTraversal {
    static void DFS(StringBuilder str,int[][] matrix,int v,int current,boolean[] visited)
    {
        str.append(current+" ");
        visited[current]=true;
        for (int i = 0; i < v; i++) {
            if(matrix[current][i]==1 && !visited[i])
                DFS(str,matrix,v,i,visited);
        }
    }

    static void BFS(StringBuilder str,int[][] matrix, boolean[] visited,int v, int current,Queue<Integer> queue)
    {
        queue.offer(current);
        visited[current]=true;
        while (!queue.isEmpty()) {
            current = queue.poll();
            str.append(current + " ");
            for (int i = 0; i < v; i++) {
                if (matrix[current][i] == 1 && !visited[i]) {
                    queue.offer(i);
                    visited[i] = true;
                }
            }
        }
    }
    public static void main(String[] args) throws Exception{
        Reader reader=new Reader();
        int v=reader.nextInt();
        int e=reader.nextInt();
        int a,b;
        int[][] matrix=new int[v][v];
        boolean[] visited=new boolean[v];
        for (int i = 0; i < e; i++) {
            a=reader.nextInt();
            b=reader.nextInt();
            matrix[b][a]=matrix[a][b]=1;
        }
        StringBuilder str=new StringBuilder();
        Queue<Integer> queue=new LinkedList<>();
        int current;
        for (int i = 0; i < v; i++) {
            if(!visited[i])
               BFS(str,matrix,visited,v,i,queue);
        }
        System.out.println("BFS\n"+str);
        visited=new boolean[v];
        str=new StringBuilder();
        DFS(str,matrix,v,0,visited);
        System.out.println("DFS\n"+str);
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

        public Reader(String file_name) throws IOException
        {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException
        {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1)
            {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
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

        public long nextLong() throws IOException
        {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException
        {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');

            if (c == '.')
            {
                while ((c = read()) >= '0' && c <= '9')
                {
                    ret += (c - '0') / (div *= 10);
                }
            }

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
