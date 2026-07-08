class Solution {
    static final int MOD = 1_000_000_007;
    int n;
    long[] segVal;
    int[] segCnt;
    long[] pow10;

    public int[] sumAndMultiply(String s, int[][] queries) {
        n = s.length();
        char[] c = s.toCharArray();

        // Precompute powers of 10 mod p (index = count of non-zero digits)
        pow10 = new long[n + 1];
        pow10[0] = 1;
        for (int i = 1; i <= n; i++) pow10[i] = pow10[i - 1] * 10 % MOD;

        // Prefix sums of digit values (zeros contribute 0 anyway)
        long[] prefSum = new long[n + 1];
        for (int i = 0; i < n; i++) prefSum[i + 1] = prefSum[i] + (c[i] - '0');

        // Build segment tree (1-indexed, size 4n)
        segVal = new long[4 * n];
        segCnt = new int[4 * n];
        build(1, 0, n - 1, c);

        int q = queries.length;
        int[] ans = new int[q];
        for (int i = 0; i < q; i++) {
            int l = queries[i][0], r = queries[i][1];
            long enc = query(1, 0, n - 1, l, r);
            long xVal = enc / 200000L;      // decode value
            long sumMod = (prefSum[r + 1] - prefSum[l]) % MOD;
            ans[i] = (int) ((xVal * sumMod) % MOD);
        }
        return ans;
    }

    private void build(int node, int l, int r, char[] c) {
        if (l == r) {
            int d = c[l] - '0';
            if (d != 0) {
                segVal[node] = d;
                segCnt[node] = 1;
            } else {
                segVal[node] = 0;
                segCnt[node] = 0;
            }
            return;
        }
        int mid = (l + r) >>> 1;
        build(2 * node, l, mid, c);
        build(2 * node + 1, mid + 1, r, c);
        segCnt[node] = segCnt[2 * node] + segCnt[2 * node + 1];
        segVal[node] = (segVal[2 * node] * pow10[segCnt[2 * node + 1]] + segVal[2 * node + 1]) % MOD;
    }

    // Returns encoded (val * 200000 + cnt); cnt < 100001 so this is safe/unique
    private long query(int node, int l, int r, int ql, int qr) {
        if (qr < l || r < ql) return 0L; // val=0, cnt=0
        if (ql <= l && r <= qr) return segVal[node] * 200000L + segCnt[node];

        int mid = (l + r) >>> 1;
        long leftEnc = query(2 * node, l, mid, ql, qr);
        long rightEnc = query(2 * node + 1, mid + 1, r, ql, qr);

        long lv = leftEnc / 200000L, lc = leftEnc % 200000L;
        long rv = rightEnc / 200000L, rc = rightEnc % 200000L;

        long mergedVal = (lv * pow10[(int) rc] + rv) % MOD;
        long mergedCnt = lc + rc;
        return mergedVal * 200000L + mergedCnt;
    }
}

//Time: O((n + q log n))
//Space: O(n) for segment tree and prefix sum


// Approach
// Problem breakdown: For each query range [l, r], you need two things:

// sum — sum of non-zero digits in the range (though zeros contribute 0 anyway, so it's just sum of all digits)
// x — the number formed by concatenating non-zero digits in order, mod 1e9+7

// The naive approach (build the substring, extract digits, form the number) is O(range length) per query, which is up to O(n) — with q up to 1e5 queries, worst case is O(n·q) = 1e10, way too slow → TLE, which matches what you were seeing.
// Key idea — Segment Tree with a custom merge:
// Think of each segment tree node as storing two things about its range:

// val — the concatenated-digit number (non-zero digits only), mod 1e9+7
// cnt — how many non-zero digits are in that range

// The clever part is the merge operation. If you know the left child's (val, cnt) and right child's (val, cnt), concatenation math tells you:
// merged.val = left.val * 10^(right.cnt) + right.val   (mod 1e9+7)
// merged.cnt = left.cnt + right.cnt
// This is because shifting left.val left by right.cnt decimal digits (multiplying by 10^cnt) makes room to append right.val. This is exactly why you need the count alongside the value — the count tells you how far to shift.

// Leaf nodes: single digit. If non-zero, val = digit, cnt = 1. If zero, val = 0, cnt = 0.
// Build: merge bottom-up, standard segment tree build.
// Query: combine O(log n) segments that make up the range [l, r], using the same merge rule, giving you x mod (1e9+7) and count in O(log n).
// Sum of digits: just a prefix sum array, O(1) per query — no need for a second segment tree.
// Final answer: (x mod p) * (sum mod p) mod p.