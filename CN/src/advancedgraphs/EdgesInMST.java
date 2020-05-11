package advancedgraphs;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class EdgesInMST {

    static void processMultiples(int from,int to,ArrayList<Edge> edgeList,int[] parentMatrix)
    {
        for (int i = from; i < to; i++) {
            Edge edge=edgeList.get(i);
            if(hasPath(edge.v1,edge.v2,parentMatrix))
                edge.ans="none";
        }

        int[] tempParent;
        int a,b;
        boolean notAny;
        for (int i = from; i < to; i++) {
            Edge edge1=edgeList.get(i);
            if(edge1.ans==null)
            {
                tempParent= Arrays.copyOf(parentMatrix,parentMatrix.length);
                notAny=false;
                for (int j = from; j < to; j++) {
                    if(j!=i)
                    {
                        Edge edge2=edgeList.get(j);
                        setPath(edge2.v1,edge2.v2,tempParent);
                        if(isSame(edge1,edge2,tempParent))
                        {
                            edge1.ans="at least one";
                            notAny=true;
                            break;
                        }
                    }
                }
                if(!notAny)
                {
                    edge1.ans="any";
                    setPath(edge1.v1,edge1.v2,parentMatrix);
                }
            }
        }
        for (int i = from; i < to; i++) {
            Edge edge=edgeList.get(i);
            if(edge.ans.equals("at least one"))
            {
                setPath(edge.v1,edge.v2,parentMatrix);
                break;
            }
        }
    }

    static boolean hasPath(int v1,int v2,int[] parentMatrix)
    {
        int a=v1,b=v2;
        while(parentMatrix[a]!=a)
            a=parentMatrix[a];
        while (parentMatrix[b]!=b)
            b=parentMatrix[b];
        if(a==b) return true;
        return false;
    }
    static void setPath(int v1,int v2,int[] parentMatrix)
    {
        int a=v1,b=v2;
        while(parentMatrix[a]!=a)
            a=parentMatrix[a];
        while (parentMatrix[b]!=b)
            b=parentMatrix[b];
        if(a==b) return;
        parentMatrix[b]=a;
    }
    static boolean isSame(Edge edge1,Edge edge2,int[] parentMatrix)
    {
        int x1=edge1.v1,y1=edge1.v2;
        int x2=edge2.v1,y2=edge2.v2;

        while(parentMatrix[x1]!=x1)
            x1=parentMatrix[x1];
        while (parentMatrix[y1]!=y1)
            y1=parentMatrix[y1];

        while(parentMatrix[x2]!=x2)
            x2=parentMatrix[x2];
        while (parentMatrix[y2]!=y2)
            y2=parentMatrix[y2];

        if(x1==x2 && y1==y2)
            return true;
        return false;

    }
    public static void main(String[] args) throws Exception {
        Reader reader=new Reader();
        int v=reader.nextInt();
        int e=reader.nextInt();
        int[] parentMatrix=new int[v];
        for (int i = 0; i < v; i++) parentMatrix[i]=i;
        ArrayList<Edge> edgeList=new ArrayList<>();
        int[] weightCounter=new int[1000001];
        int a,b;
        for (int i = 0; i < e; i++) {
            Edge edge=new Edge();
            a=reader.nextInt()-1;
            b=reader.nextInt()-1;
            edge.v1=Math.min(a,b);
            edge.v2=Math.max(a,b);
            edge.wt=reader.nextInt();
            weightCounter[edge.wt]++;
            edgeList.add(edge);
        }
        int co=0;
        ArrayList<Edge> nonSorted=new ArrayList<>(edgeList);
        Collections.sort(edgeList,(o1, o2) -> o1.wt-o2.wt);
        StringBuilder str=new StringBuilder();

        for (int i = 0; i < e; i++) {
            Edge edge=edgeList.get(i);
            if(weightCounter[edge.wt]==1)
            {
                if(!hasPath(edge.v1,edge.v2,parentMatrix))
                {
                    setPath(edge.v1,edge.v2,parentMatrix);
                    edge.ans="any";
                }
                else
                    edge.ans="none";
            }
            if(weightCounter[edge.wt]>1)
            {
                int wco=i+weightCounter[edge.wt];
                processMultiples(i,wco,edgeList,parentMatrix);
                i=wco-1;
            }
        }
        for (Edge edge:nonSorted)
            str.append(edge.ans+"\n");
        System.out.print(str);

    }

    static class Edge {int v1, v2, wt;String ans;}
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
