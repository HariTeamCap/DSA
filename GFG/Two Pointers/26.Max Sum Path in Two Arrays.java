class Solution {
    public int maxPathSum(int[] a, int[] b) {

        int i = 0, j = 0;
        long sum1 = 0;
        long sum2 = 0;
        long ans = 0;

        while (i < a.length && j < b.length) {

            if (a[i] < b[j]) {
                sum1 += a[i++];
            } else if (a[i] > b[j]) {
                sum2 += b[j++];
            } else {
                ans += Math.max(sum1, sum2) + a[i];
                sum1 = 0;
                sum2 = 0;
                i++;
                j++;
            }
        }

        while (i < a.length) {
            sum1 += a[i++];
        }

        while (j < b.length) {
            sum2 += b[j++];
        }

        ans += Math.max(sum1, sum2);

        return (int) ans;
    }
}

//Two pointer approach
// Idea
// Traverse both sorted arrays simultaneously.
// Maintain:
// sum1 = sum collected in array a
// sum2 = sum collected in array b
// Whenever a common element is found:
// Add max(sum1, sum2) + commonElement to the answer.
// Reset both sums to 0.
// After traversal, add the larger of the remaining sums.

// Complexity
// Time: O(n + m)
// Space: O(1)