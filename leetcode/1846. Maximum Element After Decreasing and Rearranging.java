// Approach (Greedy + Sorting)
// Since we can rearrange the array in any order and decrease any element, the optimal strategy is:
// Sort the array.
// Make the first element 1.
// For every next element:
// It cannot be more than previous + 1.
// If it is larger, decrease it to previous + 1.
// Otherwise, keep it as it is.
// This greedy approach always maximizes the final largest element.

import java.util.Arrays;

class Solution {
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {

        Arrays.sort(arr);

        arr[0] = 1;

        for (int i = 1; i < arr.length; i++) {
            arr[i] = Math.min(arr[i], arr[i - 1] + 1);
        }

        return arr[arr.length - 1];
    }
}

// Time Complexity
// Sorting: O(n log n)
// Traversal: O(n)
// Overall: O(n log n)
// Space Complexity
// O(1) (ignoring the sorting algorithm's internal space)
// O(log n) if considering Java's sorting recursion stack.