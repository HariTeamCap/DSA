class Solution {

    class BIT {
        int[] tree;

        BIT(int n) {
            tree = new int[n + 2];
        }

        void update(int idx, int val) {
            while (idx < tree.length) {
                tree[idx] += val;
                idx += idx & -idx;
            }
        }

        int query(int idx) {
            int sum = 0;
            while (idx > 0) {
                sum += tree[idx];
                idx -= idx & -idx;
            }
            return sum;
        }
    }

    public long countMajoritySubarrays(int[] nums, int target) {

        int n = nums.length;

        int[] prefix = new int[n + 1];
        int cur = 0;
        int min = 0, max = 0;

        for (int i = 0; i < n; i++) {
            cur += (nums[i] == target) ? 1 : -1;
            prefix[i + 1] = cur;
            min = Math.min(min, cur);
            max = Math.max(max, cur);
        }

        int offset = 1 - min;
        BIT bit = new BIT(max - min + 3);

        long ans = 0;

        // insert prefix 0
        bit.update(prefix[0] + offset, 1);

        for (int i = 1; i <= n; i++) {
            int idx = prefix[i] + offset;

            // previous prefix sums strictly smaller
            ans += bit.query(idx - 1);

            bit.update(idx, 1);
        }

        return ans;
    }
}


// Approach
// Convert the array into +1 and -1.
// Compute prefix sums.
// A valid subarray has positive sum.
// For each prefix sum, count earlier prefix sums that are smaller using a Fenwick Tree.
// Add the count to the answer.
// Complexity
// Time: O(n log n)
// Space: O(n)