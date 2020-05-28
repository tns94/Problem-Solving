package tries;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class SUBXOR {
    static class Trie{
        Trie left;
        Trie right;
        int lco,rco;
    }
    static void insert(Trie root, int n)
    {
        Trie curr=root;
        for (int i = 31; i >=0 ; i--) {
            int bit=(n>>i)&1;
            if (bit==0)
            {
                curr.lco++;
                if(curr.left==null)
                    curr.left=new Trie();
                curr=curr.left;
            }
            else
            {
                curr.rco++;
                if(curr.right==null)
                    curr.right=new Trie();
                curr=curr.right;
            }
        }
    }
    static long queryXor(Trie root, int n,int k)
    {
        Trie curr=root;
        long co=0;
        for (int i = 31; i >= 0; i--) {
            if(curr==null)
                break;
            int Kbit=(k>>i)&1;
            int Nbit=(n>>i)&1;

            if(Kbit==1 && Nbit==0)
            {
                co+=curr.lco;
                curr=curr.right;
            }
            if(Kbit==1 && Nbit==1)
            {
                co+=curr.rco;
                curr=curr.left;
            }
            if(Kbit==0 && Nbit==1)
                curr=curr.right;


            if(Kbit==0 && Nbit==0)
                curr=curr.left;

        }
        return co;
    }
    static long count(Trie root, int[] arr, int k)
    {
        long co=0;
        int total_xor=0;
        insert(root,0);
        for (int i = 0; i < arr.length; i++) {
            total_xor^=arr[i];
            co+=queryXor(root,total_xor,k);
            insert(root,total_xor);
        }
        return co;
    }

    public static void main(String[] args) throws IOException {
        Reader reader=new Reader();
        int t=reader.nextInt();
        for (int i = 0; i < t; i++) {
            int n = reader.nextInt();
            int k = reader.nextInt();
            int[] arr = new int[n];
            for (int j = 0; j < n; j++) arr[j] = reader.nextInt();
            Trie root = new Trie();
            System.out.println(count(root, arr,k));
        }
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
