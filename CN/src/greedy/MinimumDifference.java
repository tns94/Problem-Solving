package greedy;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

/*
The Minimum absolute difference between two elements ai, and aj (where i != j ) is |ai - aj|.
 */
public class MinimumDifference {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int[] arr=new int[n];
        for (int i = 0; i < n; i++) {
            arr[i]=sc.nextInt();
        }
        Arrays.sort(arr);
        int min=1000007;
        for (int i = 1; i < n; i++) {
            min=Math.min(arr[i]-arr[i-1],min);
        }
        System.out.print(min);
    }
}
