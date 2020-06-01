package numbertheory1and2;

import java.io.DataInputStream;
import java.io.IOException;

public class GoodSet {

    static int maxN=750001;
    static int mod=1000000007;
    static void Seive(int[] sieve){
        for (int i = 1; i < 375001; i++) {
            if(sieve[i]>0)
            {
                for (int j = i*2; j < maxN; j+=i) {
                    if(sieve[j]>0)
                        sieve[j] = (sieve[j] + sieve[i])%mod;
                }
            }
        }
    }

    public static void main(String[] args)throws Exception {
        Reader reader=new Reader();
        int t=reader.nextInt();
        int n;
        int[] arr,seive;
        StringBuilder ans=new StringBuilder();
        while (t-- > 0)
        {
            n=reader.nextInt();
            seive=new int[maxN];
            arr=new int[n];
            for (int i = 0; i < n; i++) {
                arr[i]=reader.nextInt();
                seive[arr[i]]=1;
            }
            Seive(seive);
            long total=0;
            for (int i = 0; i < n; i++) {
                total = (total + seive[arr[i]])%mod;
            }
            ans.append(total+"\n");
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
