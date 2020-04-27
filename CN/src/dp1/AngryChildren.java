package dp1;

import java.util.Arrays;
import java.util.Scanner;

public class AngryChildren {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int k=sc.nextInt();
        long[] arr=new long[n];
        for (int i = 0; i < n; i++) {
            arr[i]=sc.nextLong();
        }

        Arrays.sort(arr);
        long cost=arr[1]-arr[0];
        long sum=arr[0]+arr[1];
        for (int i = 2; i < k; i++) {
            cost+=(i*arr[i])-(sum);
            sum+=arr[i];
        }
        long min=cost;
        for (int i = k; i < n; i++) {
            sum-=arr[i-k];
           cost= cost - (2 * sum) + ( (k-1) * arr[i-k] ) + ((k-1) * arr[i]) ;
           sum+=arr[i];
           min=min>cost?cost:min;
        }
        System.out.println(min);
    }
}
