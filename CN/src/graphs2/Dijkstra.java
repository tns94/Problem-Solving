package graphs2;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.*;

public class Dijkstra {
    static void dijkstra(Map<Integer, List<Vertex>> adjacancyList,int[] parent,int[] distance,boolean[] visited,int v)
    {
        PriorityQueue<Vertex> queue=new PriorityQueue<>((o1, o2) -> o1.distance-o2.distance);
        Vertex vertex=new Vertex();
        vertex.vertex=0;
        queue.offer(vertex);

        while(!queue.isEmpty())
        {
            int cur=queue.poll().vertex;
            visited[cur]=true;

            for(Vertex ver:adjacancyList.get(cur))
            {
                if(!visited[ver.vertex])
                {
                    if(distance[ver.vertex]>distance[cur]+ver.distance)
                    {
                        distance[ver.vertex]=distance[cur]+ver.distance;
                        parent[ver.vertex]=cur;
                        Vertex vertex1=new Vertex();
                        vertex1.vertex=ver.vertex;
                        vertex1.distance=distance[ver.vertex];
                        queue.remove(vertex1);
                        queue.offer(vertex1);
                    }
                }
            }
            /*for (int i = 0; i < v; i++) {
                if(matrix[cur][i] !=0 && !visited[i])
                {
                    if(distance[i]>distance[cur]+matrix[cur][i])
                    {
                        distance[i]=distance[cur]+matrix[cur][i];
                        parent[i]=cur;
                        Vertex vertex1=new Vertex();
                        vertex1.vertex=i;
                        vertex1.distance=distance[i];
                        queue.remove(vertex1);
                        queue.offer(vertex1);
                    }
                }
            }*/
        }
    }
    public static void main(String[] args) throws  Exception{
        Reader reader=new Reader();
        int v=reader.nextInt();
        int e=reader.nextInt();
        int a,b;
        //int[][] matrix=new int[v][v];
        Map<Integer, List<Vertex>> adjacancyList=new HashMap<>();
        for (int i = 0; i < e; i++) {
            a=reader.nextInt();b=reader.nextInt();
            Vertex vera=new Vertex();vera.vertex=a;
            Vertex verb=new Vertex();verb.vertex=b;
            vera.distance=verb.distance=reader.nextInt();

            if(!adjacancyList.containsKey(a))
            {
                List<Vertex> list=new ArrayList<>();
                list.add(verb);
                adjacancyList.put(a,list);
            }
            else
                adjacancyList.get(a).add(verb);
            if(!adjacancyList.containsKey(b))
            {
                List<Vertex> list=new ArrayList<>();
                list.add(vera);
                adjacancyList.put(b,list);
            }
            else
                adjacancyList.get(a).add(verb);
            //matrix[a][b]=matrix[b][a]=reader.nextInt();
        }
        int[] parent=new int[v];
        int[] distance=new int[v];
        for (int i = 1; i < v; i++) distance[i]=Integer.MAX_VALUE;
        boolean[] visited=new boolean[v];
        dijkstra(adjacancyList,parent,distance,visited,v);
        StringBuilder str=new StringBuilder();
        for (int i = 0; i < v; i++) {
            str.append(i+" "+distance[i]+"\n");
        }
        System.out.print(str);
    }

    static class Vertex{int vertex,distance;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Vertex vertex1 = (Vertex) o;
            return vertex == vertex1.vertex;
        }

        @Override
        public int hashCode() {
            return Objects.hash(vertex);
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
