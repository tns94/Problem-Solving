package numbertheory1and2;

import java.io.DataInputStream;
import java.io.IOException;

public class CubeFreeNumber {
    static int[] cubeFreeSieve()
    {
        int[] arr=new int[1000001];
        int cube;
        for (int i = 2; i < 101; i++) {
            cube=i*i*i;
            for (int j = cube; j < 1000001; j+=cube) {
                arr[j]=-1;
            }
        }

        int co=1;
        for (int i = 1; i < 1000001; i++) {
            if(arr[i]!=-1)
            {
                arr[i]=co++;
            }
        }
        return arr;
    }
    public static void main(String[] args) throws Exception{
        Reader reader=new Reader();
        int[] arr=cubeFreeSieve();
        int t=reader.nextInt();
        StringBuilder ans=new StringBuilder();
        for (int i = 1; i <= t; i++)
        {
            int n=reader.nextInt();
            if(arr[n]==-1)
                ans.append("Case "+i+": Not Cube Free\n");
            else
                ans.append("Case "+i+": "+arr[n]+" \n");
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
