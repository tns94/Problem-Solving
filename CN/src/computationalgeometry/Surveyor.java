package computationalgeometry;

import java.util.Scanner;

public class Surveyor {
    static void getCordinates(char[] dir,int[] points,int[] xCor,int[] yCor)
    {
        int n=dir.length;
        int x=0,y=0;
        for (int i = 0; i < n-1; i++) {
            if(dir[i]=='N')
            {
                y=yCor[i]=y+points[i];
                xCor[i]=x;
            }
            else if(dir[i]=='S')
            { 
                y=yCor[i]=y-points[i];
                xCor[i]=x;
            }
            else if(dir[i]=='E')
            {
                x=xCor[i]=x+points[i];
                yCor[i]=y;
            }
            else if(dir[i]=='W')
            {
                x=xCor[i]=x-points[i];
                yCor[i]=y;
            }
        }
    }
    static long areaOfPolygon(int[] xCor,int[] yCor,int n)
    {
        double area=0;
        for (int i = 0; i < n-1; i++) {

            long x1=xCor[i];
            long y1=yCor[i];
            long x2=xCor[i+1];
            long y2=yCor[i+1];

            long crossProduct=(x1*y2)-(x2*y1);
            area+=crossProduct;
        }
        return Math.abs(((long)area)/2);
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String dir=sc.next();
        int n=dir.length();
        char[] directions=dir.toCharArray();
        int[] points=new int[n];
        for (int i = 0; i < n; i++) {
            points[i]=sc.nextInt();
        }
        int[] xCor=new int[n-1];
        int[] yCor=new int[n-1];

        getCordinates(directions,points,xCor,yCor);

        System.out.print(areaOfPolygon(xCor,yCor,n-1));
    }
}
