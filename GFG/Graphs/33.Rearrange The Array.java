// Approach
// Treat the permutation as a graph.
// Every index points to b[i].
// This forms one or more disjoint cycles.
// An element returns to its original position only after completing its cycle.
// Therefore, the answer is the LCM of the lengths of all cycles.
// Since the answer can be huge, compute the LCM modulo 1e9+7.
// Formula:
// LCM(a,b) = (a / gcd(a,b)) * b

class Solution {

    static final long MOD = 1000000007L;

    public int minOperations(int[] b) {

        int n = b.length;
        boolean[] vis = new boolean[n];

        long ans = 1;

        for (int i = 0; i < n; i++) {

            if (vis[i]) continue;

            int len = 0;
            int cur = i;

            while (!vis[cur]) {
                vis[cur] = true;
                cur = b[cur] - 1;
                len++;
            }

            ans = lcm(ans, len) % MOD;
        }

        return (int) ans;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long t = a % b;
            a = b;
            b = t;
        }
        return a;
    }

    private long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }
}

// Time Complexity: O(n log n), where n is the length of the array. We traverse the array once to find cycles, and for each cycle, we compute the LCM which involves computing GCD in logarithmic time.
// space Complexity: O(n), where n is the length of the array. We use an additional boolean array to keep track of visited indices.