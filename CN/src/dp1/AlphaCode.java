package dp1;

import java.util.Scanner;

public class AlphaCode {

    static int count(char [] arr)
    {
        int[] output=new int[arr.length+1];
        output[0]=1;
        output[1]=1;
        for (int i = 2; i <= arr.length; i++) {
            if ((Character.getNumericValue(arr[i-2])>2 || Character.getNumericValue(arr[i-2])==0) && Character.getNumericValue(arr[i-1])==0)
                return 0;

            if(arr[i-1]!='0')
                output[i]=output[i-1];
            else
                output[i]=0;

            if (Integer.parseInt(arr[i-2]+""+arr[i-1])<=26  && arr[i-2]!='0')
                output[i]=(output[i]+output[i-2])%1000000007;

        }
        return output[arr.length];

    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String s;
        char[] arr;
        int len;
        while(!(s=sc.next()).equals("0"))
        {
            len=s.length();
            arr=new char[len];
            s.getChars(0,len,arr,0);
            System.out.println(count(arr));
        }
    }

}
