import java.util.*;

class Solution {

    int preIndex = 0;
    HashMap<Integer, Integer> map = new HashMap<>();

    public Node constructBinaryTree(int[] pre, int[] preMirror) {

        for (int i = 0; i < preMirror.length; i++) {
            map.put(preMirror[i], i);
        }

        return build(pre, preMirror, 0, preMirror.length - 1);
    }

    private Node build(int[] pre, int[] preMirror, int l, int r) {

        if (preIndex >= pre.length || l > r)
            return null;

        Node root = new Node(pre[preIndex++]);

        if (l == r || preIndex >= pre.length)
            return root;

        int idx = map.get(pre[preIndex]);

        root.left = build(pre, preMirror, idx, r);
        root.right = build(pre, preMirror, l + 1, idx - 1);

        return root;
    }
}

//Time Complexity: O(n) where n is the number of nodes in the tree. We are traversing each node once.
//Space Complexity: O(n) where n is the number of nodes in the tree. We are using a HashMap to store the indices of the nodes in the preMirror array.   

//Algorithm:
//1. Create a HashMap to store the indices of the nodes in the preMirror array
//2. Create a recursive function build() that takes the pre and preMirror arrays, and the left and right indices of the current subtree as parameters
//3. In the build() function, check if the preIndex is greater than or equal to the length of the pre array or if the left index is greater than the right index. If so, return null
//4. Create a new node with the value of pre[preIndex] and increment preIndex
//5. If the left index is equal to the right index or if preIndex is greater than or equal to the length of the pre array, return the root node
//6. Get the index of the next node in the preMirror array from the HashMap
//7. Recursively call the build() function to construct the left and right subtrees using the appropriate indices
//8. Return the root node of the constructed subtree    