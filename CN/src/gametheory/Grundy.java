package gametheory;

import java.util.Scanner;

public class Grundy {
    static int mex(int n,int... arr)
    {
        if(arr[0]!=0 && arr[1]!=0 && arr[2]!=0 )
            return 0;
        if(arr[0]!=1 && arr[1]!=1 && arr[2]!=1 )
            return 1;
        if(arr[0]!=2 && arr[1]!=2 && arr[2]!=2 )
            return 2;
        return 3;
    }
    static int grundy(int n){
        if(n==0)
            return 0;

        return mex(n,grundy(n/2),grundy(n/3),grundy(n/6));
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        System.out.println(grundy(n));
    }
}
