class Solution {

    public ArrayList<Integer> preOrder(Node root) {

        ArrayList<Integer> ans = new ArrayList<>();

        if (root == null)
            return ans;

        Stack<Node> stack = new Stack<>();

        stack.push(root);

        while (!stack.isEmpty()) {

            Node curr = stack.pop();

            ans.add(curr.data);

            if (curr.right != null)
                stack.push(curr.right);

            if (curr.left != null)
                stack.push(curr.left);
        }

        return ans;
    }
}

// Algorithm
// 1. Create an empty stack and push the root node onto it.
// 2. While the stack is not empty, do the following:
//    a. Pop the top node from the stack and add its value to the result list
//    b. Push the right child of the popped node onto the stack (if it exists)
//    c. Push the left child of the popped node onto the stack (if it exists)
// 3. Return the result list containing the preorder traversal of the tree.

// Complexity
// Time: O(n), where n is the number of nodes in the tree. Each node is visited once.
// Space: O(h), where h is the height of the tree. In the worst case, the stack can hold all the nodes in a single path from the root to a leaf 
