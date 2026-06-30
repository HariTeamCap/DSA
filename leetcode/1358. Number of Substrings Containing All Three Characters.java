class Solution {
    public int numberOfSubstrings(String s) {

        int[] count = new int[3];
        int left = 0;
        int ans = 0;
        int n = s.length();

        for (int right = 0; right < n; right++) {

            count[s.charAt(right) - 'a']++;

            while (count[0] > 0 && count[1] > 0 && count[2] > 0) {

                ans += n - right;

                count[s.charAt(left) - 'a']--;
                left++;
            }
        }

        return ans;
    }
}

// Complexity
// Time: O(n) (each pointer moves at most n times)
// Space: O(1) (only an array of size 3)

// Approach
// Maintain a window [left...right] that contains characters from the string.
// Keep the count of:
// ->a
// ->b
// ->c
// Whenever the current window contains at least one of each character:
// Every substring starting from left and ending at right, right+1, ..., n-1 is also valid.
// Therefore, add   n - right
// to the answer.
// Then shrink the window from the left until it becomes invalid.