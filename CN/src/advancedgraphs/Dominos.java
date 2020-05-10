package advancedgraphs;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Dominos {
    static int DFS(Stack<Integer> stack,List<Integer>[] adjacancyList,boolean[] visited,int start)
    {
       visited[start]=true;
        int co=1;
        if(adjacancyList[start]!=null)
            for (int i:adjacancyList[start])
            {
                if(!visited[i])
                    co+=DFS(stack,adjacancyList, visited, i);
            }
        stack.push(start);
        return co;
    }
    static void DFS2(List<Integer>[] adjacancyList,boolean[] visited,int start)
    {
        visited[start]=true;

        if(adjacancyList[start]!=null)
            for (int i:adjacancyList[start])
            {
                if(!visited[i])
                    DFS2(adjacancyList, visited, i);
            }
    }
    static int getComponentsCount(List<Integer>[] adjacancyList,int n)
    {
        boolean[] visited=new boolean[n];
        Stack<Integer> stack=new Stack<>();
        for (int i = 0; i < n; i++) {
            if(!visited[i])
                DFS(stack,adjacancyList,visited, i);
        }

        visited=new boolean[n];
        int start,co=0;
        while(!stack.empty())
        {
            start=stack.pop();
            if(!visited[start])
            {
                DFS2(adjacancyList,visited,start);
                co++;
            }
        }
        return co;
    }
    public static void main(String[] args)throws Exception {
        Reader reader=new Reader();
        int a,b,n,m,t=reader.nextInt();
        List<Integer>[] adjacancyList;
        for (int i = 0; i < t; i++) {
            n=reader.nextInt();
            m=reader.nextInt();
            adjacancyList=new List[n];
            for (int j = 0; j < m; j++) {
                a=reader.nextInt()-1;
                b=reader.nextInt()-1;
                if(adjacancyList[a]==null)
                {
                    ArrayList<Integer> list=new ArrayList<>();
                    list.add(b);
                    adjacancyList[a]=list;
                }
                else
                    adjacancyList[a].add(b);
            }

            System.out.println(getComponentsCount(adjacancyList,n));

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
