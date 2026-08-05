import java.util.*;

class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {

        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++)
            graph[i] = new ArrayList<>();

        for (int[] e : invocations)
            graph[e[0]].add(e[1]);

        boolean[] suspicious = new boolean[n];

        dfs(k, graph, suspicious);

        // If any non-suspicious method invokes a suspicious one,
        // removal is impossible.
        for (int[] e : invocations) {
            int u = e[0], v = e[1];
            if (!suspicious[u] && suspicious[v]) {
                List<Integer> ans = new ArrayList<>();
                for (int i = 0; i < n; i++)
                    ans.add(i);
                return ans;
            }
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!suspicious[i])
                ans.add(i);
        }

        return ans;
    }

    private void dfs(int node, List<Integer>[] graph, boolean[] suspicious) {
        if (suspicious[node])
            return;

        suspicious[node] = true;

        for (int next : graph[node]) {
            dfs(next, graph, suspicious);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 5;
        int k = 2;
        int[][] invocations = {{0, 1}, {1, 2}, {2, 3}, {3, 4}};
        List<Integer> remainingMethods = solution.remainingMethods(n, k, invocations);
        System.out.println(remainingMethods); // Output: [0, 1]
    }
}

//Time Complexity: O(n + m) where n is the number of methods and m is the number of invocations (edges).
//Space Complexity: O(n + m) for the graph representation and the suspicious array.

//Approach:
//1. Build a directed graph from the invocations list where each method is a node and each invocation is a directed edge from one method to another.
//2. Use Depth-First Search (DFS) starting from the suspicious method k to mark all reachable methods as suspicious.
//3. After marking suspicious methods, check if any non-suspicious method invokes a suspicious one. If so, return a list of all methods since removal is impossible.
//4. If no such invocation exists, return a list of all non-suspicious methods that can be safely removed.

