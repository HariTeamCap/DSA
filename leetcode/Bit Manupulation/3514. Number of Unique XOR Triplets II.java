class Solution {

    public int uniqueXorTriplets(int[] nums) {

        final int MAX = 2048;

        boolean[][] dp = new boolean[4][MAX];
        dp[0][0] = true;

        for (int pick = 1; pick <= 3; pick++) {

            for (int xor = 0; xor < MAX; xor++) {

                if (!dp[pick - 1][xor])
                    continue;

                for (int num : nums) {
                    dp[pick][xor ^ num] = true;
                }
            }
        }

        int ans = 0;

        for (boolean possible : dp[3]) {
            if (possible)
                ans++;
        }

        return ans;
    }
}

//Algorithm: Explanation
// 1. We can use dynamic programming to solve this problem. We will maintain a 2D boolean array dp where dp[pick][xor] will be true if we can pick 'pick' numbers from the array such that their XOR is equal to 'xor'.
// 2. We initialize dp[0][0] to true because we can always pick 0 numbers to get an XOR of 0.
// 3. We iterate through the number of picks from 1 to 3. For each pick, we iterate through all possible XOR values from 0 to MAX (which is 2048 in this case, as the maximum value of nums[i] is 1000, and the maximum XOR value can be 2047).
// 4. For each possible XOR value, if dp[pick - 1][xor] is true, we can pick another number from the array and update dp[pick][xor ^ num] to true for each number in the array.
// 5. Finally, we count the number of true values in dp[3] to get the number of unique XOR triplets and return that count as the answer.    


// Time Complexity: O(3 * MAX * n) where n is the length of the input array nums. The outer loop runs 3 times (for picks 1 to 3), the middle loop runs MAX times (for all possible XOR values), and the inner loop runs n times (for each number in nums).  
// Space Complexity: O(4 * MAX) for the dp array, which is a constant space of size 4 * 2048.