class Solution {
    public int maxIndexDifference(String s) {
        int n = s.length();
        int[] maxF = new int[26];
        Arrays.fill(maxF, -1);

        int ans = -1;
        boolean foundA = false;

        for (int i = n - 1; i >= 0; i--) {
            int idx = s.charAt(i) - 'a';
            int f;

            if (idx == 25) { // 'z' has no next letter
                f = i;
            } else if (maxF[idx + 1] != -1) {
                f = maxF[idx + 1];
            } else {
                f = i;
            }

            if (idx == 0) { // it's an 'a' — a valid starting point
                foundA = true;
                ans = Math.max(ans, f - i);
            }

            if (f > maxF[idx]) {
                maxF[idx] = f;
            }
        }

        return foundA ? ans : -1;
    }
}

//Time Complexity: O(n) where n is the length of the string s. We traverse the string once.
//Space Complexity: O(1) since the size of the maxF array is constant (26 for lowercase letters).

//Algorithm Explanation:
//1. We initialize an array maxF of size 26 to keep track of the maximum index for each character from 'a' to 'z'. We fill it with -1 to indicate that we haven't seen any characters yet.
//2. We iterate through the string from the end to the beginning. For each character, we determine the maximum index f that can be reached by moving to the next character in the alphabet.
//3. If the current character is 'a', we check if we have found a valid maximum index f for the next character. If so, we calculate the difference f - i and update the answer if it's greater than the current maximum difference.
//4. We update the maxF array with the maximum index for the current character if f is greater than the current value in maxF.
//5. Finally, we return the maximum difference found if we have encountered at least one 'a' in the string; otherwise, we return -1 to indicate that no valid starting point was found.