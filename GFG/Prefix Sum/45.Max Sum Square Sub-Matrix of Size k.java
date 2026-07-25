class Solution {
    public int maximumSum(int[][] mat, int k) {

        int n = mat.length;

        int[][] prefix = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                prefix[i][j] = mat[i - 1][j - 1]
                        + prefix[i - 1][j]
                        + prefix[i][j - 1]
                        - prefix[i - 1][j - 1];
            }
        }

        int ans = Integer.MIN_VALUE;

        for (int i = 0; i + k <= n; i++) {
            for (int j = 0; j + k <= n; j++) {

                int sum = prefix[i + k][j + k]
                        - prefix[i][j + k]
                        - prefix[i + k][j]
                        + prefix[i][j];

                ans = Math.max(ans, sum);
            }
        }

        return ans;
    }
}

// Time Complexity: O(n^2)
// Space Complexity: O(n^2)

//Algorithm: 
// 1. Create a prefix sum matrix of size (n+1) x (n+1) to store the cumulative sums of the input matrix.
// 2. Iterate through the input matrix and fill the prefix sum matrix using the formula:
// prefix[i][j] = mat[i - 1][j - 1] + prefix[i - 1][j] + prefix[i][j - 1] - prefix[i - 1][j - 1]
// 3. Initialize a variable 'ans' to store the maximum sum of the k x k sub-matrix found so far.
// 4. Iterate through all possible top-left corners of k x k sub-matrices in the input matrix.
// 5. For each top-left corner (i, j), calculate the sum of the k x k sub-matrix using the prefix sum matrix with the formula:
// sum = prefix[i + k][j + k]
//       - prefix[i][j + k]
//       - prefix[i + k][j]
//       + prefix[i][j]
// 6. Update 'ans' with the maximum of the current 'ans' and the calculated sum.
// 7. Return 'ans' as the final result, which represents the maximum sum of any k x k sub-matrix in the input matrix.