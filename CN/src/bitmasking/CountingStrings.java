package bitmasking;

import java.util.Scanner;
/*
Given a string 's' consisting of upper case alphabets, i.e. from 'A' to 'Z'. Your task is to find how many strings 't' with length equal to that of 's', also consisting of upper case alphabets are there satisfying the following conditions:
-> String 't' is lexicographical larger than string 's'.
-> When you write both 's' and 't' in the reverse order, 't' is still lexicographical larger than 's'.
 */
public class CountingStrings {
    static int mod=1000000007;
    public static long  countStrings(String str){
        char[] arr=str.toCharArray();
        long co=90-arr[0];
        long sum=0;

        for (int i = 1; i < arr.length; i++) {
            sum = (sum+((90-arr[i]) * co)%mod)%mod;
            co = (((26 * co)%mod)+(90-arr[i]))%mod;
        }
        for (int i = 0; i < arr.length; i++) {
            sum = (sum + (90-arr[i])%mod)%mod;
        }
        return sum;
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String s=sc.next().toUpperCase();
        System.out.println(countStrings(s));
    }
}
