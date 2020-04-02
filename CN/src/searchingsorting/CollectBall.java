package searchingsorting;

import java.util.Scanner;

public class CollectBall {



    static boolean check(long n,long k)
    {
        long half=n/2;
        if(n%2!=0)
            half++;
        long cur=n;
        long sum=0;
        while(cur>=0)
        {
            sum+=Math.min(k, cur);
            cur-=k;
            cur-=(cur/10);
            if (sum>=half)
                return true;
        }
        return false;
    }
    static long bs(long n,long si, long ei,long curk)
    {
        if (si>ei)
            return curk;
        long mid=(si+ei)/2;
        if (check(n, mid))
            return bs(n,si, mid-1,mid);
        else
            return bs(n,mid+1, ei,curk);

    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        long n=sc.nextLong();
        System.out.println(bs(n, 1, n, 0));
    }

}
