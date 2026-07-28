import java.util.*;

class Solution {

    static class Pair {
        int node, dist;

        Pair(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }
    }

    public int shortestPath(int V, int src, int dest, int[][] edges) {

        ArrayList<Pair>[] graph = new ArrayList[V];

        for (int i = 0; i < V; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            graph[u].add(new Pair(v, w));
            graph[v].add(new Pair(u, w));
        }

        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<Pair> pq =
                new PriorityQueue<>((a, b) -> a.dist - b.dist);

        dist[src] = 0;
        pq.offer(new Pair(src, 0));

        while (!pq.isEmpty()) {

            Pair curr = pq.poll();

            if (curr.dist > dist[curr.node])
                continue;

            for (Pair next : graph[curr.node]) {

                if (dist[curr.node] + next.dist < dist[next.node]) {

                    dist[next.node] = dist[curr.node] + next.dist;

                    pq.offer(new Pair(next.node, dist[next.node]));
                }
            }
        }

        return dist[dest] == Integer.MAX_VALUE ? -1 : dist[dest];
    }
}

// Approach
// Build an adjacency list.
// Maintain a dist[] array initialized to ∞.
// Set dist[src] = 0.
// Use a PriorityQueue to always process the vertex with the minimum distance.
// Relax all adjacent edges.
// If dest is unreachable, return -1.

// Time Complexity: O(E log V) where E is the number of edges and V is the number of vertices. Each edge is processed once, and each vertex is added to the priority queue at most once.    
// Space Complexity: O(V + E) for the adjacency list and O(V) for the dist[] array and priority queue.