package numbertheory1and2;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SegmentedSieve {
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

    static void primesLtoR(int l,int r,List<Integer> primes,StringBuilder result){
        boolean[] isNotPrime=new boolean[r-l+1];

        int sqrt=(int)Math.sqrt(r);

        for (int i = 0; primes.get(i) <= sqrt; i++) {
            int p=primes.get(i);
            int base=(l/p)*p;
            if(base<l)
                base+=p;
            for (int j = base; j <= r; j+=p) {
                isNotPrime[j-l]=true;
            }
            if(base==p)
                isNotPrime[base-l]=false;
        }
        for (int i = 0; i <= r-l; i++) {
            if(!isNotPrime[i])
                result.append((i+l)+"\n");
        }
    }
    public static void main(String[] args) throws Exception{
        Reader reader=new Reader();
        int t=reader.nextInt();
        List<Integer> primes=primes(100001);
        StringBuilder result=new StringBuilder();
        while (t-- > 0)
        {
            int l=reader.nextInt();
            int r=reader.nextInt();
            primesLtoR(l,r,primes,result);
        }
        System.out.print(result);
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
