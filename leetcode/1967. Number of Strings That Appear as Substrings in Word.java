class Solution {
    public int numOfStrings(String[] patterns, String word) {

        int count = 0;

        for (String pattern : patterns) {
            if (word.contains(pattern)) {
                count++;
            }
        }

        return count;
    }
}

// Approach
// For every string in patterns:
// Check whether it is a substring of word.
// If yes, increment the answer.
// Java already provides the method:
// word.contains(pattern)
// which checks if pattern is a substring of word.
// Algorithm
// Initialize count = 0.
// Traverse every string in patterns.
// If word.contains(pattern) is true, increment count.
// Return count.

// Time: O(n × m × k) in the worst case.
// Here, n ≤ 100, m ≤ 100, and k ≤ 100, so at most about 10 
// 6
//   character comparisons, which is easily acceptable.
// Space: O(1)