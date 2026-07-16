// Approach
// Let
// dp[i][j]
// represent the number of ways to form an i-digit number whose digits sum to j.
// Initialization
// The first digit cannot be 0.
// So,
// for (digit = 1 to 9)
//     dp[1][digit] = 1
// Transition
// For every additional digit (which can be 0 to 9):
// dp[i][j] += dp[i-1][j-digit]
// Finally,
// If dp[n][sum] == 0, return -1.
// Otherwise return dp[n][sum].

class Solution {
    public int countWays(int n, int sum) {
        // code here
    

        int[][] dp = new int[n + 1][sum + 1];

        // First digit cannot be zero
        for (int d = 1; d <= 9 && d <= sum; d++) {
            dp[1][d] = 1;
        }

        // Build DP
        for (int i = 2; i <= n; i++) {
            for (int s = 0; s <= sum; s++) {
                for (int d = 0; d <= 9; d++) {
                    if (s >= d) {
                        dp[i][s] += dp[i - 1][s - d];
                    }
                }
            }
        }

        return dp[n][sum] == 0 ? -1 : dp[n][sum];
    
    }
};

// Time Complexity: O(n * sum * 10)
// Space Complexity: O(n * sum)