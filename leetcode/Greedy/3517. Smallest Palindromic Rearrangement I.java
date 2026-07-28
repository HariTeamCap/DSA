class Solution {
    public String smallestPalindrome(String s) {

        int[] freq = new int[26];

        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        StringBuilder left = new StringBuilder();
        char middle = 0;

        for (int i = 0; i < 26; i++) {

            for (int j = 0; j < freq[i] / 2; j++) {
                left.append((char) ('a' + i));
            }

            if ((freq[i] & 1) == 1) {
                middle = (char) ('a' + i);
            }
        }

        StringBuilder ans = new StringBuilder();

        ans.append(left);

        if (middle != 0) {
            ans.append(middle);
        }

        ans.append(new StringBuilder(left).reverse());

        return ans.toString();
    }
}

// Approach
// Since s is already guaranteed to be a palindrome:
// Every character appears an even number of times except possibly one character (the middle character).
// To obtain the lexicographically smallest palindrome:
// Count the frequency of each character.
// Build the left half by taking freq[i] / 2 copies of each character from 'a' to 'z'.
// If a character has an odd frequency, it becomes the middle character.
// The right half is the reverse of the left half.
// This greedy construction ensures the smallest lexicographical order.

// Time Complexity: O(n) where n is the length of the string s. We traverse the string to count frequencies and then build the result.
// Space Complexity: O(1) since the frequency array has a fixed size of 26
