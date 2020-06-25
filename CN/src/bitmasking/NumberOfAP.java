package bitmasking;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
/*
Number of SubSequences which are AP.
 */

public class NumberOfAP {
    static int mod=100001;
    public static int numOfAP(int arr[], int n){
        int[][] dp=new int[n][2001];

        long aps=n+1;

        for (int i = 0; i < n-1; i++) {
            for (int j = i+1; j < n; j++) {
                int diff=arr[j]-arr[i];
                dp[i][diff+1000] += 1;
                aps = (aps + 1)% mod;
            }
        }

        for (int i = n-3; i >= 0; i--) {
            for (int j = i+1; j < n; j++) {
                int diff=arr[j]-arr[i];
                dp[i][diff+1000]= (dp[i][diff+1000]+dp[j][diff+1000])%mod;
                aps = (aps + dp[j][diff+1000])%mod;
            }
        }
        return (int)aps;
    }
    public static void main(String[] args)throws IOException {
        Reader reader=new Reader();
        int n=reader.nextInt();
        int[] arr=new int[n];
        int max=Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) max=Math.max(max,arr[i]=reader.nextInt());
        System.out.println(numOfAP(arr,n));
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
