class Solution {

    public int count(int n, int m) {

        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i <= m; i++)
            dp[1][i] = 1;

        for (int len = 2; len <= n; len++) {

            for (int x = 1; x <= m; x++) {

                for (int d = 1; d * d <= x; d++) {

                    if (x % d == 0) {

                        dp[len][x] += dp[len - 1][d];

                        if (d != x / d)
                            dp[len][x] += dp[len - 1][x / d];
                    }
                }

                for (int multiple = 2 * x; multiple <= m; multiple += x)
                    dp[len][x] += dp[len - 1][multiple];
            }
        }

        int ans = 0;

        for (int i = 1; i <= m; i++)
            ans += dp[n][i];

        return ans;
    }
}

//Algorithm: Explanation:
// 1. Create a 2D array dp of size (n+1) x (m+1) initialized to 0, where dp[len][x] represents the number of valid sequences of length len ending with the number x.
// 2. Initialize the base case: for sequences of length 1, there is exactly one valid sequence for each number from 1 to m.
// 3. For each length from 2 to n, and for each number x from 1 to m, calculate the number of valid sequences of that length ending with x by considering all divisors of x and all multiples of x. Update dp[len][x] accordingly.
// 4. Finally, sum up all the valid sequences of length n for all numbers from 1 to m and return the result as the answer.

// Time Complexity: O(n * m * sqrt(m)), where n is the length of the sequence and m is the maximum number in the sequence. The sqrt(m) factor comes from finding divisors of x.
// Space Complexity: O(n * m), for the dp array.