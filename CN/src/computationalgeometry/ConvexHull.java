package computationalgeometry;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.*;

public class ConvexHull {

    static boolean change(int p,int q,int r,int[] xCor,int[] yCor){
        int x1=xCor[q]-xCor[p];
        int y1=yCor[q]-yCor[p];
        int x2=xCor[r]-xCor[q];
        int y2=yCor[r]-yCor[q];

        int crossProduct=(x1*y2)-(x2*y1);
        return crossProduct>0;
    }
    public static int orientation(Point p, Point q, Point r)
    {
        int val = (q.y - p.y) * (r.x - q.x) -
                (q.x - p.x) * (r.y - q.y);

        if (val == 0) return 0;
        return (val > 0)? 1: 2;
    }

    public static List<Point> convexHull(Point points[], int n)
    {
        List<Point> hull = new ArrayList<>();
        if (n < 3) return hull;

        int l = 0;
        for (int i = 1; i < n; i++)
            if (points[i].x < points[l].x)
                l = i;

        int p = l, q;
        do
        {
            hull.add(points[p]);


            q = (p + 1) % n;

            for (int i = 0; i < n; i++)
            {

                if (orientation(points[p], points[i], points[q])
                        == 2)
                    q = i;
            }


            p = q;

        } while (p != l);

        return hull;
    }
    static List<Point> getConvexHull(int[] xCor,int[] yCor,int n)
    {
        List<Point> convexHull=new ArrayList<>();
        int l=0;
        for (int i = 1; i < n; i++) {
            if(xCor[i]<xCor[l])
                l=i;
        }
        int p=l;
        do {
            convexHull.add(new Point(xCor[p],yCor[p]));
            int q=(p+1)%n;
            for (int i = 0; i < n; i++) {
                if(change(p,q,i,xCor,yCor))
                    q=i;
            }
            p=q;
        }while(p!=l);
        return convexHull;
    }


    static Set<Point> convexHull(List<Point> points,int n)
    {
        Set<Point> convexHull=new HashSet<>();
        Point l=points.get(0);
        Point p=l;
        int i=0;
        do {
            convexHull.add(p);
            i=(i+1)%n;
            Point q=points.get(i);
            long x1=p.x-q.x;
            long y1=p.y-q.y;
            for (int j = 0; j < n; j++) {
                Point r=points.get(j);
                long x2=r.x-q.x;
                long y2=r.y-q.y;

                long crossProduct=(x1*y2)-(x2*y1);
                if(crossProduct>0)
                {
                    q=r;
                    i=j;
                    x1=p.x-q.x;
                    y1=p.y-q.y;
                }
            }
            p=q;
        }while(p!=l);
        return convexHull;
    }
    public static void main(String[] args)throws Exception {
        Reader reader=new Reader();
        int n=reader.nextInt();
        Point[] points=new Point[n];
        int[] xCor=new int[n];
        int[] yCor=new int[n];
        for (int i = 0; i < n; i++) {
            xCor[i]=reader.nextInt();
        }
        for (int i = 0; i < n; i++) {
            yCor[i]=reader.nextInt();
        }
         for (int i = 0; i < n; i++) {
            points[i]=new Point(xCor[i],yCor[i]);
        }
        List<Point> convexHull=convexHull(points,n);
        StringBuilder ans=new StringBuilder();
        //List<Point> convexHull=getConvexHull(xCor,yCor,n);
        for (Point p:convexHull)
            ans.append(p.x+" "+p.y+"\n");
        System.out.print(ans);
    }
    static class Point{int x,y;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x &&
                    y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
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
