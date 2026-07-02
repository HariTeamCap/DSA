class Solution {
    public boolean divisibleByK(int[] arr, int k) {
        // code here
        boolean[] dp = new boolean[k];
        for(int num : arr){
            boolean[] next = dp.clone();
            next[num % k] = true;
            for(int r = 0; r < k; r++){
                if(dp[r]){
                    next[(r + num) % k] = true;
                }
            }
            dp = next;
            if(dp[0])
                return true;
        }
        return false;
    }
}

// Idea
// Maintain a boolean array where:
// dp[r] = true means there exists a subset whose sum % k == r.
// For every element, update the reachable remainders.
// If at any point dp[0] becomes true, return true.
// Time Complexity
// O(n × k)
// Space: O(k)