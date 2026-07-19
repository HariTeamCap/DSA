import java.util.*;

class Solution {
    public ArrayList<Boolean> processQueries(int[] arr, int[][] queries) {

        int n = arr.length;

        int[] inc = new int[n];
        int[] dec = new int[n];

        // inc[i] = farthest index reachable while non-decreasing
        inc[n - 1] = n - 1;
        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] <= arr[i + 1]) {
                inc[i] = inc[i + 1];
            } else {
                inc[i] = i;
            }
        }

        // dec[i] = farthest index reachable while non-increasing
        dec[n - 1] = n - 1;
        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] >= arr[i + 1]) {
                dec[i] = dec[i + 1];
            } else {
                dec[i] = i;
            }
        }

        ArrayList<Boolean> ans = new ArrayList<>();

        for (int[] q : queries) {

            int l = q[0];
            int r = q[1];

            int peak = inc[l];

            if (peak >= r) {
                ans.add(true);
            } else {
                ans.add(dec[peak] >= r);
            }
        }

        return ans;
    }
}
// Time Complexity: O(n + q), where n is the length of the array and q is the number of queries.
// Space Complexity: O(n), for the inc and dec arrays.

// Algorithm:
// 1. Initialize two arrays `inc` and `dec` of size `n` to store the farthest indices reachable while non-decreasing and non-increasing, respectively.
// 2. Fill the `inc` array by iterating from the second last element to the first, checking if the current element is less than or equal to the next element. If it is, set `inc[i]` to `inc[i + 1]`, otherwise set it to `i`.
// 3. Fill the `dec` array similarly, checking if the current element is greater than or equal to the next element.
// 4. For each query, retrieve the left and right indices `l` and `r`.
// 5. Find the peak index using `inc[l]`. If the peak index is greater than or equal to `r`, add `true` to the answer list. Otherwise, check if `dec[peak]` is greater than or equal to `r` and add the result to the answer list.
// 6. Return the answer list containing boolean values for each query. 