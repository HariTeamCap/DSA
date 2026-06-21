class Solution {
    public String chooseSwap(String s) {

        int[] first = new int[26];
        Arrays.fill(first, -1);

        for (int i = 0; i < s.length(); i++) {
            if (first[s.charAt(i) - 'a'] == -1) {
                first[s.charAt(i) - 'a'] = i;
            }
        }

        char c1 = 0, c2 = 0;

        for (int i = 0; i < s.length(); i++) {

            int curr = s.charAt(i) - 'a';

            for (int ch = 0; ch < curr; ch++) {

                if (first[ch] > first[curr]) {

                    c1 = s.charAt(i);
                    c2 = (char) (ch + 'a');
                    break;
                }
            }

            if (c1 != 0) break;
        }

        if (c1 == 0) return s;

        char[] arr = s.toCharArray();

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == c1)
                arr[i] = c2;
            else if (arr[i] == c2)
                arr[i] = c1;
        }

        return new String(arr);
    }
}
//Time: O(n)
//Space: O(1)

// Correct Greedy Idea
// Store the first occurrence of every character.
// For each character s[i], try to find a smaller character ch such that:
// firstOccurrence[ch] > firstOccurrence[curr]
// This ensures swapping improves the earliest position where the larger character appears