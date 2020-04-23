package dp1;

public class Boredom {
    public static int solve(int n,int A[])
    {
        int size=0;
        for (int i = 0; i < n; i++) {
            size=size<A[i]?A[i]:size;
        }
        size++;
        int arr[]=new int[size];
        int dp[]=new int[size];
        for (int i = 0; i < n; i++) {
            arr[A[i]]++;
        }
        dp[1]=arr[1];
        for (int i = 2; i < size; i++) {
            dp[i]= Math.max(dp[i-2]+(i*arr[i]),dp[i-1]);
        }
        return dp[size-1];

    }

    public static void main(String[] args) {
        System.out.println(solve(3,new int[]{1,2,3}));
    }
}
