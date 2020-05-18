package segmenttree;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class _2vs3 {
    static int rangeQuery(int[] pow2,int[] tree, int treeIn, int st, int en, int l, int r) {
        if (st >= l && en <= r)
            return (tree[treeIn]*pow2[r-en])%3;
        if (en < l || st > r)
            return 0;
        int mid = (st + en) / 2;
         int ans1=rangeQuery(pow2,tree, 2 * treeIn, st, mid, l, r);
         int ans2=rangeQuery(pow2,tree, 2 * treeIn + 1, mid + 1, en, l, r);

         return (ans1+ans2)%3;
    }

    static void updateTree(int[] pow2, int[] tree, int treeIn, int st, int en, int index) {
        if (st == index && en == index) {
            tree[treeIn] =  1;
            return;
        }
        int mid = (st + en) / 2;
        if (index > mid)
            updateTree(pow2, tree, treeIn * 2 + 1, mid + 1, en, index);
        else
            updateTree(pow2, tree, treeIn * 2, st, mid, index);

        tree[treeIn] = (tree[2 * treeIn] *pow2[en-mid] + tree[2 * treeIn + 1])%3;
    }

    static void buildTree(int[] pow2,String str, int[] tree, int treeIn, int st, int en) {
        if (st == en) {
            tree[treeIn] = Character.getNumericValue(str.charAt(st));
            return;
        }
        int mid = (st + en) / 2;

        buildTree(pow2,str, tree, treeIn * 2, st, mid);
        buildTree(pow2,str, tree, treeIn * 2 + 1, mid + 1, en);

        tree[treeIn] = (tree[2 * treeIn] *pow2[en-mid] + tree[2 * treeIn + 1])%3;
    }

    public static void main(String[] args) throws Exception{
        //Scanner sc = new Scanner(System.in);
        Reader sc=new Reader();
        int n = sc.nextInt();
        String str=sc.readLine(n);
        int[] pow2=new int[n];pow2[0]=1;
        for (int i = 1; i < n; i++) pow2[i]=(pow2[i-1]*2)%3;
        int[] tree = new int[4 * n];
        buildTree(pow2,str, tree, 1, 0, n - 1);
        int q=sc.nextInt();
        int type,x,y;
        StringBuilder s=new StringBuilder();
        for (int i = 0; i < q; i++) {
            type=sc.nextInt();
            if(type==0)
                s.append(rangeQuery(pow2,tree,1,0,n-1,sc.nextInt(),sc.nextInt())+"\n");
            else
                updateTree(pow2,tree,1,0,n-1,sc.nextInt());
        }
        System.out.print(s);
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

        public String readLine(int len) throws IOException
        {
            byte[] buf = new byte[len]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1)
            {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }
        public String readLine() throws IOException
        {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1)
            {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
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

        public double nextDouble() throws IOException
        {
            double ret = 0, div = 1;
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

            if (c == '.')
            {
                while ((c = read()) >= '0' && c <= '9')
                {
                    ret += (c - '0') / (div *= 10);
                }
            }

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
