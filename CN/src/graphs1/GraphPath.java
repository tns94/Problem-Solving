package graphs1;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class GraphPath {
    static void BFS(ArrayList<Integer> com, int[][] matrix, boolean[] visited, int v, int current, Queue<Integer> queue)
    {
        queue.offer(current);
        visited[current]=true;
        while (!queue.isEmpty()) {
            current = queue.poll();
            com.add(current);//str.append(current + " ");
            for (int i = 0; i < v; i++) {
                if (matrix[current][i] == 1 && !visited[i]) {
                    queue.offer(i);
                    visited[i] = true;
                }
            }
        }
    }
    static boolean isConnectedGraph(int[][] matrix, boolean[] visited,int v, int current)
    {
        Queue<Integer> queue=new LinkedList<>();
        queue.offer(current);
        visited[current]=true;
        while (!queue.isEmpty()) {
            current = queue.poll();
            for (int i = 0; i < v; i++) {
                if (matrix[current][i] == 1 && !visited[i]) {
                    queue.offer(i);
                    visited[i] = true;
                }
            }
        }
        for (int i = 0; i < v; i++) {
            if(visited[i]==false)
                return false;
        }
        return true;
    }
    static boolean pathBFS(int[] track,int[][] matrix, boolean[] visited,int v, int start,int end)
    {
        Queue<Integer> queue=new LinkedList<>();
        queue.offer(start);
        visited[start]=true;
        while (!queue.isEmpty()) {
            start = queue.poll();
            for (int i = 0; i < v; i++) {
                if (matrix[start][i] == 1 && !visited[i]) {
                    queue.offer(i);
                    track[i]=start;
                    visited[i] = true;
                    if(i==end)
                        return true;

                }

            }
        }
        return false;
    }
    static boolean pathDFS(StringBuilder path, int[][] matrix, int v, int start, int end, boolean[] visited)
    {
        if(start==end)
        {
            path.append(start+" ");
            return true;
        }
        visited[start]=true;
        for (int i = 0; i < v; i++) {
            if(matrix[start][i]==1 && !visited[i])
               if(pathDFS(path,matrix,v,i,end,visited))
                  {
                      path.append(start+" ");
                      return true;
                  }
        }
        return false;
    }

    static boolean hasPath(int[][] matrix, boolean[] visited,int v, int start,int end)
    {
        Queue<Integer> queue=new LinkedList<>();
        queue.offer(start);
        visited[start]=true;
        while (!queue.isEmpty()) {
            start = queue.poll();
            for (int i = 0; i < v; i++) {
                if (matrix[start][i] == 1 && !visited[i]) {
                    if(i==end)
                        return true;
                    queue.offer(i);
                    visited[i] = true;
                }
            }
        }
        return false;
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
       // int start=reader.nextInt();
       // int end=reader.nextInt();


       //getAllConnectedComponents
        StringBuilder str=new StringBuilder();
        Queue<Integer> queue=new LinkedList<>();
        ArrayList<Integer> list=new ArrayList<>();
        for (int i = 0; i < v; i++) {
            if(!visited[i])
            {
                BFS(list,matrix,visited,v,i,queue);
                Collections.sort(list);
                for (int j = 0; j < list.size(); j++) {
                    str.append(list.get(j)+" ");
                }
                list.clear();
                str.append("\n");
            }
        }
        System.out.print(str);

        //isConnectedGraph
        //System.out.println(isConnectedGraph(matrix,visited,v,0));


        //StringBuilder str=new StringBuilder();
        //Get Path by BFS
       /* int[] track=new int[v];
        if(pathBFS(track,matrix,visited,v,start,end))
        { str.append(end+" ");
            while(true)
            {
                str.append(track[end]+" ");end=track[end];
                if(end==start)break;
            }
            System.out.print(str);
        }*/

       /*//Get Path DFS
        boolean ans= pathDFS(str,matrix,v,start,end,visited);
        if (ans)
            System.out.print(str);*/

        //hasPath
            // System.out.println(hasPath(matrix,visited,v,start,end));

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
