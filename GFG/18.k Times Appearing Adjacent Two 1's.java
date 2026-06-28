class Solution {

    static final int MOD = 1000000007;

    public int countStrings(int n, int k) {

        long[][][] dp = new long[n + 1][k + 1][2];

        dp[1][0][0] = 1;
        dp[1][0][1] = 1;

        for (int i = 2; i <= n; i++) {

            for (int j = 0; j <= k; j++) {

                // Append 0
                dp[i][j][0] =
                        (dp[i - 1][j][0] + dp[i - 1][j][1]) % MOD;

                // Append 1 after 0
                dp[i][j][1] =
                        (dp[i][j][1] + dp[i - 1][j][0]) % MOD;

                // Append 1 after 1
                if (j > 0) {
                    dp[i][j][1] =
                        (dp[i][j][1] + dp[i - 1][j - 1][1]) % MOD;
                }
            }
        }

        return (int)((dp[n][k][0] + dp[n][k][1]) % MOD);
    }
}

// Approach
// Use DP with three parameters:
// current length
// adjacent 11 count
// last bit
// Extend strings by appending 0 or 1.
// Appending 1 after 1 increases the adjacent-pair count.
// Sum both ending states for the final answer.

//Time: O(n x k)
//Space: O(n x k)