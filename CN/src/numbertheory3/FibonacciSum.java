package numbertheory3;

import java.util.Scanner;

public class FibonacciSum {
    static int mod=1000000007;
    static void matrixMultiply(long[][] A,long[][] M)
    {

        long firstValue = ((A[0][0] * M[0][0])%mod + (A[0][1] * M[1][0])%mod)%mod;
        long secondValue = ((A[0][0] * M[0][1])%mod + (A[0][1] * M[1][1])%mod)%mod;
        long thirdValue = ((A[1][0] * M[0][0])%mod + (A[1][1] * M[1][0])%mod)%mod;
        long fourthValue = ((A[1][0] * M[0][1])%mod + (A[1][1] * M[1][1])%mod)%mod;

        A[0][0] =firstValue;
        A[0][1] = secondValue;
        A[1][0] = thirdValue;
        A[1][1] = fourthValue;
    }
    static void power(long[][] A,long n)
    {
        if(n==1)
            return;
        power(A,n/2);
        matrixMultiply(A,A);
        if(n%2 !=0){
            long[][] M = {{1,1},{1,0}};
            matrixMultiply(A,M);
        }
    }
    static long fiboSum(long n,long m){
        int a,b;

            long[][] A = {{1, 1}, {1, 0}};
            power(A, n);
            a = (int) A[0][0];

            long[][] B = {{1, 1}, {1, 0}};
            power(B,m+1);
             b = (int)B[0][0];
        return ((b+mod)-a)%mod;
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int m=sc.nextInt();
        System.out.println(fiboSum(n,m));
    }
}
