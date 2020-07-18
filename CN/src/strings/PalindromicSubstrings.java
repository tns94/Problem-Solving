package strings;

import java.util.Scanner;

public class PalindromicSubstrings {
    static int getPalindromCount(String str)
    {
        int len=str.length();
        int l,r;
        int co=0;
        for (int i = 0; i < len; i++) {
            l=i-1;r=i+1;
            co++;
            while(l>=0 && r<len && str.charAt(l)==str.charAt(r))
            {
                l--;r++;
                co++;
            }
            if(i<len-1 && str.charAt(i+1)==str.charAt(i))
            {
                co++;
                l=i-1;r=i+2;
                while(l>=0 && r<len && str.charAt(l)==str.charAt(r))
                {
                    l--;r++;
                    co++;
                }
            }
        }
        return co;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String string=sc.next();
        System.out.println(getPalindromCount(string));
    }
}
