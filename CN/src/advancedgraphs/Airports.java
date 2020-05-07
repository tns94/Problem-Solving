package advancedgraphs;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.*;

public class Airports {
    static boolean unionFind(int v1,int v2,int[] parentMatrix)
    {
        int a=v1,b=v2;
        while(parentMatrix[a]!=a)
            a=parentMatrix[a];
        while (parentMatrix[b]!=b)
            b=parentMatrix[b];
        if(a==b) return false;
        parentMatrix[b]=a;
        return true;
    }
    public static void main(String[] args) throws Exception{
        Reader reader=new Reader();
        int t=reader.nextInt();
        for (int i = 0; i < t; i++) {
            int n=reader.nextInt();
            int m=reader.nextInt();
            int a=reader.nextInt();
            int[] parentMatrix=new int[n+1];
            for (int j = 0; j <= n; j++) parentMatrix[j]=j;
            int x,y,wt;
            List<Edge> edgeList=new ArrayList<>();
            for (int j = 0; j < m; j++) {
                 Edge edge=new Edge();
                 edge.x=reader.nextInt();
                 edge.y=reader.nextInt();
                 edge.wt=reader.nextInt();
                 edgeList.add(edge);
            }
            Collections.sort(edgeList,(o1, o2) -> o1.wt-o2.wt);
            long ans=0;
            int edgeco=0;
            for (Edge edge:edgeList)
            {
                if(edge.wt>a || edgeco==n-1) break;
                if(edge.wt<a)
                {
                    if(unionFind(edge.x,edge.y,parentMatrix))
                    {
                        ans+=edge.wt;
                        edgeco++;
                    }
                }
            }
            int co=0;
            for (int j = 1; j <= n; j++) {
                if(parentMatrix[j]==j)
                    co++;
            }
            ans+= co*a;
            System.out.println("Case #"+(i+1)+": "+ans+" "+co);

        }
    }
    static class Edge{int x,y,wt;}
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
