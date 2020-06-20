package bitmasking;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class BankRobbery {
    public static void main(String[] args)throws IOException {
        Reader reader=new Reader();
        int n=reader.nextInt();
        int m=reader.nextInt();
        Pair[] pairs=new Pair[n];
        for (int i = 0; i < n; i++) {
            pairs[i]=new Pair(reader.nextLong(),reader.nextLong());
        }
        Arrays.sort(pairs,(o1, o2) -> (int)(o1.v-o2.v));
        long[][][] dp=new long[2][n+1][m+1];

        int[] primes={1,2,3,5,7,11,13,17,19,23,29};

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[0][i][j]=dp[0][i-1][j];
                if(j >= pairs[i-1].w)
                {
                    dp[0][i][j]=Math.max(dp[0][i][j],dp[0][i-1][(int)(j-pairs[i-1].w)]+pairs[i-1].v);
                }
            }
        }
        for (int prime = 1; prime < 11; prime++) {
            int p=prime%2;
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    dp[p][i][j]=dp[p][i-1][j];
                    if(j>=pairs[i-1].w)
                    {
                        dp[p][i][j]=Math.max(dp[p^1][i-1][(int)(j-pairs[i-1].w)]+(pairs[i-1].v * primes[prime]),Math.max(dp[p][i-1][(int)(j-pairs[i-1].w)]+pairs[i-1].v,dp[p][i][j]));
                    }
                }
            }
        }
        System.out.print(dp[0][n][m]);
    }
    static class Pair{long v,w;

        public Pair(long v, long w) {
            this.v = v;
            this.w = w;
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
        } public long nextLong() throws IOException
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
        public String readLine() throws IOException
        {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1)
            {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
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
