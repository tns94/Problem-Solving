package searchingsorting;

import java.util.Scanner;

public class TajEntry {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        long min=Long.MAX_VALUE;
        int in=0;
        long v,t;
        for(int i=0;i<n;i++){
            v=sc.nextLong();
            t=(v-i)/n;
            if ((v-i)%n>0)
                t=t+1;
            v=i+t*n;
            // System.out.println(v);
            if (min>v)
            {  min=v;in=i;}
        }
        System.out.print(in+1);
    }
}