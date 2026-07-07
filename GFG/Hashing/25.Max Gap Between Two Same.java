// Approach
// Store the first index of every character.
// Initialize all indices to -1.
// Traverse the string:
// If it's the first occurrence, store its index.
// Otherwise, compute the gap:
// currentIndex - firstIndex - 1
// and update the maximum.


class Solution {
    public int maxCharGap(String s) {
        int[] first = new int[26];
        Arrays.fill(first, -1);

        int ans = -1;

        for (int i = 0; i < s.length(); i++) {
            int idx = s.charAt(i) - 'a';

            if (first[idx] == -1) {
                first[idx] = i;
            } else {
                ans = Math.max(ans, i - first[idx] - 1);
            }
        }

        return ans;
    }
}

Complexity
Time: O(n)
Space: O(1)