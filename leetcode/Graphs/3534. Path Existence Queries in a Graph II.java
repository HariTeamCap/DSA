import java.util.*;

class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) order[i] = i;
        Arrays.sort(order, (a, b) -> Integer.compare(nums[a], nums[b]));

        int[] sortedVal = new int[n];
        int[] pos = new int[n]; // pos[originalNode] = sorted index
        for (int k = 0; k < n; k++) {
            sortedVal[k] = nums[order[k]];
            pos[order[k]] = k;
        }

        // Two-pointer: R[k] = furthest index j with sortedVal[j] - sortedVal[k] <= maxDiff
        int[] R = new int[n];
        int j = 0;
        for (int k = 0; k < n; k++) {
            if (j < k) j = k;
            while (j + 1 < n && sortedVal[j + 1] - sortedVal[k] <= maxDiff) j++;
            R[k] = j;
        }

        // Component ids via consecutive-gap grouping
        int[] comp = new int[n];
        for (int k = 1; k < n; k++) {
            comp[k] = (sortedVal[k] - sortedVal[k - 1] <= maxDiff) ? comp[k - 1] : comp[k - 1] + 1;
        }

        // Binary lifting table on R
        int LOG = 1;
        while ((1 << LOG) < n) LOG++;
        LOG += 1;
        int[][] up = new int[LOG][n];
        up[0] = R;
        for (int p = 1; p < LOG; p++)
            for (int i = 0; i < n; i++)
                up[p][i] = up[p - 1][up[p - 1][i]];

        int q = queries.length;
        int[] ans = new int[q];
        for (int qi = 0; qi < q; qi++) {
            int u = queries[qi][0], v = queries[qi][1];
            if (u == v) { ans[qi] = 0; continue; }

            int pu = pos[u], pv = pos[v];
            int s = Math.min(pu, pv), t = Math.max(pu, pv);

            if (comp[s] != comp[t]) { ans[qi] = -1; continue; }

            int cur = s, steps = 0;
            for (int p = LOG - 1; p >= 0; p--) {
                if (up[p][cur] < t) {
                    cur = up[p][cur];
                    steps += (1 << p);
                }
            }
            ans[qi] = steps + 1;
        }
        return ans;
    }
}


// Total:O((n + q) log n)


// Approach
// Key insight: Sort nodes by nums value. In this sorted order, node k connects to a contiguous range of neighbors [L(k), R(k)] — every node whose value is within maxDiff — because values are sorted, so the "connectable" neighbors of any node form a contiguous window.
// Connectivity (components): Two sorted-adjacent nodes are in the same component iff their value gap ≤ maxDiff. This fully determines connectivity — if a gap between consecutive sorted values exceeds maxDiff, no edge can ever cross it (since crossing requires an even bigger value difference). So just scan sorted values once and cut components wherever the gap exceeds maxDiff.
// Shortest distance (the hard part): Since each node's neighbor range is contiguous and monotonic (both L(k) and R(k) are non-decreasing as k increases — a sliding-window property), BFS "layers" from a source s also stay contiguous: layer i = [L^i(s), R^i(s)] (applying L/R repeatedly).
// For a query with s ≤ t (sorted positions), note L^i(s) ≤ s ≤ t always holds — so the left bound never matters! The answer reduces to: minimum k such that R^k(s) ≥ t, i.e., minimum jumps rightward. This is solvable via binary lifting in O(log n) per query.