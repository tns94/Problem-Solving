package numbertheory3;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class Swaps {
    static int mod=1000000007;
    static int modularExponent(int a,int b)
    {
        if(b==0)
            return 1;
        long val=modularExponent(a,b/2);
        long ans= (val * val)%mod;
        if(b%2!=0)
            ans = (ans * a)%mod;
        return (int)ans;
    }
    static int factModular(int n)
    {
        long tm=n/mod;
        long ans;
        if((tm&1)==0)
            ans=1;
        else
            ans=mod-1;
        n=n%mod;
        for (int i = 1; i <= n; i++) {
            ans=(ans*i)%mod;
        }
        return (int)ans;
    }
    static int[] factMod()
    {
        int[] factm=new int[1000001];
        factm[0]=1;
        long te;
        for (int i = 1; i < 1000001; i++) {
            factm[i]=(int)((factm[i-1] *1l * i)%mod);
        }
        return factm;
    }
    static int[] powerof2()
    {
        int[] powerof2=new int[1000001];
        powerof2[0]=1;
        for (int i = 1; i < 1000001; i++) {
            powerof2[i] = (powerof2[i-1] * 2 )%mod;
        }
        return powerof2;
    }
    static int[] factmPowerM()
    {
        int[] factmp=new int[1000001];
        factmp[0]=1;
        int m2=mod-2;
        for (int i = 1; i < 1000001; i++) {
            factmp[i] = modularExponent(i,m2);
        }
        return factmp;
    }
    public static void main(String[] args) throws Exception{
        Reader reader=new Reader();
        int t=reader.nextInt();
        int[] factMod=factMod();
        int[] powerof2=powerof2();
        int[] factmp=factmPowerM();
        StringBuilder ans=new StringBuilder();
        while (t-->0)
        {
            int n=reader.nextInt();
            int k=reader.nextInt();
            int a=powerof2[k];
            int b=factMod[n];
            int c,d;
            if(factMod[k]<1000001) {
                 c = factmp[factMod[k]];
            }else {
                 c = modularExponent(factMod[k], mod - 2);
            }
            if(factMod[n-k]<1000001) {
                d = factmp[factMod[n-k]];
            }else {
                d = modularExponent(factMod[n-k], mod - 2);
            }
            long result=1;
            result = (((((((result * a)%mod)*b)%mod)*c)%mod)*d)%mod;
            ans.append(result+"\n");
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
