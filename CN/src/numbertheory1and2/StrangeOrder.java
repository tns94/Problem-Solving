package numbertheory1and2;

import java.util.*;

public class StrangeOrder {
    static int[] lowestPrimeSieve(int n)
    {
        int[] primeSeive=new int[n+1];
        for (int i = 1; i <= n; i++) {
            primeSeive[i]=i;
        }
        int sqrt=(int)Math.sqrt(n);
        for (int i = 2; i <= sqrt; i++) {
            for (int j = i*2; j <= n; j+=i) {
                if (primeSeive[j] == j)
                    primeSeive[j]=i;
            }
        }
        return primeSeive;
    }
    static String solve(int n)
    {
        StringBuilder ans=new StringBuilder();
        int[] pS=lowestPrimeSieve(n);
        for (int i = n; i > 0; i--) {
            List<Integer> list=new ArrayList<>();
            if(pS[i]==0)
                continue;
            int p=pS[i];
            list.add(i);
            pS[i]=0;
            int te=i;
            while(te!=1)
            {
                for (int j = i-p; j > 0; j-=p) {
                    if(pS[j]!=0) {
                        list.add(j);
                        pS[j] = 0;
                    }
                }
                while (te%p==0)
                    te/=p;
                p=pS[te];
            }
            list.sort(Comparator.reverseOrder());
            for (int num:list)
                ans.append(num+" ");
        }
        return ans.toString();
    }
    static int gcd(int a,int b)
    {
        if(b==0)
            return a;
        return gcd(b,a%b);
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        System.out.print(solve(n));
    }
}
