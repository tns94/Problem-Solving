package strings;

import java.io.DataInputStream;
import java.io.IOException;
/*
KMP algorithm for pattern searching in string.
 */
public class KMP {
    static void fillPrefixArray(int[] prefixArray,String pattern)
    {
        int length=pattern.length();
        int i=1,j=0;
        while(i<length)
        {
            if(pattern.charAt(j)==pattern.charAt(i))
            {
                prefixArray[i]=j+1;
                i++;j++;
            }
            else if(j>0)
                j=prefixArray[j-1];
            else
                i++;
        }
    }
    static int indexOf(String str,String pattern)
    {
        int[] prefixArray=new int[pattern.length()];
        fillPrefixArray(prefixArray,pattern);
        int patternLength=pattern.length();
        int stringLength=str.length();
        int j=0,i=0;
        while(j<patternLength && i<stringLength)
        {
            if(str.charAt(i)==pattern.charAt(j))
            {
                i++;j++;
            }
            else if(j>0)
                j=prefixArray[j];
            else
                i++;
        }
        if(j>=patternLength)
            return i-j;
        return -1;
    }

    public static void main(String[] args) throws Exception{
        Reader reader=new Reader();
        String str=reader.readLine();
        String pattern=reader.readLine();
        System.out.print(indexOf(str,pattern));
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

