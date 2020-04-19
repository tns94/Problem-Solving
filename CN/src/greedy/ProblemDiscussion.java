package greedy;

import java.util.Arrays;
import java.util.Scanner;

public class ProblemDiscussion {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int k=sc.nextInt();
        int[] arr=new int[n];
        for (int i = 0; i < n; i++) {
            arr[i]=sc.nextInt();
        }
        Arrays.sort(arr);
        int mP=arr[n-1]-arr[0];
        int min=Math.min(arr[0]+k,arr[n-1]-k);
        int max=Math.max(arr[0]+k,arr[n-1]-k);

        for (int i = 1; i < n-1; i++) {
            if(arr[i]-k>=min || arr[i]+k<=max)
                continue;
            if(max-(arr[i]-k) >= (arr[i]+k)-min)
                max=arr[i]+k;
            else
                min=arr[i]-k;
        }
        System.out.println(Math.min(mP,max-min));

    }
}
