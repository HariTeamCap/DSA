class Solution {

    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};

    public int countCoordinates(int[][] mat) {

        int n = mat.length;
        int m = mat[0].length;

        boolean[][] p = new boolean[n][m];
        boolean[][] q = new boolean[n][m];

        // Station P (Top row)
        for (int j = 0; j < m; j++) {
            dfs(0, j, p, mat);
        }

        // Station P (Left column)
        for (int i = 0; i < n; i++) {
            dfs(i, 0, p, mat);
        }

        // Station Q (Bottom row)
        for (int j = 0; j < m; j++) {
            dfs(n - 1, j, q, mat);
        }

        // Station Q (Right column)
        for (int i = 0; i < n; i++) {
            dfs(i, m - 1, q, mat);
        }

        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (p[i][j] && q[i][j]) {
                    count++;
                }
            }
        }

        return count;
    }

    private void dfs(int r, int c, boolean[][] vis, int[][] mat) {

        int n = mat.length;
        int m = mat[0].length;

        if (vis[r][c]) return;

        vis[r][c] = true;

        for (int k = 0; k < 4; k++) {

            int nr = r + dr[k];
            int nc = c + dc[k];

            if (nr >= 0 && nr < n && nc >= 0 && nc < m &&
                !vis[nr][nc] &&
                mat[nr][nc] >= mat[r][c]) {

                dfs(nr, nc, vis, mat);
            }
        }
    }
}

// Complexity
// Time: O(n × m)
// Space: O(n × m)


// Algorithm
// Create two visited arrays.
// Run DFS/BFS from top row and left column (Station P).
// Run DFS/BFS from bottom row and right column (Station Q).
// During DFS/BFS move only to neighbors with value greater than or equal to the current cell.
// Count cells visited in both arrays.
// Return the count.
// This is the optimal O(n × m) solution expected for the problem.