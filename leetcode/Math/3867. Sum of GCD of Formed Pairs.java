// Approach
// Build prefixGcd[]
// Maintain the maximum element seen so far (mx).
// For each index:
// Update mx.
// prefixGcd[i] = gcd(nums[i], mx).
// Sort prefixGcd.
// Pair:
// Smallest with largest
// Second smallest with second largest
// ...
//Add the GCD of every pair

import java.util.Arrays;

class Solution {

    public long gcdSum(int[] nums) {

        int n = nums.length;
        int[] prefixGcd = new int[n];

        int mx = 0;

        for (int i = 0; i < n; i++) {
            mx = Math.max(mx, nums[i]);
            prefixGcd[i] = gcd(nums[i], mx);
        }

        Arrays.sort(prefixGcd);

        long ans = 0;

        int left = 0;
        int right = n - 1;

        while (left < right) {
            ans += gcd(prefixGcd[left], prefixGcd[right]);
            left++;
            right--;
        }

        return ans;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }
}

// Time Complexity: O(n log n) for sorting
// Space Complexity: O(n) for prefixGcd