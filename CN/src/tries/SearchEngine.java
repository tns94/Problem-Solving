package tries;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SearchEngine {
    static class Trie{
        char ch;
        int wt;
        List<Trie> list;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Trie trie = (Trie) o;
            return ch == trie.ch;
        }

        @Override
        public int hashCode() {
            return Objects.hash(ch);
        }
    }
    static void insert(Trie root,String str,int weight)
    {
        Trie curr=root;
        for (int i = 0; i < str.length(); i++) {
            if(curr.list==null)
            {
                Trie node=new Trie();
                node.wt=weight;
                node.ch=str.charAt(i);
                curr.list=new ArrayList<>();
                curr.list.add(node);
                curr=node;
            }
            else
            {
                Trie node=new Trie();
                node.ch=str.charAt(i);
                int in;
                if((in=curr.list.indexOf(node))!=-1)
                {
                    node=curr.list.get(in);
                    if(node.wt<weight)
                        node.wt=weight;
                }
                else
                {
                    node.wt=weight;
                    curr.list.add(node);
                }
                curr=node;
            }
        }
    }
    static int query(Trie root,String query)
    {
        Trie curr=root;
        for (int i = 0; i < query.length(); i++) {
            Trie node=new Trie();
            node.ch=query.charAt(i);
            int in;
            if(curr.list!=null && (in=curr.list.indexOf(node))!=-1)
                curr=curr.list.get(in);
            else
                return -1;
        }
        return curr.wt;
    }
    public static void main(String[] args) throws IOException {
        Reader reader=new Reader();
        int n=reader.nextInt();
        int q=reader.nextInt();
        StringBuilder ans=new StringBuilder();
        String str;
        int weight;
        Trie root=new Trie();
        for (int i = 0; i < n; i++) {
            str=reader.readLine();
            weight=reader.nextInt();
            insert(root,str,weight);
        }
        for (int i = 0; i < q; i++) {
            str=reader.readLine();
            ans.append(query(root,str)+"\n");
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
        public String readLine() throws IOException
        {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1)
            {
                if (c == '\n' || c==' ')
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
