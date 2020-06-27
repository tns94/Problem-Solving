package computationalgeometry;

import java.io.DataInputStream;
import java.io.IOException;

public class AreaofConvexPolygon {

    static long areaOfPolygon(int[] xCor,int[] yCor,int n)
    {
        double area=0;
        for (int i = 1; i < n-1; i++) {

            long x1=xCor[i]-xCor[0];
            long y1=yCor[i]-yCor[0];
            long x2=xCor[i+1]-xCor[0];
            long y2=yCor[i+1]-yCor[0];

            long crossProduct=(x1*y2)-(x2*y1);
            area+=crossProduct;
        }
        return Math.abs(((long)area)/2);
    }
    public static void main(String[] args)throws Exception {
        Reader reader=new Reader();
        int n=reader.nextInt();
        int[] xCor=new int[n];
        int[] yCor=new int[n];
        for (int i = 0; i < n; i++) {
            xCor[i]=reader.nextInt();
        }
        for (int i = 0; i < n; i++) {
            yCor[i]=reader.nextInt();
        }
        System.out.print(areaOfPolygon(xCor,yCor,n));

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
