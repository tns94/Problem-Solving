package numbertheory1and2;

import java.io.DataInputStream;
import java.io.IOException;

public class NumberOfFactors {
    static int maxN=1000001;
    static int[] primefactorSieve(){
        int[] arr=new int[maxN];
        for (int i = 2; i < 500001; i++) {
            if(arr[i]==0)
            {
                for (int j = i; j < maxN; j+=i) {
                    arr[j]++;
                }
            }
        }
        return arr;
    }
    public static void main(String[] args)throws Exception {
        Reader reader=new Reader();
        int t=reader.nextInt();
        int[] arr=primefactorSieve();
        int[][] range=new int[11][maxN];

        for (int i = 0; i < 11; i++) {
            for (int j = 1; j < maxN; j++) {
                if(arr[j]==i) {
                    range[i][j]=range[i][j-1]+1;
                }
                else
                    range[i][j]=range[i][j-1];
            }
        }
        System.out.println(range[0][30180]);
        StringBuilder ans=new StringBuilder();
        for (int i = 1; i <= t; i++) {
            int a=reader.nextInt();
            int b=reader.nextInt();
            int n=reader.nextInt();

            ans.append(range[n][b]-range[n][a-1]+"\n");
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
