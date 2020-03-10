package dp;

import java.util.Scanner;

public class BitonicSequence {
    static int bitonic(int[] arr)
    {
        int len=arr.length;
        int[] inc=new int[len];
        int[] dec=new int[len];
        inc[0]=1;
        dec[len-1]=1;
        for (int i = 1; i < len; i++) {
            inc[i]=1;
            for (int j = i-1; j >=0 ; j--) {
                    if (arr[j]<arr[i])
                    {
                        if(inc[j]+1>inc[i])
                            inc[i]=inc[j]+1;
                    }
            }
        }
        for (int i = len-1; i >=0 ; i--) {
            dec[i]=1;
            for (int j = i+1; j < len ; j++) {
                if (arr[j]<arr[i])
                {
                    if(dec[j]+1>dec[i])
                        dec[i]=dec[j]+1;
                }
            }
        }
        int max=0;
        for (int i = 0; i < len; i++) {
            if ((inc[i]+dec[i]-1)>max)
                max=inc[i]+dec[i]-1;
        }
        return max;
    }
    public static void main(String[] args) {
        System.out.println(bitonic(new int[]{10 ,351 ,997, 693, 506, 112, 930, 815, 53, 815, 89}));
    }
}
