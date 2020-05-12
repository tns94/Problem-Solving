package advancedgraphs;

import javax.print.attribute.IntegerSyntax;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.*;

public class FILLMTR {
    static boolean isBipartite(Set<Integer>[] adjacancyList,int n)
    {
        List<Integer> red=new ArrayList<>();
        List<Integer> black=new ArrayList<>();
        Queue<Integer> queue=new LinkedList<>();
        queue.offer(0);
        red.add(0);
        int ele;
        while(!queue.isEmpty())
        {
            ele=queue.poll();
            if(red.contains(ele))
            {
                if(adjacancyList[ele]!=null)
                    for (int i:adjacancyList[ele]) {
                        if (!red.contains(i) && !black.contains(i)) {
                            black.add(i);
                            queue.offer(i);
                        } else if (red.contains(i))
                            return false;
                    }
            }
            else
            {
                if(adjacancyList[ele]!=null)
                    for (int i:adjacancyList[ele]) {
                        if (!red.contains(i) && !black.contains(i)) {
                            red.add(i);
                            queue.offer(i);
                        } else if (black.contains(i))
                            return false;
                    }
            }
        }
        return true;
    }
    static Set<Integer>[] getAdjacancyList(int[] parentMatrix,int[][] arr,int n)
    {
        Set<Integer>[] adjacancyList=new Set[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(arr[i][j]==2)
                {
                    int a=i,b=j;
                    while(parentMatrix[a]!=a)
                        a=parentMatrix[a];
                    while (parentMatrix[b]!=b)
                        b=parentMatrix[b];
                    if(a==b) continue;
                    if(adjacancyList[a]==null)
                    {
                        HashSet<Integer> list=new HashSet<>();
                        list.add(b);
                        adjacancyList[a]=list;
                    }
                    else
                        adjacancyList[a].add(b);

                    if(adjacancyList[b]==null)
                    {
                        HashSet<Integer> list=new HashSet<>();
                        list.add(a);
                        adjacancyList[b]=list;
                    }
                    else
                        adjacancyList[b].add(a);
                }
            }
        }
        return adjacancyList;
    }
    static void unionFind(int[] parentMatrix,int a,int b,int[][] arr)
    {
        while(parentMatrix[a]!=a)
            a=parentMatrix[a];
        while (parentMatrix[b]!=b)
            b=parentMatrix[b];
        if(a==b) return;
        if(arr[a][b]!=2)
        parentMatrix[b]=a;
    }
    static boolean check(int[][] arr,int n)
    {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if((i==j && arr[i][j]==2) || (arr[i][j]==2 && arr[j][i]==1))
                    return false;
               // if(arr[i][j]==2)
                   // arr[j][i]=2;
            }
        }
        return true;
    }
    public static void main(String[] args)throws Exception {
        Reader reader=new Reader();
        int t=reader.nextInt();
        int n,q,a,b;
        int[][] arr;
        StringBuilder str=new StringBuilder();
        for (int i = 0; i < t; i++) {
            n=reader.nextInt();
            q=reader.nextInt();
            arr=new int[n][n];
            for (int j = 0; j < q; j++) {
                a=reader.nextInt()-1;
                b=reader.nextInt()-1;
                arr[a][b]=reader.nextInt()+1;
            }
            if(!check(arr,n))
            {str.append("no\n");continue;}
            int[] parent=new int[n];
            for (int j = 0; j < n; j++) parent[j]=j;
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if(arr[j][k]==1)
                        unionFind(parent,j,k,arr);
                }
            }
            Set<Integer>[] adjacancyList=getAdjacancyList(parent,arr,n);
            if(isBipartite(adjacancyList,n))
                str.append("yes\n");
            else
                str.append("no\n");
        }
        System.out.print(str);
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
