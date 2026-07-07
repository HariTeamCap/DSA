class Solution {

    public ArrayList<ArrayList<Integer>> shortestDist(int[][] mat) {

        int n = mat.length;
        int[][] path = new int[n][n];
        boolean[][] visited = new boolean[n][n];

        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();

        if (mat[0][0] == 0 || !solve(0, 0, mat, path, visited, n)) {
            ArrayList<Integer> row = new ArrayList<>();
            row.add(-1);
            ans.add(row);
            return ans;
        }

        for (int i = 0; i < n; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                row.add(path[i][j]);
            }
            ans.add(row);
        }

        return ans;
    }

    private boolean solve(int r, int c,
                          int[][] mat,
                          int[][] path,
                          boolean[][] visited,
                          int n) {

        if (r == n - 1 && c == n - 1) {
            path[r][c] = 1;
            return true;
        }

        if (r >= n || c >= n || mat[r][c] == 0 || visited[r][c]) {
            return false;
        }

        path[r][c] = 1;

        int jump = mat[r][c];

        for (int step = 1; step <= jump; step++) {

            // right first
            if (solve(r, c + step, mat, path, visited, n))
                return true;

            // then down
            if (solve(r + step, c, mat, path, visited, n))
                return true;
        }

        path[r][c] = 0;
        visited[r][c] = true; // no path possible from here

        return false;
    }
}
// Complexity
// Time: O(n² × maxJump) in practice, because each cell is processed once.
// Here n ≤ 50, maxJump ≤ 20.
// Roughly O(50² × 20) = 50,000 operations.
// Space: O(n²)
// path[][]
// visited[][]
// recursion stack
// This version passes all GFG test cases.