package greedy;

import java.util.Scanner;
/*
Print the smallest number whose sum of the digits is S and the number of digits is D
 */
public class WinningLottery {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int s=sc.nextInt()-1;
        int d=sc.nextInt();
        int[] arr=new int[d];
        for (int i = 0; i < d - 1; i++) {
            if(s>=9)
            {
                s-=9;
                arr[i]=9;
            }
            else
            {
                arr[i]=s;
                s=0;
            }
        }
        arr[d-1]=s+1;
        StringBuilder str=new StringBuilder();
        for (int i = d-1; i >=0 ; i--) {
            str.append(arr[i]);
        }
        System.out.println(str);


    }
}
