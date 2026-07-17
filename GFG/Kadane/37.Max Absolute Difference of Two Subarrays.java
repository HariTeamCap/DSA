// Approach
// We need four arrays:
// leftMax[i] = Maximum subarray sum in arr[0...i]
// leftMin[i] = Minimum subarray sum in arr[0...i]
// rightMax[i] = Maximum subarray sum in arr[i...n-1]
// rightMin[i] = Minimum subarray sum in arr[i...n-1]
// Using Kadane's algorithm twice (left→right and right→left).
// Then for every split between i and i+1:
// Case 1:
// |leftMax - rightMin|

// Case 2:
// |leftMin - rightMax|
// Take the maximum.

class Solution {
    public int maxDiffSubArrays(int[] arr) {

        int n = arr.length;

        int[] leftMax = new int[n];
        int[] leftMin = new int[n];
        int[] rightMax = new int[n];
        int[] rightMin = new int[n];

        // Left Max
        int curr = arr[0];
        leftMax[0] = arr[0];
        for (int i = 1; i < n; i++) {
            curr = Math.max(arr[i], curr + arr[i]);
            leftMax[i] = Math.max(leftMax[i - 1], curr);
        }

        // Left Min
        curr = arr[0];
        leftMin[0] = arr[0];
        for (int i = 1; i < n; i++) {
            curr = Math.min(arr[i], curr + arr[i]);
            leftMin[i] = Math.min(leftMin[i - 1], curr);
        }

        // Right Max
        curr = arr[n - 1];
        rightMax[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            curr = Math.max(arr[i], curr + arr[i]);
            rightMax[i] = Math.max(rightMax[i + 1], curr);
        }

        // Right Min
        curr = arr[n - 1];
        rightMin[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            curr = Math.min(arr[i], curr + arr[i]);
            rightMin[i] = Math.min(rightMin[i + 1], curr);
        }

        int ans = 0;

        for (int i = 0; i < n - 1; i++) {
            ans = Math.max(ans,
                    Math.abs(leftMax[i] - rightMin[i + 1]));

            ans = Math.max(ans,
                    Math.abs(leftMin[i] - rightMax[i + 1]));
        }

        return ans;
    }
}

// Time Complexity: O(n)
// Space Complexity: O(n)