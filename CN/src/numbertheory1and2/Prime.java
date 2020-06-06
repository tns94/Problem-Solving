package numbertheory1and2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Prime {
    static List<Integer> primes(int n)
    {
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
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();

        for (int i:primes(n))
            System.out.println(i);
    }

}
