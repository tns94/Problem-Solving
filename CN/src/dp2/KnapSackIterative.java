package dp2;

import java.util.Scanner;

public class KnapSackIterative {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();

        int[] value=new int[n];
        int[] weight=new int[n];
        int w;

        for (int i = 0; i < n; i++) weight[i]=sc.nextInt();
        for (int i = 0; i < n; i++) value[i]=sc.nextInt();
        w=sc.nextInt();
        int[] dp=new int[w+1];
        int[] bk=new int[w+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= w; j++) {
                if(weight[i-1]<=j)
                    dp[j]=Math.max(bk[j],value[i-1]+bk[j-weight[i-1]]);
                else
                    dp[j]=bk[j];

            }
            bk=dp;
            dp=new int[w+1];
        }
        System.out.print(bk[w]);

    }
}
