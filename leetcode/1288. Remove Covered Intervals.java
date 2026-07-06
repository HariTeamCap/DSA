import java.util.*;

class Solution {
    public int removeCoveredIntervals(int[][] intervals) {

        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0])
                return b[1] - a[1];
            return a[0] - b[0];
        });

        int count = 0;
        int maxEnd = 0;

        for (int[] interval : intervals) {
            if (interval[1] > maxEnd) {
                count++;
                maxEnd = interval[1];
            }
        }

        return count;
    }
}

// Approach
// Sort intervals by:
// Start in ascending order
// If starts are equal, end in descending order
// Example:
// [1,4], [1,3], [2,8], [3,6]

// After sorting:
// [1,4], [1,3], [2,8], [3,6]
// Why descending end?
// If two intervals have the same start, the larger interval should come first.
// Otherwise, the smaller interval might incorrectly appear as not covered.

// Algorithm
// Initialize
// count = 0
// maxEnd = 0
// Traverse the sorted intervals.
// If current interval's end is greater than maxEnd
// It is not covered
// Increment answer
// Update maxEnd
// Otherwise
// Current interval is covered by a previous interval.


// Complexity
// Sorting: O(n log n)
// Traversal: O(n)
// Overall: O(n log n)
// Space: O(1) (excluding the sorting implementation's internal stack).