package numbertheory1and2;

import java.util.Scanner;

public class AdvancedGCD {
    static long gcd(long a,long b)
    {
        if(b==0)
            return a;
        return gcd(b,a%b);
    }
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        int t=sc.nextInt();
        StringBuilder ans=new StringBuilder();
        while (t-- > 0)
        {
            int a=sc.nextInt();
            String bigInt=sc.next();
            if (a==0) {
                ans.append(bigInt + "\n");
                continue;
            }

            int b=0;
            for (int i = 0; i < bigInt.length(); i++) {
               b =  ( ((10 * b) % a) + bigInt.charAt(i)-48) % a;
            }
            if(a > b)
                ans.append(gcd(a,b)+"\n");
            else
                ans.append(gcd(b,a)+"\n");

        }
        System.out.print(ans);
    }

}
