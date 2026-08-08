class Solution {

    int[] parent;
    int[] rank;

    int find(int x) {
        if (parent[x] == x)
            return x;

        return parent[x] = find(parent[x]);
    }

    void union(int a, int b) {

        int pa = find(a);
        int pb = find(b);

        if (pa == pb)
            return;

        if (rank[pa] < rank[pb]) {
            parent[pa] = pb;
        } else if (rank[pa] > rank[pb]) {
            parent[pb] = pa;
        } else {
            parent[pb] = pa;
            rank[pa]++;
        }
    }

    int minEdgesReq(int n, int[][] edges) {

        if (edges.length < n - 1)
            return -1;

        parent = new int[n];
        rank = new int[n];

        for (int i = 0; i < n; i++)
            parent[i] = i;

        for (int[] edge : edges)
            union(edge[0], edge[1]);

        int components = 0;

        for (int i = 0; i < n; i++) {
            if (find(i) == i)
                components++;
        }

        return components - 1;
    }
}
public class MinEdgeMovementsToConnectAGraph {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 4; // Number of nodes
        int[][] edges = { { 0, 1 }, { 0, 2 } }; // Example edges
        int result = solution.minEdgesReq(n, edges);
        System.out.println("Minimum edges required to connect the graph: " + result);
    }
}

// Time Complexity: O(E * α(V)) - Where E is the number of edges and V is the number of vertices. The find and union operations take nearly constant time due to path compression and union by rank, which is represented by the inverse Ackermann function α(V).
// Space Complexity: O(V) - We are using two arrays of size V to store the parent and rank of each vertex, so the space complexity is linear with respect to the number of vertices

//Approach: Explanation:
//1. The problem is to find the minimum number of edges required to connect a graph given n nodes and a list of edges. If the number of edges is less than n-1, it is impossible to connect the graph, and we return -1.
//2. We can use the Disjoint Set Union (DSU) or Union-Find data structure to keep track of connected components in the graph. Each node starts as its own component, and we union nodes that are connected by edges.
//3. We initialize two arrays: parent and rank. The parent array keeps track of the representative of each component, and the rank array helps optimize the union operation by keeping the tree flat.
//4. We iterate through the edges and perform union operations for each pair of connected nodes. After processing all edges, we count the number of unique components by checking how many nodes are their own parent.
//5. The minimum number of edges required to connect the graph is equal to the number of components minus one, as we need to connect all components together.
