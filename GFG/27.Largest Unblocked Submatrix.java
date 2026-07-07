// Approach
// The key observation is:
// A blocked cell (r, c) blocks the entire row r and entire column c.
// Therefore, the largest valid rectangle can only lie between two blocked rows and between two blocked columns.
// We only need the largest consecutive unblocked rows and the largest consecutive unblocked columns.
// The answer is:
// maxUnblockedRows×maxUnblockedCols

// Algorithm
// Collect blocked rows.
// Collect blocked columns.
// Add boundaries 0 and n+1 (rows).
// Add boundaries 0 and m+1 (columns).
// Sort both arrays.
// Compute the maximum gap between consecutive blocked rows.
// Compute the maximum gap between consecutive blocked columns.
// Return maxRowGap * maxColGap



class Solution {
    public int largestArea(int n, int m, int[][] arr) {
        // code here
        List<Integer> rows = new ArrayList<>();
        List<Integer> cols = new ArrayList<>();
        
        rows.add(0);
        rows.add(n + 1);
        
        cols.add(0);
        cols.add(m + 1);
        
        for (int[] cell : arr){
            rows.add(cell[0]);
            cols.add(cell[1]);
        }
        
        Collections.sort(rows);
        Collections.sort(cols);
        
        int maxRowGap = 0;
        for (int i = 1; i < rows.size(); i++){
            maxRowGap = Math.max(maxRowGap, rows.get(i) - rows.get(i - 1) - 1);
        }
        
        int maxColGap = 0;
        for (int i = 1; i < cols.size(); i++){
            maxColGap = Math.max(maxColGap, cols.get(i) - cols.get(i - 1) - 1);
        }
        return maxRowGap * maxColGap;
    }
}

// Time Complexity: O(k log k)
// Space Complexity
// O(k)