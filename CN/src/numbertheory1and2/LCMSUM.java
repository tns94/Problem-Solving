package numbertheory1and2;

import java.util.Scanner;
/*
LCMSUM using Euler Totient Function.
 */
public class LCMSUM {
    static long[] eulerTF(int n)
    {
        long[] etf=new long[n+1];
        for (int i = 1; i <= n; i++) {
            etf[i]=i;
        }
        for (int i = 2; i <= n ; i++) {
            if(etf[i]==i)
            {
                etf[i]=i-1;
                for (int j = 2*i; j <= n; j+=i) {
                    etf[j]=(etf[j]*(i-1))/i;
                }
            }
        }
        return etf;
    }
    static long lcmsum(int n)
    {
        long[] etf=eulerTF(n);
        long sum=1;

        sum+=n*etf[n];

        for (int i = 2; i <= n/2; i++) {
            if(n%i==0)
            {
                sum += i * etf[i];
            }
        }
        sum=sum+1;
        sum = (sum * n)/2;
        return sum;
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        System.out.print(lcmsum(n));
    }
}
