class Solution {
    public int maxActiveSectionsAfterTrade(String s) {

        int ones = 0;
        int maxGain = 0;

        // Length of previous zero block
        int prevZero = Integer.MIN_VALUE;

        int i = 0;
        int n = s.length();

        while (i < n) {

            int j = i;

            while (j < n && s.charAt(j) == s.charAt(i)) {
                j++;
            }

            int len = j - i;

            if (s.charAt(i) == '1') {
                ones += len;
            } else {
                maxGain = Math.max(maxGain, prevZero + len);
                prevZero = len;
            }

            i = j;
        }

        return ones + maxGain;
    }
}

// Time Complexity: O(n)
// Space Complexity: O(1)

// Intuition
// We are allowed at most one trade.
// A trade consists of two mandatory steps:
// Convert one block of 1s surrounded by 0s into 0s.
// Then convert one block of 0s surrounded by 1s into 1s.
// Instead of thinking character by character, think in terms of continuous blocks (runs).