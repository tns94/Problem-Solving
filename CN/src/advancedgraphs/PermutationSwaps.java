package advancedgraphs;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class PermutationSwaps {
    static void DFS(List<Integer>[] adjacancyList,List<Integer> component,boolean[] visited,int n,int start)
    {
        visited[start]=true;
        component.add(start);
        if(adjacancyList[start]!=null)
        for (int i:adjacancyList[start])
        {
            if(!visited[i])
            DFS(adjacancyList,component,visited,n,i);
        }
    }
    static List<List> getComponents(List<Integer>[] adjacancyList, int n)
    {
        List<List> components=new ArrayList<>();
        List<Integer> component;
        boolean[] visited=new boolean[n];
        for (int i = 0; i < n; i++) {
            if(!visited[i])
            {
                component=new ArrayList<>();
                DFS(adjacancyList,component,visited,n,i);
                components.add(component);
            }
        }
        return components;
    }
    public static void main(String[] args) throws Exception{
        Reader reader=new Reader();
        int t=reader.nextInt();
        int n,m,a,b;
        int[] p;
        int[] q;
        List<Integer>[] adjancancyList;
        iloop:
        for (int i = 0; i < t; i++) {
            n=reader.nextInt();
            m=reader.nextInt();
            p=new int[n];
            q=new int[n];
            adjancancyList=new List[n];
            for (int j = 0; j < n; j++) p[j]=reader.nextInt()-1;
            for (int j = 0; j < n; j++) q[j]=reader.nextInt()-1;
            for (int j = 0; j < m; j++) {
                a=reader.nextInt()-1;
                b=reader.nextInt()-1;
                if(adjancancyList[a]==null)
                {
                    ArrayList<Integer> list=new ArrayList<>();
                    list.add(b);
                    adjancancyList[a]=list;
                }
                else
                adjancancyList[a].add(b);
                if(adjancancyList[b]==null)
                {
                    ArrayList<Integer> list=new ArrayList<>();
                    list.add(a);
                    adjancancyList[b]=list;
                }
                else
                    adjancancyList[b].add(a);
            }
            List<List> components=getComponents(adjancancyList,n);
            List<Integer> checker;
            for (List<Integer> component:components)
            {
                checker=new ArrayList<>();
                for (int index:component)
                    checker.add(p[index]);
                for (int index:component)
                    checker.remove((Integer)q[index]);
                if(checker.size()!=0)
                {
                    System.out.println("NO");
                    continue iloop;
                }

            }
            System.out.println("YES");


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
