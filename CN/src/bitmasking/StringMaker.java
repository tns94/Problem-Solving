package bitmasking;

public class StringMaker {
    static int mod = 1000000007;

    public static int solve(String a, String b, String c) {
        char[] a1 = a.toCharArray();
        char[] b1 = b.toCharArray();
        char[] c1 = c.toCharArray();
        int al = a1.length, bl = b1.length, cl = c1.length;
        int[][][] dp = new int[al + 1][bl + 1][cl + 1];
        for (int i = 0; i <= al; i++) {
            for (int j = 0; j <= bl; j++) {
                for (int k = 0; k <= cl; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }
        return (int) stringMaker(a1, b1, c1, al, bl, cl - 1, dp);

    }

    static long stringMaker(char[] a, char[] b, char[] c, int x, int y, int z, int[][][] dp) {
        if (z < 0)
            return 1;

        if (x < 0 || y < 0)
            return 0;

        if (dp[x][y][z] != -1)
            return dp[x][y][z];

        long co = 0;
        for (int i = x - 1; i >= 0; i--) {
            if (a[i] == c[z])
                co = (co + stringMaker(a, b, c, i, y, z - 1, dp)) % mod;
        }

        for (int i = y - 1; i >= 0; i--) {
            if (b[i] == c[z])
                co = (co + stringMaker(a, b, c, x, i, z - 1, dp)) % mod;
        }
        return dp[x][y][z] = (int) co;
    }

    public static void main(String[] args) {
        //"abbcd","bccde","abcde"
        System.out.println(solve("aa", "aa", "aa"));
    }
}
