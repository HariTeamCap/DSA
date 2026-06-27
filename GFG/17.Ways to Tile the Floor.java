class Solution {
    static final int MOD = 1000000007;

    public int countWays(int n, int m) {

        long[] dp = new long[n + 1];

        dp[0] = 1;

        for (int i = 1; i <= n; i++) {

            if (i < m) {
                dp[i] = 1;
            } 
            else if (i == m) {
                dp[i] = 2;
            } 
            else {
                dp[i] = (dp[i - 1] + dp[i - m]) % MOD;
            }
        }

        return (int) dp[n];
    }
}

// Approach
// Create a DP array where dp[i] stores the number of ways to tile an i × m floor.
// If i < m, only horizontal placement is possible.
// If i == m, either all horizontal or all vertical.
// Otherwise:
// Extend a horizontal arrangement: dp[i-1]
// Extend a vertical arrangement: dp[i-m]
// Return dp[n] % MOD.
// Time Complexity
// O(n)
// Space Complexity
// O(n)