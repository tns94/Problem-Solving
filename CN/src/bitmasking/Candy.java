package bitmasking;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class Candy {

    static long solve(int[][] like,int N){
        long[] dp=new long[1<<N];
        boolean[] dph=new boolean[1<<N];
        return solve(dp,dph,like,0,0,N);
    }
    static long solve(long[] dp,boolean[] dph,int[][] input,int mask,int stu,int n)
    {
        if(n==stu)
            return 1;
        if(dph[mask])
            return dp[mask];
        long co=0;
        for (int i = 0; i < n; i++) {
            if(input[stu][i]!=0 && (mask&(1<<i))==0)
            {
                co+=solve(dp,dph,input,mask|(1<<i),stu+1,n);
            }
        }
        dph[mask]=true;
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
