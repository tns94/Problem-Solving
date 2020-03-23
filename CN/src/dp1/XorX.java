package dp;

import java.util.Scanner;

/*
10 8 7
17 13  0 0 1000 1000 1000 0 7 999
 */
/*
Find min and max after k operations :-  Sort array and xor alternate numbers with x.
*/
public class XorX {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
            int n=sc.nextInt();
            int arr[]=new int[n];
            int k=sc.nextInt();
            int x=sc.nextInt();

            int cur[]=new int[1024];
            int store[]=new int[1024];
            int co=0;
            for (int i = 0; i < n; i++) {

                cur[sc.nextInt()]++;
            }
            int nn=0;
            for(int j=0;j<k;j++)
            {
                for(int i=0;i<1024;i++)
                {
                    if (cur[i]>0)
                    {
                        if (co%2==0 && cur[i]%2!=0)
                        {
                            nn=(cur[i]/2)+1;
                            store[i^x]+=nn;
                            store[i]+=cur[i]-nn;
                        }
                        else
                        {
                            nn=cur[i]/2;
                            store[i^x]+=nn;
                            store[i]+=cur[i]-nn;
                        }
                        co+=cur[i];

                    }

                }
                co=0;
                cur=store;
                store=new int[1024];
            }

            int i=1024;
            while(cur[--i]==0);
            System.out.print(i+" ");
            i=-1;
            while(cur[++i]==0);
            System.out.print(i);



        }
}
