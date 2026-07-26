import java.util.*;

class Solution {
    public ArrayList<ArrayList<Integer>> levelSort(int[] arr) {

        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();

        int n = arr.length;
        int idx = 0;
        int levelSize = 1;

        while (idx < n) {

            ArrayList<Integer> level = new ArrayList<>();

            for (int i = 0; i < levelSize && idx < n; i++) {
                level.add(arr[idx++]);
            }

            Collections.sort(level);

            ans.add(level);

            levelSize *= 2;
        }

        return ans;
    }
}

// Time Complexity: O(n log n)
// Space Complexity: O(n)

// Algorithm:
// 1. Initialize an empty list 'ans' to store the sorted levels of the binary tree.
// 2. Get the length of the input array 'arr' and initialize 'idx' to 0 and 'levelSize' to 1, which represents the number of nodes at the current level of the binary tree.
// 3. Use a while loop to iterate through the array until 'idx' is less than 'n' (the length of the array).
// 4. Inside the loop, create a new list 'level' to store the nodes at the current level of the binary tree.
// 5. Use a for loop to iterate 'levelSize' times or until 'idx' is less than 'n', adding the elements from 'arr' to the 'level' list and incrementing 'idx'.
// 6. Sort the 'level' list using Collections.sort() to arrange the nodes in ascending order.
// 7. Add the sorted 'level' list to the 'ans' list.
// 8. Double the 'levelSize' for the next level of the binary tree (since each level has twice as many nodes as the previous level).
// 9. After the while loop completes, return the 'ans' list, which contains the sorted levels of the binary tree represented by the input array.    
x