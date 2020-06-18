package numbertheory3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SehwagAndETF {
    static List<Integer> primes()
    {
        int n=1000000;
        List<Integer> primes=new ArrayList<>();
        boolean[] arr=new boolean[n+1];
        int sqrt=(int)Math.sqrt(n);
        for (int i = 2; i <= sqrt; i++) {
            for (int j = i*i; j <= n; j+=i) {
                arr[j]=true;
            }
        }

        for (int i = 2; i <= n; i++) {
            if(!arr[i])
                primes.add(i);
        }

        return primes;
    }
    static int divisible(long l,long r,long k,List<Integer> primes)
    {
        long[] first=new long[(int)(r-l+1)];
        long[] second=new long[(int)(r-l+1)];
        for (int i = 0; i < first.length; i++) {
            first[i]=l+i;
            second[i]=l+i;
        }
        for(int i = 0; i < primes.size(); i++) {
            int currPrime = primes.get(i);
            long base = (int)(l / currPrime) * currPrime;

            if (base < l)
                base += currPrime;
            for(long j = base; j <= r; j += currPrime)
            {
                int jl=(int)(j-l);
                first[jl] -= (first[jl]/currPrime);
                while(second[jl] % currPrime == 0)
                {
                    second[jl] /= currPrime;
                }
            }
        }

        long count = 0;
        for(int i = 0; i <= r-l ; i++)
        {
            if(first[i] == i+l && i+l != 1)
                first[i]--;

            else if(second[i] > 1)
                first[i] -= first[i]/second[i];
            if(first[i] % k == 0)
                count ++;
        }
        return (int)count;
        
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        List<Integer> primes=primes();
        while(t-->0){
            long l=sc.nextLong();
            long r=sc.nextLong();
            long k=sc.nextLong();
            int div=divisible(l,r,k,primes);
            double ans=div/(double)(r-l+1);
            System.out.printf("%1$.6f",ans);
            System.out.println();
        }
    }
}
