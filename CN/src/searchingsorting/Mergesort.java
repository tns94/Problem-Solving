package searchingsorting;

import java.util.Scanner;

public class Mergesort {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int t=sc.nextInt();
		int arr[]=new int[t];
		for (int i = 0; i < t; i++) {
			arr[i]=sc.nextInt();
		}
		mergeSort(arr);
		
		for (int i = 0; i < t; i++) {
			System.out.println(arr[i]);
		}
	}

		public static void mergeSort(int[] input){
			mergeSort(input,0,input.length-1);
		}
	    public static void mergeSort(int [] input , int si,int ei)
	    {
	        if(si>=ei)
	            return;
	        int mid=(si+ei)/2;
	        mergeSort(input,si,mid);
	        mergeSort(input,mid+1,ei);
	        merge(input,si,mid,ei);
	        
	    }
	    public static void merge(int [] input , int si,int mid,int ei)
	    {
	        int[] arr=new int [ei-si+1];
	        int i=si,j=mid+1,k=0;
	        while(i<=mid && j<=ei){
	            if (input[i]<input[j])
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
}
