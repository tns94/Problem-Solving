package adhoc;

import java.util.Scanner;

public class InterestingSequence {

    static  int  calCost(int e, int[] arr, int k, int l)
    {
        int in=0,dec=0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i]>e)
                dec+=arr[i]-e;
            if (arr[i]<e)
                in+=e-arr[i];
        }

        if (dec>in)
            return -1;
        else
            return dec*k+(in-dec)*l;

    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int k=sc.nextInt();
        int l=sc.nextInt();
        int arr[]=new int[n];
        int min=Integer.MAX_VALUE,max=0,minCost=Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            arr[i]=sc.nextInt();
            if (arr[i]>max)
                max=arr[i];
            if (arr[i]<min)
                min=arr[i];
        }
            int te;
        for (int i = min; i <= max; i++) {
            te=calCost(i,arr,k,l);
            if(te!=-1 && te<minCost)
                minCost=te;
        }
        System.out.println(minCost);
    }
}
