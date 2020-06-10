package numbertheory3;

import java.util.Scanner;

public class CubicSquare {
    static long power(long  a, char[] b, int mod)
    {
        int len = b.length;
        long  ans = 1;

        for(int i=len-1;i>=0;i--)
        {
            if(b[i] == '0')
            {
                a %= mod;
                a = (((a*a)%mod)*a)%mod;
            }

            if(b[i] == '1')
            {
                ans = ans * a;
                ans %= mod;
                a = (((a*a)%mod)*a)%mod;
            }

            if(b[i] == '2')
            {
                ans = ((ans * a)%mod) * a;
                ans %= mod;
                a = (((a*a)%mod)*a)%mod;

            }
        }

        return ans;
    }


    static int power(long a,long b,int mod)
    {
        if(b==0)
            return 1;
        long val=power(a,b/2,mod);
        long ans= (val * val)%mod;
        if(b%2!=0)
            ans = (ans * a)%mod;
        return (int)ans;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();
        StringBuilder ans=new StringBuilder();
        while (t-->0)
        {
            int a=sc.nextInt();
            char[] arr=sc.next().toCharArray();
            int mod=sc.nextInt();
         /*   long result=1;
            long pow=1;
            for (int i = arr.length-1; i >=0; i--) {
                if(arr[i]=='2') {
                    result = (result * power(a,(2*pow),mod))%mod;
                }
                if(arr[i]=='1')
                {
                    result = (result * power(a,pow,mod))%mod;
                }
                pow = (pow * 3 );
            }*/
            ans.append(power(a,arr,mod)+"\n");
        }
        System.out.print(ans);
    }
}
