class Solution {
    public int minimumPushes(String word) {
        int[] freq = new int[26];

        for (char ch : word.toCharArray()){
            freq[ch - 'a']++;
        }

        Arrays.sort(freq);

        int ans = 0;
        int idx = 0;

        for (int i = 25; i >= 0; i--){
            if (freq[i] == 0)
                break;
            ans += freq[i] * ((idx/8) + 1);
            idx++;
        }
        return ans;
    }
}

// Approach
// 1. Create an integer array freq of size 26 to store the frequency of each character in the input string word. Each index of the array corresponds to a letter in the alphabet (0 for 'a', 1 for 'b', ..., 25 for 'z').
// 2. Iterate through each character in the input string word and update the frequency of that character in the freq array. The frequency of a character is incremented by 1 for each occurrence of that character in the word.
// 3. Sort the freq array in ascending order. This allows us to process the characters with the highest frequency first, which will minimize the total number of pushes required to type the word.
// 4. Initialize a variable ans to 0, which will keep track of the  total number of pushes required to type the word, and a variable idx to 0, which will keep track of the index of the current character being processed in the sorted freq array.
// 5. Iterate through the sorted freq array from the highest frequency (index 25) to the lowest frequency (index 0). For each character with a non-zero frequency, calculate the number of pushes required to type that character. The number of pushes for the character at index i is calculated as freq[i] * ((idx / 8) + 1), where idx is the index of the current character being processed. This is because the first 8 characters (indices 0 to 7) require 1 push, the next 8 characters (indices 8 to 15) require 2 pushes, and so on. Add the calculated number of pushes for the current character to the ans variable and increment idx by 1.
// 6. After processing all characters in the freq array, return the value of ans, which represents the total number of pushes required to type the entire word. 

// Time Complexity: O(n + 26log26), where n is the length of the input string word. We iterate through each character in the string once to calculate the frequency, and then we sort the freq array of size 26, which takes O(26log26) time.
// Space Complexity: O(1) as we are using a constant amount of extra space for the freq array of size 26.