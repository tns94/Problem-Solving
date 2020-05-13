package advancedgraphs;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.*;

public class MonkandIslands {
    static int BFS( List<Integer>[] adjacancyList, boolean[] visited, int v, int start)
    {
        Queue<Integer> queue=new LinkedList<>();
        int[] level=new int[v];

        queue.offer(start);
        visited[start]=true;
        while (!queue.isEmpty()) {
            start = queue.poll();
            if(adjacancyList[start]!=null)
            for(int i:adjacancyList[start]) {
                if (!visited[i]) {
                    level[i]=level[start]+1;
                    if(i==v-1)
                        return level[i];
                    queue.offer(i);
                    visited[i] = true;

                }
            }
        }
        return-1;
    }
    public static void main(String[] args) throws Exception {
        Reader reader=new Reader();
        int t=reader.nextInt();
        for (int i = 0; i < t; i++) {
            int n=reader.nextInt();
            int m=reader.nextInt();
            int a,b;
            boolean[] visited=new boolean[n];
            List<Integer>[] adjacancyList=new List[n];
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
                if(adjacancyList[b]==null)
                {
                    ArrayList<Integer> list=new ArrayList<>();
                    list.add(a);
                    adjacancyList[b]=list;
                }
                else
                    adjacancyList[b].add(a);
            }
            System.out.println(BFS(adjacancyList,visited,n,0));
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
