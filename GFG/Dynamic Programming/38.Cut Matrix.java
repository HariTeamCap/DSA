import java.util.Arrays;

class Solution {
    static final int MOD = 1000000007;
    int n, m;
    int[][] suffix;

    public int findWays(int[][] matrix, int k) {
        n = matrix.length;
        m = matrix[0].length;

        suffix = new int[n + 1][m + 1];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                suffix[i][j] = matrix[i][j]
                        + suffix[i + 1][j]
                        + suffix[i][j + 1]
                        - suffix[i + 1][j + 1];
            }
        }

        // dp for pieces = 1
        int[][] dpPrev = new int[n][m];
        for (int r = 0; r < n; r++)
            for (int c = 0; c < m; c++)
                dpPrev[r][c] = suffix[r][c] > 0 ? 1 : 0;

        if (k == 1) return dpPrev[0][0];

        for (int p = 2; p <= k; p++) {
            int[][] dpCur = new int[n][m];

            // rowSuf[c][i] = sum of dpPrev[i'][c] for i' = i..n-1
            int[][] rowSuf = new int[m][n + 1];
            for (int c = 0; c < m; c++) {
                for (int i = n - 1; i >= 0; i--) {
                    rowSuf[c][i] = (int) ((rowSuf[c][i + 1] + dpPrev[i][c]) % MOD);
                }
            }

            // colSuf[r][j] = sum of dpPrev[r][j'] for j' = j..m-1
            int[][] colSuf = new int[n][m + 1];
            for (int r = 0; r < n; r++) {
                for (int j = m - 1; j >= 0; j--) {
                    colSuf[r][j] = (int) ((colSuf[r][j + 1] + dpPrev[r][j]) % MOD);
                }
            }

            for (int r = 0; r < n; r++) {
                for (int c = 0; c < m; c++) {
                    if (suffix[r][c] == 0) {
                        dpCur[r][c] = 0;
                        continue;
                    }

                    long ways = 0;
                    int target = suffix[r][c];

                    // first row index in (r, n] where suffix[idx][c] < target
                    int lo = r + 1, hi = n;
                    while (lo < hi) {
                        int mid = (lo + hi) >>> 1;
                        if (suffix[mid][c] < target) hi = mid; else lo = mid + 1;
                    }
                    ways += rowSuf[c][lo];

                    // first col index in (c, m] where suffix[r][idx] < target
                    int lo2 = c + 1, hi2 = m;
                    while (lo2 < hi2) {
                        int mid = (lo2 + hi2) >>> 1;
                        if (suffix[r][mid] < target) hi2 = mid; else lo2 = mid + 1;
                    }
                    ways += colSuf[r][lo2];

                    dpCur[r][c] = (int) (ways % MOD);
                }
            }

            dpPrev = dpCur;
        }

        return dpPrev[0][0];
    }
}

// Algorithm:
// 1. Initialize the dimensions of the matrix `n` and `m`, and create a suffix sum array `suffix` to store the sum of elements from each cell to the bottom-right corner of the matrix.
// 2. Fill the `suffix` array by iterating from the bottom-right corner to the top-left corner, calculating the sum of elements for each cell.
// 3. Create a dynamic programming array `dpPrev` to store the number of ways to cut the matrix into pieces for `k = 1`. If the suffix sum at a cell is greater than 0, set `dpPrev[r][c]` to 1; otherwise, set it to 0.
// 4. If `k` is equal to 1, return the value at `dpPrev[0][0]` as the result.
// 5. For each piece count `p` from 2 to `k`, create a new dynamic programming array `dpCur` to store the number of ways to cut the matrix into pieces for the current piece count.
// 6. Create two suffix sum arrays `rowSuf` and `colSuf` to store the cumulative sums of `dpPrev` for each row and column, respectively.
// 7. For each cell `(r, c)` in the matrix:
//    - If the suffix sum at that cell is 0, set `dpCur[r][c]` to 0 and continue to the next cell.
//    - Otherwise, calculate the number of ways to cut the matrix by finding the first row and column indices where the suffix sum is less than the target value (the suffix sum at `(r, c)`). Use binary search to find these indices and add the corresponding values from `rowSuf` and `colSuf` to the total number of ways.
//    - Store the total number of ways in `dpCur[r][c]`.
// 8. After processing all cells for the current piece count, update `dpPrev`  to `dpCur` for the next iteration.
// 9. After processing all piece counts up to `k`, return the value at `dpPrev [0][0]` as the final result.        

// Time Complexity: O(k * n * m * log(n + m)), where n is the number of rows, m is the number of columns, and k is the number of pieces. The outer loop runs k times, and for each piece count, we iterate through all cells in the matrix (n * m) and perform binary searches (log(n + m)) to find the first row and column indices.
// Space Complexity: O(n * m), as we are using additional space for the `suffix`, `dpPrev`, `dpCur`, `rowSuf`, and `colSuf` arrays