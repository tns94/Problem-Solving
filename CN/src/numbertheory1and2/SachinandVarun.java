package numbertheory1and2;

import java.io.DataInputStream;
import java.io.IOException;

public class SachinandVarun {
    static long gcd(long a,long b)
    {
        if(b==0)
            return a;
        return gcd(b,a%b);
    }

    static Node extendedEuclid(long a,long b){

        if(b==0)
        {
            Node node=new Node();
            node.gcd=a;
            node.x=1;
            node.y=0;
            return node;
        }

        Node ans1=extendedEuclid(b,a%b);

        Node ans=new Node();
        ans.gcd=ans.gcd;
        ans.x=ans1.y;
        ans.y=ans1.x - ((a/b)* ans1.y);
        return ans;
    }

    static long mInverse(long A, long M)
    {
        long x=extendedEuclid(A,M).x;
        return (x % M + M)%M;
    }
    public static void main(String[] args) throws IOException{
        Reader reader = new Reader();
        int t=reader.nextInt();
        StringBuilder ans=new StringBuilder();
        while(t-- >0)
        {
            long a=reader.nextInt();
            long b=reader.nextInt();
            long d=reader.nextInt();

            long g=gcd(a,b);

            if(d%g != 0){
                ans.append("0\n");
                continue;
            }

            if(d == 0){
                ans.append("1\n");
                continue;
            }
            a/=g;
            b/=g;
            d/=g;

            long y1  = ((d%a) * mInverse(b,a) ) %a;
            long val = d/b;

            if(d < y1*b){
                ans.append("0\n");
                continue;
            }

            long n = (val - y1)/a;

            ans.append(n+1+"\n");
        }
        System.out.print(ans);
    }
    static class Node{long x,y,gcd;}
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
