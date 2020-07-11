package fenwicktree;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class WaveSequence {

    static int mod=1000000007;
    static void update(long[][] tree,int subin,int index,long val)
    {
        while(index<=tree.length)
        {
            tree[index][subin]+=val;
            index+=index&(-index);
        }
    }
    static long query(long[][] tree,int subin,int index)
    {
        long ans=0;
        while (index>0)
        {
            ans+=tree[index][subin];
            index-=index&(-index);
        }
        return ans;
    }

    public static void main(String[] args) throws IOException{
        Reader reader=new Reader();
        int n=reader.nextInt();
        long a,b;
        int ai;
        long[][] tree=new long[100001][3];
        int max=0;
        long ans=0;
        for (int i = 0; i < n; i++) {
            ai=reader.nextInt();
            a=(query(tree,0,ai-1)+query(tree,2,ai-1))%mod;
            b=((query(tree,1,max)-query(tree,1,ai))+(query(tree,2,max)-query(tree,2,ai)))%mod;
            ans=(ans+((a+b)%mod))%mod;
            //
            System.out.println((query(tree,1,max)));
            //
            update(tree,0,ai,b);
            update(tree,1,ai,a);
            update(tree,2,ai,1);

            max=Math.max(max,ai);

        }
        System.out.print(ans);
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
