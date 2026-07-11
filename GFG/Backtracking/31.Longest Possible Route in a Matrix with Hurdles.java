// Approach
// Since a cell can be visited only once, we need to try every possible path.
// At each cell:
// If destination is reached, return 0.
// Mark current cell as visited.
// Explore all 4 directions.
// Take the maximum path length among all valid moves.
// Backtrack (unmark the current cell).
// If no path exists, return -1.
// This is a classic Backtracking + DFS problem.

class Solution {

    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};

    public int longestPath(int[][] mat, int xs, int ys, int xd, int yd) {

        int n = mat.length;
        int m = mat[0].length;

        if (mat[xs][ys] == 0 || mat[xd][yd] == 0)
            return -1;

        boolean[][] vis = new boolean[n][m];

        return dfs(mat, vis, xs, ys, xd, yd);
    }

    private int dfs(int[][] mat, boolean[][] vis,
                    int x, int y, int xd, int yd) {

        if (x == xd && y == yd)
            return 0;

        vis[x][y] = true;

        int ans = -1;

        for (int k = 0; k < 4; k++) {

            int nx = x + dx[k];
            int ny = y + dy[k];

            if (isSafe(mat, vis, nx, ny)) {

                int res = dfs(mat, vis, nx, ny, xd, yd);

                if (res != -1)
                    ans = Math.max(ans, res + 1);
            }
        }

        vis[x][y] = false;

        return ans;
    }

    private boolean isSafe(int[][] mat, boolean[][] vis, int x, int y) {

        int n = mat.length;
        int m = mat[0].length;

        return x >= 0 && x < n &&
               y >= 0 && y < m &&
               mat[x][y] == 1 &&
               !vis[x][y];
    }
}

Time Complexity:O(4^(n*m))
Space Complexity:O(n*m) for the visited array and recursion stack.