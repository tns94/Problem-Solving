package dp2;

import java.util.Arrays;
import java.util.Scanner;

/*
Edit distance Between 2 Strings
 */
public class EditDistance {

    public static int editDistance(String s1, String s2){
        int m=s1.length();
        int n=s2.length();
        int[] bk=new int[n+1];
        int[] dp=new int[n+1];


        for (int i = 0; i <= n; i++) bk[i] = i;

        for (int i = 1; i <= m; i++) {
            dp[0]=i;
            for (int j = 1; j <= n; j++) {
                if(s1.charAt(m-i)==s2.charAt(n-j))
                    dp[j]=bk[j-1];
                else{
                    dp[j]=Math.min(bk[j],Math.min(bk[j-1],dp[j-1]))+1;
                }
            }
            bk= Arrays.copyOf(dp,n+1);

        }
        return bk[n];
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String s1=sc.next();
        String s2=sc.next();
        System.out.println(editDistance(s1,s2));
    }
}

