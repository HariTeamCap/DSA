class Solution {
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;

        int[] dp = new int[n + 1];

        for (int i = n - 1; i >= 0; i--) {
            dp[i] = Integer.MIN_VALUE;
            int sum = 0;

            for (int k = 0; k < 3 && i + k < n; k++) {
                sum += stoneValue[i + k];
                dp[i] = Math.max(dp[i], sum - dp[i + k + 1]);
            }
        }

        if (dp[0] > 0)
            return "Alice";
        if (dp[0] < 0)
            return "Bob";
        return "Tie";
    }
}

//Time Complexity: O(n) where n is the size of the stoneValue array.
//Space Complexity: O(n) where n is the size of the stoneValue array.   

//Algorithm: Explanation
//1. We use dynamic programming to solve the problem. We create an array dp where dp[i] represents the maximum score difference that the current player can achieve starting from index i.
//2. We iterate from the end of the stoneValue array to the beginning. For each index i, we calculate the maximum score difference by considering taking 1, 2, or 3 stones. We keep track of the sum of the stones taken and update dp[i] accordingly
//3. Finally, we check the value of dp[0]. If it is greater than 0, Alice wins. If it is less than 0, Bob wins. If it is equal to 0, it is a tie.   