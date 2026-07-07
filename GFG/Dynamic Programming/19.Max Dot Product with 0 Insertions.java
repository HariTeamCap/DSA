class Solution {
    public int maxDotProduct(int[] a, int[] b) {

        int n = a.length;
        int m = b.length;

        long[][] dp = new long[n + 1][m + 1];

        long NEG = -(long)1e18;

        for (int j = 1; j <= m; j++)
            dp[0][j] = NEG;

        for (int i = 1; i <= n; i++) {

            dp[i][0] = 0;

            for (int j = 1; j <= Math.min(i, m); j++) {

                dp[i][j] = Math.max(
                        dp[i - 1][j],
                        dp[i - 1][j - 1] + 1L * a[i - 1] * b[j - 1]
                );
            }
        }

        return (int) dp[n][m];
    }
}

// Idea
// You have:
// a of length n
// b of length m (m <= n)
// You may insert zeros into b, which means you choose m positions in a where the elements of b will align. The remaining positions contribute 0.
// So the problem becomes:
// Choose positions in a to match b (in order) such that the dot product is maximum.

// Complexity
// Time: O(n × m)
// Space: O(n × m)