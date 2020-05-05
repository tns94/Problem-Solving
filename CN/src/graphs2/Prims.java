package graphs2;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.PriorityQueue;

public class Prims {
    static int getMin(boolean[] visited,int[] weight,int v)
    {
        int min=-1;
        for (int i = 0; i < v; i++) {
            if(!visited[i] && (min==-1 || (weight[min] > weight[i])))
                min=i;
        }
        return min;
    }
    static void prims2(int[][] matrix,boolean[] visited,int[] parent,int[] weight,int v)
    {
        for (int i = 1; i < v; i++) {
            int min=getMin(visited,weight,v);
            visited[min]=true;
            for (int j = 0; j < v; j++) {
                if (matrix[min][j] > 0 && !visited[j]) {
                    if(weight[j]>matrix[min][j])
                    {
                        parent[j]=min;
                        weight[j]=matrix[min][j];
                    }
                }
            }
            
        }
    }
    static void prims(int[][] matrix,boolean[] visited,int[] parent,int[] weight,int v)
    {
        PriorityQueue<Vertex> queue=new PriorityQueue<>((o1, o2) -> o1.wt-o2.wt);
        Vertex vertex=new Vertex();
        vertex.vertex=0;
        queue.offer(vertex);
        while(!queue.isEmpty()){
            int cur=queue.poll().vertex;
            visited[cur]=true;

            for (int i = 0; i < v; i++) {
                if (matrix[cur][i] > 0 && !visited[i]) {

                    if(weight[i]>matrix[cur][i])
                    {
                        Vertex vertex1 = new Vertex();
                        vertex1.vertex = i;
                        queue.remove(vertex1);
                        parent[i]=cur;
                        weight[i]=matrix[cur][i];
                        vertex1.wt = matrix[cur][i];
                        queue.offer(vertex1);
                    }
                }
            }

        }
    }
    public static void main(String[] args) throws Exception {
        Reader reader=new Reader();
        int v=reader.nextInt();
        int e=reader.nextInt();
        int a,b;
        int[][] matrix=new int[v][v];
        for (int i = 0; i < e; i++) {
            a=reader.nextInt();b=reader.nextInt();
            matrix[a][b]=matrix[b][a]=reader.nextInt();
        }
        int[] parent=new int[v];
        int[] weight=new int[v];
        for (int i = 0; i < v; i++) weight[i]=Integer.MAX_VALUE;
        boolean[] visited=new boolean[v];
        prims(matrix,visited,parent,weight,v);
        //prims2(matrix,visited,parent,weight,v);
        StringBuilder str=new StringBuilder();
        for (int i = 1; i < v; i++) {
            a=Math.min(i,parent[i]);
            b=Math.max(i,parent[i]);
            str.append(a+" "+b+" "+weight[i]+"\n");
        }
        System.out.print(str);
    }

    static class Vertex{int  vertex, wt;
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Vertex vertex1 = (Vertex) o;
            return vertex == vertex1.vertex;
        }

        @Override
        public int hashCode() {
            return Objects.hash(vertex);
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
