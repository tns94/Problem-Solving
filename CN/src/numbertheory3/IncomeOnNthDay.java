package numbertheory3;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class IncomeOnNthDay {
    static int mod=1000000006;
    static void matrixMultiply(long[][] A,long[][] M)
    {

        long firstValue = ((A[0][0] * M[0][0])%mod + (A[0][1] * M[1][0])%mod)%mod;
        long secondValue = ((A[0][0] * M[0][1])%mod + (A[0][1] * M[1][1])%mod)%mod;
        long thirdValue = ((A[1][0] * M[0][0])%mod + (A[1][1] * M[1][0])%mod)%mod;
        long fourthValue = ((A[1][0] * M[0][1])%mod + (A[1][1] * M[1][1])%mod)%mod;

        A[0][0] =firstValue;
        A[0][1] = secondValue;
        A[1][0] = thirdValue;
        A[1][1] = fourthValue;
    }
    static void powerOfMatrix(long[][] A, long n)
    {
        if(n==1)
            return;
        powerOfMatrix(A,n/2);
        matrixMultiply(A,A);
        if(n%2 !=0){
            long[][] M = {{1,1},{1,0}};
            matrixMultiply(A,M);
        }
    }
    static long power(int a,int b)
    {
        if(b==0)
            return 1;
        long val=power(a,b/2);
        long ans= (val * val)%1000000007;
        if(b%2!=0)
            ans = (ans * a)%1000000007;
        return ans;
    }
    static int fn(int a,int b,int n)
    {
        if(n==0)
            return a-1;
        if(n==1)
            return b-1;
        long[][] A=new long[][]{{1,1},{1,0}};
        int fn,fn1;
        if(n==2) {
            fn=1;
            fn1=1;
        }else{
            powerOfMatrix(A, n - 2);
            fn1 = (int) A[0][0];
            long[][] M = {{1, 1}, {1, 0}};
            matrixMultiply(A, M);
            fn = (int) A[0][0];
        }
        long val1=power(a,fn1);
        long val2=power(b,fn);

        int val=(int)(val1*val2)%1000000007;
        return val-1;
    }

    public static void main(String[] args) throws Exception{
        Reader reader=new Reader();
        int t=reader.nextInt();
        StringBuilder ans=new StringBuilder();
        while(t-->0)
        {
            int a=reader.nextInt()+1;
            int b=reader.nextInt()+1;
            int n=reader.nextInt();

            ans.append(fn(a,b,n)+"\n");
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
