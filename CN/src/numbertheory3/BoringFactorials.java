package numbertheory3;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class BoringFactorials {
    static int power(int a,int b,int mod)
    {
        if(b==0)
            return 1;
        long val=power(a,b/2,mod);
        long ans= (val * val)%mod;
        if(b%2!=0)
            ans = (ans * a)%mod;
        return (int)ans;
    }

    public static void main(String[] args) throws Exception {
        Reader sc=new Reader();
        int t=sc.nextInt();
        StringBuilder ans=new StringBuilder();
        while (t-- >0){
            int n=sc.nextInt();
            int p=sc.nextInt();
            if(n>=p){
                ans.append(0+"\n");
                continue;
            }
            long r=1;
            for (int i = n+1; i < p; i++) {
                r = (r*power(i,p-2,p))%p;
            }
            ans.append(p-r+"\n");
        }
        System.out.print(ans);
    }
    static class Reader{
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

        public long nextLong() throws IOException
        {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
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
