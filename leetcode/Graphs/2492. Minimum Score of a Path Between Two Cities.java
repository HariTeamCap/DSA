class Solution {
    int ans = Integer.MAX_VALUE;
    public int minScore(int n, int[][] roads) {
        List<int[]>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++)
            graph[i] = new ArrayList<>();
        
        for (int[] r : roads){
            graph[r[0]].add(new int[]{r[1], r[2]});
            graph[r[1]].add(new int[]{r[0], r[2]});
        }
        boolean[] vis = new boolean[n + 1];
        dfs(1, graph, vis);

        return ans;
    }
    private void dfs(int u, List<int[]>[] graph, boolean[] vis) {
        vis[u] = true;

        for (int[] edge : graph[u]) {
            ans = Math.min(ans, edge[1]);

            if (!vis[edge[0]]) {
                dfs(edge[0], graph, vis);
            }
        }
    }
}

// Complexity
// Build graph: O(E)
// DFS/BFS: O(V + E)
// Time: O(n + roads.length)
// Space: O(n + roads.length)


// Approach (DFS/BFS)
// The key observation is:
// Since you can revisit roads and cities any number of times, the answer is simply the minimum edge weight in the connected component containing city 1.
// Why?
// There is guaranteed to be a path from 1 to n.
// You are allowed to revisit nodes and edges.
// Therefore, if there is any smaller edge anywhere in the connected component, you can always detour to that edge and then continue to n.
// Hence, the minimum possible score is just the smallest road distance in the connected component of city 1.

// Algorithm
// Build an adjacency list.
// Start DFS/BFS from node 1.
// Maintain
// visited[]
// answer = Integer.MAX_VALUE
// For every visited node:
// Traverse all its edges.
// Update
// answer = Math.min(answer, edgeWeight);
// Continue until the whole connected component is explored.
// Return answer.