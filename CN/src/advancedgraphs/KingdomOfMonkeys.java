package advancedgraphs;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class KingdomOfMonkeys {
    static long BFS( List<Integer>[] adjacancyList, int[] arr,boolean[] visited, int v, int start)
    {
        Queue<Integer> queue=new LinkedList<>();
        queue.offer(start);
        visited[start]=true;
        long co=0;
        while (!queue.isEmpty()) {
            start = queue.poll();
            co+=arr[start];
            if(adjacancyList[start]!=null)
                for(int i:adjacancyList[start]) {
                    if (!visited[i]) {
                        queue.offer(i);
                        visited[i] = true;

                    }
                }
        }
        return co;
    }
    public static void main(String[] args) throws Exception {
        Reader reader = new Reader();
        int t = reader.nextInt();
        for (int i = 0; i < t; i++) {
            int n = reader.nextInt();
            int m = reader.nextInt();
            int a, b;
            boolean[] visited = new boolean[n];
            int[] arr=new int[n];
            List<Integer>[] adjacancyList = new List[n];
            for (int j = 0; j < m; j++) {
                a = reader.nextInt() - 1;
                b = reader.nextInt() - 1;
                if (adjacancyList[a] == null) {
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(b);
                    adjacancyList[a] = list;
                } else
                    adjacancyList[a].add(b);
                if (adjacancyList[b] == null) {
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(a);
                    adjacancyList[b] = list;
                } else
                    adjacancyList[b].add(a);
            }
            for (int j = 0; j < n; j++) arr[j]=reader.nextInt();
            long max=0;
            for (int j = 0; j < n; j++) {
                if(!visited[j])
                    max=Math.max(max,BFS(adjacancyList,arr,visited,n,j));
            }
            System.out.println(max);
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
