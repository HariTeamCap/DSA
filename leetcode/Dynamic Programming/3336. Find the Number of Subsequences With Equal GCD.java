// Approach
// Since
// n ≤ 200
// nums[i] ≤ 200
// the GCD can only be between 0...200.
// Maintain DP states
// dp[g1][g2]
// where
// g1 = gcd of subsequence1
// g2 = gcd of subsequence2
// Initially
// dp[0][0] = 1
// For every number x
// three choices
// Ignore it
// Put into seq1
// Put into seq2
// Transition
// newG1 = gcd(g1,x)
// newG2 = gcd(g2,x)
// Finally sum all states where
// g1 == g2
// g1 > 0


// Time: O(n × 201²)
// Space: O(201²)


class Solution {

    static final int MOD = 1_000_000_007;

    public int subsequencePairCount(int[] nums) {

        int MAX = 200;

        long[][] dp = new long[MAX + 1][MAX + 1];
        dp[0][0] = 1;

        for (int x : nums) {

            long[][] next = new long[MAX + 1][MAX + 1];

            for (int g1 = 0; g1 <= MAX; g1++) {
                for (int g2 = 0; g2 <= MAX; g2++) {

                    if (dp[g1][g2] == 0)
                        continue;

                    long ways = dp[g1][g2];

                    // Ignore current element
                    next[g1][g2] = (next[g1][g2] + ways) % MOD;

                    // Put into seq1
                    int ng1 = (g1 == 0) ? x : gcd(g1, x);
                    next[ng1][g2] = (next[ng1][g2] + ways) % MOD;

                    // Put into seq2
                    int ng2 = (g2 == 0) ? x : gcd(g2, x);
                    next[g1][ng2] = (next[g1][ng2] + ways) % MOD;
                }
            }

            dp = next;
        }

        long ans = 0;

        for (int g = 1; g <= MAX; g++) {
            ans = (ans + dp[g][g]) % MOD;
        }

        return (int) ans;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }
}