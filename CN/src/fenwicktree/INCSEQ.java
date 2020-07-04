package fenwicktree;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class INCSEQ {
    static int mod=5000000;
    static void update(int[][] tree,int index,int val,int len)
    {
        while(index<=tree.length)
        {
            tree[index][val]=(tree[index][val]+len)%mod;
            index+=index&(-index);
        }
    }
    static int query(int[][] tree,int index,int val)
    {
        int ans=0;
        while (index>0)
        {
            ans=(ans+tree[index][val])%mod;
            index-=index&(-index);
        }
        return ans;
    }


    public static void main(String[] args) throws IOException{
        Reader reader=new Reader();
        int n=reader.nextInt();
        int k=reader.nextInt();
        int[][] tree=new int[100002][51];
        int[][] dp=new int[100002][51];
        int a;
        int sum=0;
        for(int i=1;i<=n;i++)
        {
            a=reader.nextInt()+1;
            dp[i][1]=1;
            for(int j=1;j<=Math.min(i, k); j++)
            {
                dp[i][j]=(dp[i][j]+query(tree,a-1,j-1))%mod;
                update(tree,a, j, dp[i][j]);
            }
            sum=(sum+dp[i][k])%mod;
        }
        System.out.print(sum);
    }
    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            if (din == null)
                return;
            din.close();
        }
    }
}
