package dp2;

import com.sun.javafx.image.IntPixelGetter;

import java.util.Arrays;
import java.util.Scanner;

/*
Length of shortest subsequence in str1 such that it is not a subsequence in str2
 */
public class ShortestSubsequence {
    static int count(String s1, String s2, int si1, int si2,int[][] dp)
    {
        if (si2>=s2.length())return 1;

        if( si1>=s1.length())return 1001;

        if(dp[si1][si2]!=0)
            return dp[si1][si2];

        int ans1=count(s1,s2,si1+1,si2,dp);
        int in=s2.indexOf(s1.charAt(si1),si2);
        if(in==-1) return dp[si1][si2]= 1;

        int ans2= count(s1,s2,si1+1,in+1,dp)+1;

        return dp[si1][si2]=Math.min(ans1,ans2);
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String str1=sc.next();
        String str2=sc.next();

        int dp[][]=new int[str1.length()][str2.length()];
        System.out.println(count(str1,str2,0,0,dp));

    }
}
