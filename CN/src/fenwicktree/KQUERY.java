package fenwicktree;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KQUERY {
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

    public static void main(String[] args) throws IOException{
        Reader reader=new Reader();
        int n=reader.nextInt();
        List<Input> inputs=new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Input input=new Input(0,i+1,i,reader.nextInt());
            inputs.add(input);
        }
        int q=reader.nextInt();
        for (int i = 0; i < q; i++) {
            Input input=new Input(reader.nextInt(),reader.nextInt(),i,reader.nextInt());
            inputs.add(input);
        }
        inputs.sort((o1, o2) -> o1.value == o2.value ? o2.l - o1.l : o2.value - o1.value);
        int[] tree=new int[n+2];
        int[] ansArray=new int[q];
        for (Input input:inputs)
        {
            if(input.l==0)
                update(tree,input.r,n+2);
            else
                ansArray[input.in]=query(tree,input.r)-query(tree,input.l-1);
        }
        StringBuilder ans=new StringBuilder();
        for (int i = 0; i < q; i++) {
            if(ansArray[i]!=-1)
            ans.append(ansArray[i]+"\n");
        }
        System.out.print(ans);
    }
    static class Input{int l,r,in,value;

        public Input(int l, int r, int in, int value) {
            this.l = l;
            this.r = r;
            this.in = in;
            this.value = value;
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
