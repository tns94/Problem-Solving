package greedy;

import java.util.Scanner;

public class ActivitySelection {
    public static void mergeSort(int [] st ,int[] en, int si,int ei)
    {
        if(si>=ei)
            return;
        int mid=(si+ei)/2;
        mergeSort(st,en,si,mid);
        mergeSort(st,en,mid+1,ei);
        merge(st,en,si,mid,ei);

    }
    public static void merge(int [] st , int[] en,int si,int mid,int ei)
    {
        int[] enarr=new int [ei-si+1];
        int[] starr=new int [ei-si+1];
        int i=si,j=mid+1,k=0;
        while(i<=mid && j<=ei){
            if (en[i]<en[j])
            {
                enarr[k]=en[i];
                starr[k]=st[i];
                i++;
            }
            else
            {
                enarr[k]=en[j];
                starr[k]=st[j];
                j++;
            }
            k++;
        }
        if(i<=mid)
            for (int p=i;p<=mid;p++,k++)
            {
                enarr[k]=en[p];
                starr[k]=st[p];
            }
        if(j<=ei)
            for (int p=j;p<=ei;p++,k++)
            {
                enarr[k]=en[p];
                starr[k]=st[p];
            }
        if (k!=0)
            for (int p=si,q=0;p<=ei;p++,q++)
               {
                   en[p]=enarr[q];
                   st[p]=starr[q];
               }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int[] st=new int[n];
        int[] en=new int[n];

        for (int i = 0; i < n; i++) {
            st[i]=sc.nextInt();
            en[i]=sc.nextInt();
        }
        mergeSort(st,en,0,n-1);

        int co=0;
        int pe=0;
        for (int i = 0; i < n; i++) {
            if(st[i]>=pe)
            {
                co++;
                pe=en[i];
            }
        }
        System.out.print(co);

    }
}
