package numbertheory1and2;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DivisorsOfFactorial {
    //Primes
    static List<Integer> primes(int n)
    {
        List<Integer> primes=new ArrayList<>();
        boolean[] arr=new boolean[n+1];
        int sqrt=(int)Math.sqrt(n);
        for (int i = 2; i <= sqrt; i++) {
            for (int j = i*i; j <= n; j+=i) {
                arr[j]=true;
            }
        }

        for (int i = 2; i <= n; i++) {
            if(!arr[i])
                primes.add(i);
        }
        return primes;
    }
    public static void main(String[] args) throws Exception{
        Reader reader= new Reader();
        int t=reader.nextInt();
        List<Integer> primes=primes(50000);
        int mod=1000000007;
        StringBuilder str=new StringBuilder();
        while (t-->0)
        {
            long n=reader.nextInt();
            long ans=1;
            for(int p:primes)
            {
                if(p>n)
                    break;
                long co=1,i=1;
                while((i=p*i)<=n)
                {
                    co = (co+n/i)%mod;
                }

                ans = (ans * co)%mod;
            }
            str.append(ans+"\n");
        }
        System.out.print(str);
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
