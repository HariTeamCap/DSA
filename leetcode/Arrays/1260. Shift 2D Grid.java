import java.util.*;

class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {

        int m = grid.length;
        int n = grid[0].length;
        int total = m * n;

        k %= total;

        int[][] ans = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                int oldIndex = i * n + j;
                int newIndex = (oldIndex + k) % total;

                int newRow = newIndex / n;
                int newCol = newIndex % n;

                ans[newRow][newCol] = grid[i][j];
            }
        }

        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                row.add(ans[i][j]);
            }
            result.add(row);
        }

        return result;
    }
}

// Time Complexity: O(m * n) where m is the number of rows and n is the number of columns in the grid. We iterate through each element of the grid once to compute the new positions and fill the answer grid.  
// Space Complexity: O(m * n) for the answer grid and the result list. We create a new grid to store the shifted values and a list of lists to return the final result.

//Algorithm Explanation:
// 1. Calculate the total number of elements in the grid (m * n).
// 2. Use modulo operation to handle cases where k is larger than the total number of elements, effectively reducing unnecessary full rotations.
// 3. Create a new grid (ans) to store the shifted values.
// 4. Iterate through each element in the original grid, calculate its new position based on the shift value k, and place it in the new grid.
// 5. Convert the new grid into a list of lists format to match the expected return type and return it. 
