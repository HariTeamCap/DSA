import java.util.*;

class Solution {
    public boolean findSafeWalk(List<List<Integer>> grid, int health) {

        int m = grid.size();
        int n = grid.get(0).size();

        int[][] dist = new int[m][n];

        for (int[] row : dist)
            Arrays.fill(row, Integer.MAX_VALUE);

        PriorityQueue<int[]> pq =
                new PriorityQueue<>((a, b) -> a[2] - b[2]);

        dist[0][0] = grid.get(0).get(0);
        pq.offer(new int[]{0, 0, dist[0][0]});

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!pq.isEmpty()) {

            int[] cur = pq.poll();

            int r = cur[0];
            int c = cur[1];
            int cost = cur[2];

            if (cost != dist[r][c]) continue;

            if (r == m - 1 && c == n - 1)
                break;

            for (int k = 0; k < 4; k++) {

                int nr = r + dr[k];
                int nc = c + dc[k];

                if (nr < 0 || nr >= m || nc < 0 || nc >= n)
                    continue;

                int newCost = cost + grid.get(nr).get(nc);

                if (newCost < dist[nr][nc]) {
                    dist[nr][nc] = newCost;
                    pq.offer(new int[]{nr, nc, newCost});
                }
            }
        }

        return health > dist[m - 1][n - 1];
    }
}

// This can be solved using Dijkstra's algorithm.
// Treat each cell as a node and the cost of entering a cell is:
// 0 if grid[i][j] == 0
// 1 if grid[i][j] == 1
// We want the minimum health lost from (0,0) to (m-1,n-1). If the minimum health loss is less than health, then we can finish with at least 1 health.
// Time: O(mn log(mn))
// Space: O(mn)

// The key observation is that after reaching the destination:
// Health remaining = health - minimumHealthLost
// We need health remaining ≥ 1, which is equivalent to: