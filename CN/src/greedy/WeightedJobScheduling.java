package greedy;

import java.util.Scanner;

public class WeightedJobScheduling {
    static class job{int st,en,pr;}
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        job[] arr=new job[n];
        for (int i = 0; i < n; i++) {
            job j=new job();
            j.st=sc.nextInt();
            j.en=sc.nextInt();
            j.pr=sc.nextInt();
            arr[i]=j;
        }
        mergeSort(arr,0,n-1);

        long[] dp=new long[n];
        dp[0]=arr[0].pr;
        for (int i = 1; i < n; i++) {
            dp[i]=Math.max(dp[i-1],arr[i].pr+bs(arr,0,i-1,i,0,dp));
        }
        System.out.println(dp[n-1]);
    }

    public static void mergeSort(job [] input , int si,int ei)
    {
        if(si>=ei)
            return;
        int mid=(si+ei)/2;
        mergeSort(input,si,mid);
        mergeSort(input,mid+1,ei);
        merge(input,si,mid,ei);

    }
    public static void merge(job [] input , int si,int mid,int ei)
    {
        job[] arr=new job [ei-si+1];
        int i=si,j=mid+1,k=0;
        while(i<=mid && j<=ei){
            if (input[i].en<input[j].en)
            {
                arr[k]=input[i];
                i++;
            }
            else
            {
                arr[k]=input[j];
                j++;
            }
            k++;
        }
        if(i<=mid)
            for (int p=i;p<=mid;p++,k++)
                arr[k]=input[p];
        if(j<=ei)
            for (int p=j;p<=ei;p++,k++)
                arr[k]=input[p];
        if (k!=0)
            for (int p=si,q=0;p<=ei;p++,q++)
                input[p]=arr[q];
    }
    static long bs(job[] arr,int si, int ei,int se,long cuin,long[] dp)
    {
        if (si>ei)
            return cuin;
        int mid=(si+ei)/2;
        if (arr[se].st<arr[mid].en)
            return bs(arr,si, mid-1,se,cuin,dp);
        else
            return bs(arr,mid+1, ei,se,dp[mid],dp);

    }
}
