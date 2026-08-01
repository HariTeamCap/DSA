class Solution {
    public int findMax(int n, int[] a, int[] b, int[] k) {

        long[] diff = new long[n + 1];

        for (int i = 0; i < a.length; i++) {
            diff[a[i]] += k[i];

            if (b[i] + 1 < n)
                diff[b[i] + 1] -= k[i];
        }

        long max = 0;
        long curr = 0;

        for (int i = 0; i < n; i++) {
            curr += diff[i];
            if (curr > max)
                max = curr;
        }

        return (int) max;
    }
}

//Algorithm: Explanation:
// 1. Create a difference array of size n+1 initialized to 0.
// 2. For each range increment operation defined by arrays a, b, and k,update the difference array by adding k[i] at index a[i] and subtracting k[i] at index b[i] + 1 (if b[i] + 1 < n).
// 3. Iterate through the difference array to compute the prefix sum, which gives the final values of the original array after all range increments.
// 4. Keep track of the maximum value encountered during the prefix sum computation and return it as the result.            

// Time Complexity: O(n + m), where n is the size of the array and m is the number of range increment operations.
// Space Complexity: O(n), for the difference array.
