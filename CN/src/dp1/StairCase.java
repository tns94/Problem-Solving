package dp1;

public class StairCase {


    public static long staircase(int n){

        long[] op=new long[n+1];
        return sc(n,op);
    }

    static long sc(int n,long[] op)
    {

        if (n==0 || n==1)
            return 1;
        if (n==2)
            return 2;


        if (op[n]!=0l)
            return op[n];
        long ans= sc(n-1,op)+sc(n-2,op)+sc(n-3,op);
        op[n]=ans;
        return ans;
    }
    public static void main(String[] args) {
        System.out.println(staircase(70));

    }

}
