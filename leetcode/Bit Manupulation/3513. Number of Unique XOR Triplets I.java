class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;

        if (n <= 2){
            return n;
        }

        int ans = 1;
        while (ans <= n){
            ans <<= 1;
        } 
        return ans;
    }
}

// Algorithm
// If n <= 2, return n.
// Otherwise,
// Find the smallest power of two greater than n.
// Return it.

// Complexity
// Time: O(log n)
// Space: O(1)