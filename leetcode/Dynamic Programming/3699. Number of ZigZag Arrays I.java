class Solution {
    static final int MOD = 1_000_000_007;

    public int zigZagArrays(int n, int l, int r) {

        int m = r - l + 1;

        if (n == 1) return m;

        long[] up = new long[m];
        long[] down = new long[m];

        for (int i = 0; i < m; i++) {
            up[i] = i;               // smaller values before it
            down[i] = m - 1 - i;     // larger values before it
        }

        if (n == 2) {
            long ans = 0;
            for (int i = 0; i < m; i++) {
                ans = (ans + up[i] + down[i]) % MOD;
            }
            return (int) ans;
        }

        for (int len = 3; len <= n; len++) {

            long[] prefUp = new long[m + 1];
            long[] prefDown = new long[m + 1];

            for (int i = 0; i < m; i++) {
                prefUp[i + 1] = (prefUp[i] + up[i]) % MOD;
                prefDown[i + 1] = (prefDown[i] + down[i]) % MOD;
            }

            long totalUp = prefUp[m];
            long totalDown = prefDown[m];

            long[] newUp = new long[m];
            long[] newDown = new long[m];

            for (int y = 0; y < m; y++) {

                newUp[y] = prefDown[y];

                newDown[y] =
                    (totalUp - prefUp[y + 1] + MOD) % MOD;
            }

            up = newUp;
            down = newDown;
        }

        long ans = 0;

        for (int i = 0; i < m; i++) {
            ans = (ans + up[i] + down[i]) % MOD;
        }

        return (int) ans;
    }
}
// Time: O(n × (r - l + 1))
// Space: O(r - l + 1)