class Solution {

    int ans = 0;

    public int longestConsecutive(Node root) {

        if (root == null)
            return -1;

        dfs(root, 1);

        return ans == 1 ? -1 : ans;
    }

    private void dfs(Node node, int len) {

        if (node == null)
            return;

        ans = Math.max(ans, len);

        if (node.left != null) {

            if (node.left.data == node.data + 1)
                dfs(node.left, len + 1);
            else
                dfs(node.left, 1);
        }

        if (node.right != null) {

            if (node.right.data == node.data + 1)
                dfs(node.right, len + 1);
            else
                dfs(node.right, 1);
        }
    }
}

//Algorithm: Explanation
//1. We will use a depth-first search (DFS) approach to traverse the binary tree
//2. We will maintain a variable 'len' to keep track of the current length of the consecutive path
//3. We will also maintain a variable 'ans' to keep track of the maximum length of the consecutive path found so far
//4. For each node, we will check its left and right children to see if they are consecutive (i.e., if the child's value is equal to the parent's value + 1)
//5. If they are consecutive, we will increment the length and continue the DFS on that child
//6. If they are not consecutive, we will reset the length to 1 and continue the DFS on that child
//7. Finally, we will return the maximum length found, or -1 if no consecutive path exists

//Time Complexity: O(n), where n is the number of nodes in the binary tree. We visit each node once.
//Space Complexity: O(h), where h is the height of the binary tree. This space is used by the recursion stack during the DFS traversal. 