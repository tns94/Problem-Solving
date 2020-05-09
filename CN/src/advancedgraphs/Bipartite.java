package advancedgraphs;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Bipartite {
    static boolean isBipartite(List<Integer>[] adjacancyList,int n)
    {
        List<Integer> red=new ArrayList<>();
        List<Integer> black=new ArrayList<>();
        Queue<Integer> queue=new LinkedList<>();
        queue.offer(0);
        red.add(0);
        int ele;
        while(!queue.isEmpty())
        {
            ele=queue.poll();
            if(red.contains(ele))
            {
                if(adjacancyList[ele]!=null)
                for (int i:adjacancyList[ele]) {
                    if (!red.contains(i) && !black.contains(i)) {
                        black.add(i);
                        queue.offer(i);
                    } else if (red.contains(i))
                        return false;
                }
            }
            else
            {
                if(adjacancyList[ele]!=null)
                    for (int i:adjacancyList[ele]) {
                        if (!red.contains(i) && !black.contains(i)) {
                            red.add(i);
                            queue.offer(i);
                        } else if (black.contains(i))
                            return false;
                    }
            }
        }
        return true;
    }
    public static void main(String[] args) throws Exception{
        Reader reader =new Reader();
        int n=reader.nextInt();
        int m=reader.nextInt();
        List<Integer>[] adjacancyList=new List[n];
        int a,b;
        for (int j = 0; j < m; j++) {
            a=reader.nextInt();
            b=reader.nextInt();
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
        System.out.println(isBipartite(adjacancyList,n));
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
