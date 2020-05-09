package advancedgraphs;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Bottom {
    static boolean isBottom(List<Integer>[] adjacancyList,List<Integer> component,int[] compNo)
    {
        for (int com:component)
        {
            if(adjacancyList[com]!=null)
            for(int i:adjacancyList[com])
                if(compNo[i]!=compNo[com])
                    return false;
        }
        return true;
    }
    static void DFS(Stack<Integer> stack,List<Integer>[] adjacancyList,boolean[] visited,int start)
    {
        visited[start]=true;
        if(adjacancyList[start]!=null)
            for (int i:adjacancyList[start])
            {
                if(!visited[i])
                    DFS(stack,adjacancyList, visited, i);
            }
        stack.push(start);
    }
    static void DFS2(List<Integer>[] adjacancyList,List<Integer> component,boolean[] visited,int start,int[] compNo,int no)
    {
        visited[start]=true;
        component.add(start);
        compNo[start]=no;
        if(adjacancyList[start]!=null)
            for (int i:adjacancyList[start])
            {
                if(!visited[i])
                    DFS2(adjacancyList, component,visited, i,compNo,no);
            }
    }
    static List<List<Integer>> getSCC(List<Integer>[] adjacancyList,List<Integer>[] TadjacancyList,int n,int[] compNo)
    {
        boolean[] visited=new boolean[n];
        Stack<Integer> stack=new Stack<>();
        for (int i = 0; i < n; i++) {
            if(!visited[i])
                DFS(stack,adjacancyList,visited, i);
        }
        List<List<Integer>> components=new ArrayList<>();
        visited=new boolean[n];
        int start;
        int no=1;
        while(!stack.empty())
        {
            start=stack.pop();
            if(!visited[start]) {
                List<Integer> component = new ArrayList<>();
                DFS2(TadjacancyList, component, visited, start,compNo,no);
                components.add(component);
                no++;
            }
        }
        return components;
    }
    public static void main(String[] args) throws Exception{
        Reader reader=new Reader();
        int n,e,a,b;
        while((n=reader.nextInt())!=0)
        {
            e=reader.nextInt();
            List<Integer>[] adjacancyList=new List[n];
            List<Integer>[] TadjacancyList=new List[n];
            int[] compNo=new int[n];
            for (int j = 0; j < e; j++) {
                a=reader.nextInt()-1;
                b=reader.nextInt()-1;
                if(adjacancyList[a]==null)
                {
                    ArrayList<Integer> list=new ArrayList<>();
                    list.add(b);
                    adjacancyList[a]=list;
                }
                else
                    adjacancyList[a].add(b);
                if(TadjacancyList[b]==null)
                {
                    ArrayList<Integer> list=new ArrayList<>();
                    list.add(a);
                    TadjacancyList[b]=list;
                }
                else
                    TadjacancyList[b].add(a);
            }
            List<List<Integer>> components=getSCC(adjacancyList,TadjacancyList,n,compNo);
            StringBuilder str=new StringBuilder();
            List<Integer> sortedAns=new ArrayList<>();
            for (List<Integer> component:components)
            {
                if(isBottom(adjacancyList,component,compNo))
                {
                    for (int com:component)
                       sortedAns.add(com);
                }
            }
            Collections.sort(sortedAns);
            for (int com:sortedAns)
                str.append((com+1)+" ");
            System.out.println(str);
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
