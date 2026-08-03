class Solution {
    public int maxSumWithK(int[] arr, int k) {
        int n = arr.length;

        // maxSum[i] = maximum subarray sum ending at i
        int[] maxSum = new int[n];
        maxSum[0] = arr[0];

        for (int i = 1; i < n; i++) {
            maxSum[i] = Math.max(arr[i], maxSum[i - 1] + arr[i]);
        }

        int windowSum = 0;

        // First window of size k
        for (int i = 0; i < k; i++)
            windowSum += arr[i];

        int ans = windowSum;

        for (int i = k; i < n; i++) {
            windowSum += arr[i] - arr[i - k];

            ans = Math.max(ans, windowSum);

            // Extend the window using Kadane result
            ans = Math.max(ans, windowSum + maxSum[i - k]);
        }

        return ans;
    }
}

//Time Complexity: O(n) where n is the size of the array.
//Space Complexity: O(n) where n is the size of the array.

//Algorithm: Explanation
// The problem is to find the maximum sum of a subarray of size at least k.
// To solve this problem, we can use a combination of Kadane's algorithm and a sliding window approach. First, we calculate the maximum subarray sum ending at each index using Kadane's algorithm. Then, we use a sliding window of size k to calculate the sum of the first k elements. We then slide the window across the array, updating the sum and checking if we can extend the window using the previously calculated maximum subarray sums. The maximum of these sums will give us the answer.
