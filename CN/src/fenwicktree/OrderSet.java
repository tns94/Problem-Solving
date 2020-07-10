package fenwicktree;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class OrderSet {
    static void update(int[] tree,int num,int index)
    {
        int n=tree.length;
        while(index<=n)
        {
            tree[index]+=num;
            index+=index&(-index);
        }
    }
    static int query(int[] tree,int index)
    {
        int sum=0;
        while(index>0)
        {
            sum+=tree[index];
            index-=index&(-index);
        }
        return sum;
    }
    static void create(int[] tree,boolean[] arr,Map<Integer,Integer> map,int x){
        int mn=map.get(x);
        if(query(tree,mn)==query(tree,mn-1))
        {
            arr[mn]=true;
            update(tree,1,map.get(x));
        }
    }
    static void delete(int[] tree,boolean[] arr,Map<Integer,Integer> map,int x){

        if(map.containsKey(x)) {
            int mn=map.get(x);
            if (query(tree, mn) != query(tree, mn - 1)) {
                arr[mn] = false;
                update(tree, -1, map.get(x));
            }
        }
    }
    static void kthSmallest(StringBuilder ans,int[] tree,boolean[] arr,Map<Integer,Integer> map,List<Integer> insertList,int k){

        int st=0;
        int en=insertList.size()-1;
        int mid;
        while(st<=en)
        {
            mid=(st+en)/2;
            int num=insertList.get(mid);
            int ind=map.get(num);
            int queryAns=query(tree,ind);
            if(queryAns>k)
                en=mid;
            else if(queryAns<k)
                st=mid+1;
            else
            {
                if(!arr[ind])
                    en=mid;
                else {
                    ans.append(num + "\n");
                    return;
                }
            }
        }
        ans.append("invalid\n");
    }
    static void count(StringBuilder ans,int[] tree,NavigableMap<Integer,Integer> map,int x){
        Integer floor;
        if((floor=map.floorKey(x))!=null) {
            if (floor == x)
                ans.append(query(tree, map.get(floor) - 1) + "\n");
            else
                ans.append(query(tree, map.get(floor)) + "\n");
        }
        else
            ans.append("0\n");

    }
    static void solve(int[] tree,NavigableMap<Integer,Integer> map,List<Integer> insertList,List<Input> inputs){
        StringBuilder ans=new StringBuilder();
        boolean[] arr=new boolean[map.size()+1];

        for (Input inp:inputs)
        {
            if(inp.type==25)
                create(tree,arr,map,inp.x);
            else if(inp.type==19)
                count(ans,tree,map,inp.x);
            else if(inp.type==20)
                delete(tree,arr,map,inp.x);
            else if(inp.type==27)
                kthSmallest(ans,tree,arr,map,insertList,inp.x);
        }
        System.out.print(ans);

    }
    public static void main(String[] args)throws IOException {
        Reader reader=new Reader();
        int n=reader.nextInt();
        List<Input> inputs=new ArrayList<>();
        List<Integer> insertList=new ArrayList<>();
        NavigableMap<Integer,Integer> map=new TreeMap<>();
        int co=1;
        for (int i = 0; i < n; i++) {
            Input input=new Input(reader.nextInt(),reader.nextInt());
            if(input.type==25 && !insertList.contains((Integer)input.x))
                insertList.add(input.x);
            inputs.add(input);
        }
        Collections.sort(insertList);
        for (int i:insertList)
        {
            if(!map.containsKey(i))
            {
                map.put(i,co);
                co++;
            }
        }
        int[] tree=new int[co];
        solve(tree,map,insertList,inputs);
    }
    static class Input{int type ,x;

        public Input(int type, int x) {
            this.type = type;
            this.x = x;
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
