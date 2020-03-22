package dp;

/*
Number of Possible BST from with node n
 */
public class NumberOfBST {

    static long nBST(int n,int arr[])
    {
        if (n==0)
            return 1;
        if(n==1)
            return 1;
        if(arr[n]!=0)
            return arr[n];

        int sum=0;
        for (int i = 1; i <= n; i++) {
            sum=(int)(sum  +   (    nBST(i-1,arr)   *   nBST(n-i,arr)    )%1000000007)%1000000007;
        }

        arr[n]=sum;
        return sum;
    }

    public static void main(String[] args) {
        int n=1000;
        int arr[]=new int[n+1];
        //Input
        System.out.println(nBST(n,arr));
    }
}
