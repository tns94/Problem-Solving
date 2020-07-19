package strings;

import java.io.DataInputStream;
import java.io.IOException;

public class ZAlgorithm {
    static int[] zArray(String str,String pattern)
    {
        int i=1,j=0;
        int len=str.length()+pattern.length();
        String zString=pattern+"$"+str;
        int[] zArray=new int[len+1];
        int l=-1,r=-1;
        while(i<len)
        {
            if(zString.charAt(j)==zString.charAt(i))
            {
                if(l==-1)
                    l=i;
                i++;j++;
            }
            else
            {
                if(l>0 && i-l>0)
                {
                    r=i;
                    zArray[l]=r-l;
                    for(int k=l+1;k<r;k++)
                    {
                        if(zArray[k-l]+k<r) zArray[k]=zArray[k-l];
                        else
                        {

                            int x=zArray[k-l],y=r;
                            x=Math.min(x,len-1-k);
                            zArray[k]=zArray[x];
                            int co=0;
                            while(zString.charAt(x++)==zString.charAt(y++))
                                co++;
                            zArray[k]+=co;
                            l=k;r=r+co;
                        }
                    }
                    j=0;
                    i=r;
                    l=-1;r=-1;
                }
                else
                {
                    zArray[i]=j;
                    j=0;
                    i++;
                }
            }
        }
        return zArray;
    }

    static int indexOf(String str,String pattern)
    {
        int[] z=zArray(str,pattern);
        int len=pattern.length();
        for (int i = 0; i < z.length; i++) {
            if(z[i]==len)
                return i-(len+1);
        }
        return -1;
    }
    public static void main(String[] args) throws Exception{
        Reader reader=new Reader();
        String str=reader.readLine();
        String pattern =reader.readLine();
        System.out.println(indexOf(str,pattern));
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
