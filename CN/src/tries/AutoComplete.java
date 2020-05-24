package tries;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class AutoComplete {
    static class Trie{
        char ch;
        Trie[] arr;
        boolean leaf;
    }
    static void insert(Trie root, String str)
    {
        Trie curr=root;
        char tch;
        int in;
        for (int i = 0; i < str.length(); i++) {
            tch=str.charAt(i);
            in=tch-97;
            if(curr.arr==null)
            {
                Trie node=new Trie();
                node.ch=tch;
                curr.arr=new Trie[26];
                curr.arr[in]=node;
            }
            else if(curr.arr[in]==null) {
                Trie node = new Trie();
                node.ch = tch;
                curr.arr[in] = node;
            }
            curr=curr.arr[in];
        }
        curr.leaf=true;
    }
    static void getSuggestions(Trie root,String query,StringBuilder ans)
    {
        Trie curr=root;
        char tch;
        int in;
        for (int i = 0; i < query.length(); i++) {
            tch=query.charAt(i);
            in=tch-97;
            if(curr.arr!=null && curr.arr[in]!=null)
                curr=curr.arr[in];
            else {
                insert(root,query);
                ans.append("No suggestions\n");
                return;
            }
        }
        DFS(curr,query,ans);
    }
    static void DFS(Trie root,String subString,StringBuilder ans)
    {
        if(root.arr==null)
        {
            ans.append(subString+"\n");
            return;
        }
        if(root.leaf)
            ans.append(subString+"\n");

        Trie curr=root;
        for (int i = 0; i < 26; i++) {
            if(curr.arr[i]!=null)
            DFS(curr.arr[i],subString+curr.arr[i].ch,ans);
        }
    }
    public static void main(String[] args)throws IOException {
        Reader reader=new Reader();
        int n=reader.nextInt();
        Trie root=new Trie();
        for (int i = 0; i < n; i++) insert(root,reader.readLine());

        int q=reader.nextInt();
        StringBuilder ans=new StringBuilder();
        for (int i = 0; i < q; i++) {
            getSuggestions(root,reader.readLine(),ans);
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
