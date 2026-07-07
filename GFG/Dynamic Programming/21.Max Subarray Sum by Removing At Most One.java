class Solution {
    public int maxSumSubarray(int[] arr) {

        int n = arr.length;

        int[] fw = new int[n];
        int[] bw = new int[n];

        fw[0] = arr[0];
        int ans = arr[0];

        for (int i = 1; i < n; i++) {
            fw[i] = Math.max(arr[i], fw[i - 1] + arr[i]);
            ans = Math.max(ans, fw[i]);
        }

        bw[n - 1] = arr[n - 1];

        for (int i = n - 2; i >= 0; i--) {
            bw[i] = Math.max(arr[i], bw[i + 1] + arr[i]);
        }

        for (int i = 1; i < n - 1; i++) {
            ans = Math.max(ans, fw[i - 1] + bw[i + 1]);
        }

        return ans;
    }
}

// Complexity
// Time: O(n)
// Space: O(1)

// DP Idea
// Maintain two states:
// keep
// Maximum subarray sum ending at current index without deleting any element.
// delete
// Maximum subarray sum ending at current index after deleting exactly one element.