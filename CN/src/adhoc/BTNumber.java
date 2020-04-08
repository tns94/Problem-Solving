package adhoc;
/*
No. of binary trees
 */
public class BTNumber {
    public static void main(String[] args) {
        System.out.println(balancedTreesOfHeightH(6));
    }
    public static int balancedTreesOfHeightH(int height){
        if(height==0 || height==1)
            return 1;
        int m=1000000007;
        long x=balancedTreesOfHeightH(height-1);
        long y=balancedTreesOfHeightH(height-2);
        int ans;

        ans=(int)((x*x)%m);
        ans=(ans+  (2*  ((int)((x*y)%m)  )%m  )  %m   )%m;
        return ans;
    }

}
