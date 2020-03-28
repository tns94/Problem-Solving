package dp2;

import java.util.Scanner;

public class CharlieAndPilot {

    static int minSalary(int[] cap,int[] ast,int as,int ca,int si)
    {
        if(si==ast.length)
            return 0;

        if(as-ca==0)
            return  minSalary(cap,ast,as+1,ca,si+1)+ast[si];
        else if((as-ca)==ast.length-si)
            return  minSalary(cap,ast,as,ca+1,si+1)+cap[si];
        else
            return Math.min(minSalary(cap,ast,as,ca+1,si+1)+cap[si],minSalary(cap,ast,as+1,ca,si+1)+ast[si]);
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int[] cap=new int[n];
        int[] ast=new int[n];

        for (int i = 0; i < n; i++) {
            cap[i]=sc.nextInt();
            ast[i]=sc.nextInt();
        }
        System.out.println(minSalary(cap,ast,0,0,0));
    }
}
