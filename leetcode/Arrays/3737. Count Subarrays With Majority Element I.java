class Solution {
    public int countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        int ans = 0;

        for (int i = 0; i < n; i++) {
            int cnt = 0;

            for (int j = i; j < n; j++) {
                if (nums[j] == target)
                    cnt++;

                int len = j - i + 1;

                if (cnt > len / 2)
                    ans++;
            }
        }

        return ans;
    }
}
// Idea
// For every starting index:
// Extend the subarray one element at a time.
// Keep track of:
// cnt = number of occurrences of target
// len = current subarray length
// target is the majority if:
// cnt> 
// 2
// len
// ​	
 
// If true, increment the answer.

//Time: O(n²)
// Space: O(1)