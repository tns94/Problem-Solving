package greedy;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class PerimeterWithConditions {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int[] arr=new int[n];
        for (int i = 0; i < n; i++) {
            arr[i]=sc.nextInt();
        }
        Arrays.sort(arr);
        int a,b,c;

        for (int i = n-1; i > 1; i--) {
            a=arr[i];
            b=arr[i-1];
            c=arr[i-2];
            if(c>=a+b)
                continue;
            System.out.println(c+" "+b+" "+a);
            return;

        }
        System.out.print(-1);
    }
}
