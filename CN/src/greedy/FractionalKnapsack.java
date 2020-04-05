package greedy;

import java.util.*;

public class FractionalKnapsack {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        long d=sc.nextLong();
        int t,x,y=100001;
        int[] tarr=new int[y];
        int[] xarr=new int[y];
        int[] yarr=new int[y];
        int tmax=0;
        for (int i = 0; i < n; i++) {
            t=sc.nextInt();
            x=sc.nextInt();
            y=sc.nextInt();
            if(tarr[t]!=0)
            {
                if(yarr[t]<y || (yarr[t]==y && xarr[t]>x))
                {
                    yarr[t]=y;
                    xarr[t]=x;
                }
            }
            else
            {
                tarr[t]=t;
                yarr[t]=y;
                xarr[t]=x;
            }
        tmax=Math.max(tmax,t);
        }
        long min=0l;
        long py=0;
        int px=0;
        long pt=0;
        int st=0;
        for (int i = 0; i <= tmax; i++) {
            if(tarr[i]!=0)
            {
                py=yarr[i];
                px=xarr[i];
                min=xarr[i];
              //  d=d-py;
                st=i+1;

                pt=i;
                break;
            }
        }


        for (int i=st; i <= tmax; i++) {
            if(tarr[i]!=0)
            {
                if (yarr[i]>py) {
                   d=d-( py * (i-pt));
                    pt=i;
                    if ( d<=0 )
                        break;
                    min += xarr[i];
                    py = yarr[i];
                }
            }
        }


        System.out.print(min);
    }
}
