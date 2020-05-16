package bitmasking;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
/*
N integers describing the preferences of one student. 1 at i'th (0 <= i < N) position denotes that this student likes i'th candy , 0 means he doesn't.
Return the number of ways one can distribute these N candies to his N students such that every student gets exactly one candy he likes.``
 */
public class Candy {

    static long solve(int[][] like,int N){
        long[] dp=new long[1<<N];
        for (int i = 0; i < 1 << N; i++) {
            dp[i]=-1;
        }
        return solve(dp,like,0,0,N);
    }
    static long solve(long[] dp,int[][] input,int mask,int stu,int n)
    {
        if(n==stu)
            return 1;
        if(dp[mask]!=-1)
            return dp[mask];
        long co=0;
        for (int i = 0; i < n; i++) {
            if(input[stu][i]!=0 && (mask&(1<<i))==0)
            {
                co+=solve(dp,input,mask|(1<<i),stu+1,n);
            }
        }
        dp[mask]=co;
        return co;
    }
    public static void main(String[] args) throws IOException{
        Reader reader=new Reader();
        int n=reader.nextInt();
        int input[][]=new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                input[i][j]=reader.nextInt();
            }
        }
        System.out.println(solve(input,n));

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
