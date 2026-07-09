class Solution {
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {

        int[] component = new int[n];
        int id = 0;
        component[0] = id;

        for (int i = 1; i < n; i++) {
            if (nums[i] - nums[i - 1] > maxDiff) {
                id++;
            }
            component[i] = id;
        }

        boolean[] ans = new boolean[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int u = queries[i][0];
            int v = queries[i][1];

            ans[i] = component[u] == component[v];
        }

        return ans;
    }
}


// Time Complexity: O(n + q), where n is the length of nums and q is the number of queries.
// Space Complexity: O(n) for the component array.

// Approach (Connected Components)
// Since nums is already sorted, we don't need to build all possible edges.
// Observe:
// If
// nums[i + 1] - nums[i] <= maxDiff
// then node i is connected to node i + 1.
// And because the array is sorted, every node in the same continuous segment becomes connected.
// For example,
// nums = [1,2,3,7,8,10]
// maxDiff = 2
// Connections:
// 1 -- 2 -- 3      7 -- 8 -- 10
// So there are only two connected components.
// We simply assign every node a component id.
// Then for every query,
// (u,v)
// check whether
// component[u] == component[v]
// If yes → true
// Otherwise → false