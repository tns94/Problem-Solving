package graphs2;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
/*
  MST using Kruskal's Algorithm
 */
public class Kruskal {

    static boolean hasPath(int v1,int v2,int[] parentMatrix)
    {
        int a=v1,b=v2;
        while(parentMatrix[a]!=a)
            a=parentMatrix[a];
        while (parentMatrix[b]!=b)
            b=parentMatrix[b];
        if(a==b) return true;
        parentMatrix[b]=a;
        return false;
    }
    public static void main(String[] args) throws Exception {
        Reader reader=new Reader();
        int v=reader.nextInt();
        int e=reader.nextInt();
        int[] parentMatrix=new int[v];
        for (int i = 0; i < v; i++) parentMatrix[i]=i;
        ArrayList<Edge> edgeList=new ArrayList<>();
        int a,b;
        for (int i = 0; i < e; i++) {
            Edge edge=new Edge();
            a=reader.nextInt();
            b=reader.nextInt();
            edge.v1=Math.min(a,b);
            edge.v2=Math.max(a,b);
            edge.wt=reader.nextInt();
            edgeList.add(edge);
        }
        int co=0;
        Collections.sort(edgeList,(o1, o2) -> o1.wt-o2.wt);
        StringBuilder str=new StringBuilder();
        for (Edge edge:edgeList)
        {
            if(!hasPath(edge.v1,edge.v2,parentMatrix))
            {
                co++;
                str.append(edge.v1).append(" ").append(edge.v2).append(" ").append(edge.wt+"\n");
            }
            if(co==v-1)
                break;
        }
        System.out.print(str);
    }

    static class Edge {int v1, v2, wt;}
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
