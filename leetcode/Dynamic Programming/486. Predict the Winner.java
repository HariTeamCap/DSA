class Solution {
    public boolean predictTheWinner(int[] nums) {

        int n = nums.length;
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = nums[i];
        }

        for (int len = 2; len <= n; len++) {

            for (int i = 0; i + len - 1 < n; i++) {

                int j = i + len - 1;

                int left = nums[i] - dp[i + 1][j];
                int right = nums[j] - dp[i][j - 1];

                dp[i][j] = Math.max(left, right);
            }
        }

        return dp[0][n - 1] >= 0;
    }
}

//Algorithm: Explanation:
// 1. Create a 2D array dp of size n x n, where dp[i][j] represents the maximum score difference the current player can achieve over the other player for the subarray nums[i...j].
// 2. Initialize the diagonal of the dp array with the values of nums, as when there is only one number, the current player takes it.
// 3. Iterate over all possible lengths of subarrays from 2 to n, and for each subarray, calculate the maximum score difference by considering both choices: taking the leftmost or rightmost number and subtracting the score of the other player from the remaining subarray.
// 4. The final result is determined by checking if the maximum score difference for the entire array (dp[0][n-1]) is greater than or equal to 0, indicating that the first player can win or tie.

// Time Complexity: O(n^2), where n is the length of the input array nums, as we are filling a 2D dp array of size n x n.
// Space Complexity: O(n^2), for the dp array.