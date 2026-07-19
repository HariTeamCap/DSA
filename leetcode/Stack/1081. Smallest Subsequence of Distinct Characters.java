class Solution {

    public String smallestSubsequence(String s) {

        int[] last = new int[26];

        for (int i = 0; i < s.length(); i++) {
            last[s.charAt(i) - 'a'] = i;
        }

        boolean[] seen = new boolean[26];

        ArrayDeque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {

            char ch = s.charAt(i);

            if (seen[ch - 'a'])
                continue;

            while (!stack.isEmpty()
                    && stack.peekLast() > ch
                    && last[stack.peekLast() - 'a'] > i) {

                seen[stack.removeLast() - 'a'] = false;
            }

            stack.addLast(ch);
            seen[ch - 'a'] = true;
        }

        StringBuilder ans = new StringBuilder();

        while (!stack.isEmpty()) {
            ans.append(stack.removeFirst());
        }

        return ans.toString();
    }
}
// Algorithm:
// 1. Create an array `last` of size 26 to store the last occurrence index of each character in the string `s`.
// 2. Iterate through the string `s` and populate the `last` array with the last occurrence index of each character.
// 3. Create a boolean array `seen` of size 26 to keep track of which characters have been added to the stack.
// 4. Create a stack (using `ArrayDeque`) to build the smallest subsequence of distinct characters.
// 5. Iterate through the string `s` again:
   - For each character `ch`, check if it has already been seen (i.e., added to the stack). If it has, skip to the next character.
   - While the stack is not empty, and the top character of the stack is greater than `ch`, and the last occurrence of the top character is after the current index `i`, pop the top character from the stack and mark it as unseen in the `seen` array.
   - Add the current character `ch to the stack and mark it as seen in the `seen` array.
6. After processing all characters, build the result string by popping all characters from the stack and appending them to a `StringBuilder`.
7. Return the resulting string as the smallest subsequence of distinct characters.



// Time Complexity: O(n), where n is the length of the input string `s`.
// Space Complexity: O(1), as we are using a constant amount of extra space for the `last` and `seen` arrays, and the stack can hold at most 26 characters