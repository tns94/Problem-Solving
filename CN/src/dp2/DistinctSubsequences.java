package dp2;

import java.util.Scanner;

public class DistinctSubsequences {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();sc.nextLine();
        long m=1000000007l;
        for (int i = 0; i < t; i++) {
            String s=sc.nextLine();
            int l=s.length();

            long[] dp=new long[26];
            long sum=1,ps=1;
            int c;
            for (int j = l-1; j >=0; j--) {
                sum=2l*ps;
                c=(int)(s.charAt(j)-'A');
                if (dp[c]!=0)
                    sum=((sum+m)-dp[c])%m;
                dp[c]=ps%m;
                ps=sum%m;
            }
            System.out.println(sum%m);

            }
        }

}
