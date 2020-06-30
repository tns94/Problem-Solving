package fenwicktree;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CodersRating {
    static void update(int[] tree,int index,int n)
    {
        while(index<=n)
        {
            tree[index]+=1;
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
    public static void main(String[] args) throws IOException {
        Reader reader=new Reader();
        int n=reader.nextInt();
        int yMax=0;
        List<Rating> list=new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Rating rating=new Rating();
            rating.x=reader.nextInt();
            rating.y=reader.nextInt();
            yMax=Math.max(yMax,rating.y);
            rating.in=i;
            list.add(rating);
        }
        Collections.sort(list,(o1, o2) -> o1.x==o2.x? o1.y-o2.y:o1.x-o2.x);
        int[] tree=new int[yMax+1];
        int[] ans=new int[n];
        for (int i = 0; i < n;) {
            int en=i;
            while (en<n && list.get(i).x==list.get(en).x && list.get(i).y==list.get(en).y )
                en++;
            for (int j = i; j < en; j++) {
                ans[list.get(j).in]=query(tree,list.get(j).y);
            }
            for (int j = i; j < en; j++) {
                update(tree,list.get(j).y,yMax+1);
            }
            i=en;
        }
        StringBuilder str=new StringBuilder();
        for (int i = 0; i < n; i++) {
            str.append(ans[i]+"\n");
        }
        System.out.print(str);

    }
    static class Rating{int x,y,in;}
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
