package searchingsorting;

import java.util.Scanner;

public class DistributeCandies {

    static boolean isPossible(int[] arr,int k,int c)
    {
        for (int i=0;i<arr.length;i++)
        {
            if (k<=0)
                return true;
            k-=arr[i]/c;
        }
        if (k<=0)
            return true;
        return false;
    }
    static int bs(int[] arr, int k , int si,int ei,int currentMax)
    {
        if(si>ei)
            return currentMax;
        int mid=(si+ei)/2;
        if(isPossible(arr,k,mid))
            return bs(arr,k,mid+1,ei,mid);
        else
           return  bs(arr,k,si,mid-1,currentMax);
     }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();
        int n,k;
        int []arr;
        int max=0;
        for (int i = 0; i < t; i++) {
            n=sc.nextInt();
            k=sc.nextInt();
            arr=new int[n];
            for (int j = 0; j < n; j++) {
                arr[j]=sc.nextInt();
                if(arr[j]>max)
                    max=arr[j];
            }
            System.out.println(bs(arr,k,1,max,1));
        }
    }
}
