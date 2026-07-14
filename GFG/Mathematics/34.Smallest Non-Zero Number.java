// Approach
// During processing, the value changes as:
// if (x > arr[i])
//     x = x + (x - arr[i]);
// else
//     x = x - (arr[i] - x);
// Both cases simplify to:
// x = 2*x - arr[i]
// Instead of finding the answer forward, work backwards.
// Suppose after processing arr[i] we need at least need.
// Then before processing arr[i],
// 2*x - arr[i] >= need
// So,
// x >= (need + arr[i]) / 2
// Since x is an integer,
// x = ceil((need + arr[i]) / 2)
// Iterate from the last element to the first.


class Solution {
    int find(int[] arr) {

        long need = 0;

        for (int i = arr.length - 1; i >= 0; i--) {
            need = (need + arr[i] + 1) / 2; // ceil((need + arr[i]) / 2)
        }

        return (int) need;
    }
}

// Time Complexity: O(n)
// Space Complexity: O(1)