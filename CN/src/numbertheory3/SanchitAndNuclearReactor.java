package numbertheory3;

import java.util.Scanner;

public class SanchitAndNuclearReactor {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        StringBuilder str=new StringBuilder();
        while(n-->0)
        {
            long t=sc.nextLong();
            long m=sc.nextInt();
            long tm=t/m;
            long ans;
            if((tm&1)==0)
                ans=1;
            else
                ans=m-1;
            t=t%m;
            for (int i = 1; i <= t; i++) {
                ans=(ans*i)%m;
            }
            str.append(ans+"\n");
        }
        System.out.print(str);
    }
}
