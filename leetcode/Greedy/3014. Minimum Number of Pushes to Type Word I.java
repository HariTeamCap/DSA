class Solution {
    public int minimumPushes(String word) {

        int ans = 0;

        for (int i = 0; i < word.length(); i++) {
            ans += (i / 8) + 1;
        }

        return ans;
    }
}

// Approach
// 1. Initialize a variable ans to 0, which will keep track of the total number of pushes required to type the word.
// 2. Iterate through each character in the input string word using a for loop.
// 3. For each character at index i, calculate the number of pushes required to type that character. Since there are 8 characters per row on the keyboard, the number of pushes required for the character at index i is (i / 8) + 1. This is because the first 8 characters (indices 0 to 7) require 1 push, the next 8 characters (indices 8 to 15) require 2 pushes, and so on.
// 4. Add the calculated number of pushes for the current character to the ans variable.
// 5. After processing all characters in the word, return the value of ans, which represents the total number of pushes required to type the entire word.   

// Time Complexity: O(n), where n is the length of the input string word. We iterate through each character in the string once.
// Space Complexity: O(1) as we are using a constant amount of extra space.