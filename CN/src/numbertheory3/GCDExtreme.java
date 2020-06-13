package numbertheory3;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class GCDExtreme {
    static long[] eulerTF(int n)
    {
        long[] etf=new long[n+1];
        for (int i = 1; i <= n; i++) {
            etf[i]=i;
        }
        for (int i = 2; i <= n ; i++) {
            if(etf[i]==i)
            {
                etf[i]=i-1;
                for (int j = 2*i; j <= n; j+=i) {
                    etf[j]=(etf[j]*(i-1))/i;
                }
            }
        }
        return etf;
    }
    public static void main(String[] args)throws Exception {
        Reader reader = new Reader();
        int n;
        StringBuilder ans=new StringBuilder();
        long[] etf=eulerTF(1000001);
        long[] result=new long[1000002];
        for (int i = 1; i < 1000001; i++) {
            for (int j = 2; i*j <= 1000001; j++) {
                result[i*j] += i * etf[j];
            }
        }
        for (int i = 1; i < 1000002; i++) {
            result[i] += result [i-1];
        }
        while ((n=reader.nextInt())!=0)
        {
            ans.append(result[n]+"\n");
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
