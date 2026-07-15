// Approach
// For every index i:
// inc[i] = length of the longest non-decreasing subarray ending at i.
// dec[i] = length of the longest non-increasing subarray starting at i.
// Then the longest bitonic subarray having peak at i is:
// inc[i] + dec[i] - 1
// Subtract 1 because arr[i] gets counted twice.

// Algorithm
// Compute inc[] from left to right.
// Compute dec[] from right to left.
// For every index, compute
// inc[i] + dec[i] - 1
// and take the maximum.

class Solution {
    public int bitonic(int[] arr) {

        int n = arr.length;

        int[] inc = new int[n];
        int[] dec = new int[n];

        inc[0] = 1;
        for (int i = 1; i < n; i++) {
            if (arr[i] >= arr[i - 1])
                inc[i] = inc[i - 1] + 1;
            else
                inc[i] = 1;
        }

        dec[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] >= arr[i + 1])
                dec[i] = dec[i + 1] + 1;
            else
                dec[i] = 1;
        }

        int ans = 1;

        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, inc[i] + dec[i] - 1);
        }

        return ans;
    }
}

// Time Complexity: O(n)
// Space Complexity: O(n)