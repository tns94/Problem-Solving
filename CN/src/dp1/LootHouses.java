package dp1;

import java.util.Scanner;

public class LootHouses {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int p=sc.nextInt(),c=sc.nextInt();
        int max=p>c?p:c;int te=0;
        for (int i = 2; i < n; i++) {
            te=sc.nextInt()+p;
            p=c>p?c:p;
            c=te;
            max=max<c?c:max;

        }
        System.out.println(max);
    }
}
