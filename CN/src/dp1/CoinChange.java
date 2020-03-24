package dp1;
/*
Minimum coin problem
 */
public class CoinChange {


    static int ways(int n, int[] d,int si,int dlen, int[][] op)
    {
        if(n==0)
            return 1;
        if(dlen-si==0 || n<0)
            return 0;
        if (op[n][dlen-si]!=-1)
            return op[n][dlen-si];
        int ans=ways(n-d[si],d,si,dlen,op);
        ans+=ways(n,d,si+1,dlen,op);
        op[n][dlen-si]=ans;
        return ans;


    }
    public static int countWaysToMakeChange(int denominations[], int value){
        int [][] op=new int[value+1][denominations.length+1];
        for (int i = 0; i < value + 1; i++) {
            for (int j = 0; j < denominations.length + 1; j++) {
                op[i][j]=-1;
            }
        }
        return ways(value,denominations,0,denominations.length,op);

    }

    public static void main(String[] args) {
        System.out.println(countWaysToMakeChange(new int[]{1,2,3},4));
    }

}
