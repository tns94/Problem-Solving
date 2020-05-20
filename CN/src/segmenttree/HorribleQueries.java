package segmenttree;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class HorribleQueries {
    static long rangeQuery(long[] lazy,long[] tree,int treeIn,int st,int en,int l,int r)
    {
        if(lazy[treeIn]!=0)
        {
            tree[treeIn]+=lazy[treeIn]*(en-st+1);
            if(st!=en)
            {
                lazy[treeIn*2]+=lazy[treeIn];
                lazy[treeIn*2+1]+=lazy[treeIn];
            }lazy[treeIn]=0;
        }
        if(st>r || en<l) return 0;
        if(l<=st && en<=r)
            return tree[treeIn];
        int mid=(st+en)/2;
        return  rangeQuery(lazy,tree,treeIn*2,st,mid,l,r)
                +
                rangeQuery(lazy,tree,treeIn*2+1,mid+1,en,l,r);
    }
    static void update(long[] lazy,long[] tree,int treeIn,int st,int en,int l,int r,long value)
    {
        if(en<st)return;
        if(lazy[treeIn]!=0)
        {
            tree[treeIn]+=lazy[treeIn]*(en-st+1);
            if(st!=en)
            {
                lazy[treeIn*2]+=lazy[treeIn];
                lazy[treeIn*2+1]+=lazy[treeIn];
            }
            lazy[treeIn]=0;
        }
        if(en<l || st>r)
            return;
        if(l<=st && en<=r)
        {
            tree[treeIn]+=value*(en-st+1);
                if(st!=en)
                {
                    lazy[treeIn*2]+=value;
                    lazy[treeIn*2+1]+=value;
                }
                return;
        }
        int  mid=(st+en)/2;
        update(lazy,tree,treeIn*2,st,mid,l,r,value);
        update(lazy,tree,treeIn*2+1,mid+1,en,l,r,value);

        tree[treeIn]=tree[treeIn*2]+tree[treeIn*2+1];
    }
    public static void main(String[] args) throws Exception {
       // Scanner sc=new Scanner(System.in);
        Reader sc=new Reader();
        int n,c,type,l,r,value,t=sc.nextInt();
        long[] tree;
        long[] lazy;
        StringBuilder str=new StringBuilder();
        for (int i = 0; i < t; i++) {
            n=sc.nextInt();
            c=sc.nextInt();
            tree=new long[3*n];
            lazy=new long[3*n];
            for (int j = 0; j < c; j++) {
                type = sc.nextInt();
                l = sc.nextInt();
                r = sc.nextInt();
                if (type == 0)
                    update(lazy, tree, 1, 0, n - 1, l - 1, r - 1, sc.nextLong());
                else
                    str.append(rangeQuery(lazy, tree, 1, 0, n - 1, l - 1, r - 1) + "\n");
            }
        }
        System.out.print(str);
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
