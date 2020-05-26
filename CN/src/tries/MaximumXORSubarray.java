package tries;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
/*
Maximum possible xor of subarray
 */
public class MaximumXORSubarray {
    static class Trie{
        Trie left;
        Trie right;
    }
    static void insert(Trie root, int n)
    {
        Trie curr=root;
        for (int i = 31; i >=0 ; i--) {
            int bit=(n>>i)&1;
            if (bit==0)
            {
                if(curr.left==null)
                    curr.left=new Trie();
                curr=curr.left;
            }
            else
            {
                if(curr.right==null)
                    curr.right=new Trie();
                curr=curr.right;
            }
        }
    }
    static int queryXor(Trie root,int n)
    {
        Trie curr=root;
        int xor=0;
        for (int i = 31; i >= 0; i--) {
            int bit=(n>>i)&1;
            if(bit==0)
            {
                if(curr.right!=null) {
                    xor += 1 << i;
                    curr = curr.right;
                }else
                    curr=curr.left;
            }
            else {
                if(curr.left!=null)
                {
                    xor += 1 << i;
                    curr = curr.left;
                }
                else
                    curr=curr.right;
            }
        }
        return xor;
    }
    static int maximumXor(Trie root,int[] arr)
    {
        int max_xor=0;
        int total_xor=0;
        insert(root,0);
        for (int i = 0; i < arr.length; i++) {
            total_xor^=arr[i];
            int curr_xor=queryXor(root,total_xor);
            max_xor=Math.max(max_xor,curr_xor);
            insert(root,total_xor);
        }
        return max_xor;
    }

    public static void main(String[] args) throws IOException{
        Reader reader=new Reader();
        int n=reader.nextInt();
        int[] arr=new int[n];
        for (int i = 0; i < n; i++) arr[i]=reader.nextInt();
        Trie root=new Trie();
        System.out.println(maximumXor(root,arr));
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
