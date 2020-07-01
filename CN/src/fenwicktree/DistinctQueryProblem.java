package fenwicktree;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class DistinctQueryProblem {
    static void update(int[] tree,int index,int num,int n)
    {
        while(index<=n)
        {
            tree[index]+=num;
            index+=index&(-index);
        }
    }
    static int query(int[] tree,int index,int n)
    {
        int sum=0;
        while(index>0)
        {
            sum+=tree[index];
            index-=index&(-index);
        }
        return sum;
    }

    static int[] solve(List<QueryNode> list,int[] arr,int n)
    {
        int[] ansArray=new int[list.size()];
        int[] tree=new int[n+1];
        Map<Integer,Integer> lastIndex=new HashMap<>();
        Iterator<QueryNode> iterator=list.iterator();
        QueryNode node=iterator.next();
        int total=0;
        for (int i = 0; i < n; i++) {
            if(lastIndex.containsKey(arr[i]))
                update(tree,lastIndex.get(arr[i])+1,-1,n);
            else total++;
            lastIndex.put(arr[i],i);
            update(tree,i+1,1,n);
            while (node.en==i+1)
            {
                ansArray[node.in]=total-query(tree,node.st-1,n);
                if(!iterator.hasNext())
                    return ansArray;
                node=iterator.next();
            }
        }
        return ansArray;
    }
    public static void main(String[] args) throws IOException{
        Reader reader=new Reader();
        int n=reader.nextInt();
        int[] arr=new int[n];
        for (int i = 0; i < n; i++) {
            arr[i]=reader.nextInt();
        }
        int a,b;
        int q=reader.nextInt();
        StringBuilder str=new StringBuilder();
        List<QueryNode> list=new ArrayList<>();
        for (int i = 0; i < q; i++) {
            a=reader.nextInt();
            b=reader.nextInt();
            QueryNode node=new QueryNode();
            node.st=a;
            node.en=b;
            node.in=i;
            list.add(node);
        }
        Collections.sort(list,(o1, o2) -> o1.en-o2.en);
        int[] ans=solve(list,arr,n);
        for (int i = 0; i < q; i++) {
            str.append(ans[i]+"\n");
        }
        System.out.print(str);
    }
    static class QueryNode{int st, en, in;}
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
