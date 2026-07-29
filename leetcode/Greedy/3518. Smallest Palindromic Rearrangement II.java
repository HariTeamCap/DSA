class Solution {
    public String smallestPalindrome(String s, int k) {
        int n = s.length();
        int[] freq = new int[26];
        for (char ch : s.toCharArray()) freq[ch - 'a']++;

        int mid = -1;
        int[] half = new int[26];
        int L = 0;
        for (int c = 0; c < 26; c++) {
            if (freq[c] % 2 == 1) mid = c;
            half[c] = freq[c] / 2;
            L += half[c];
        }

        long total = countArrangements(half, k);
        if (total < k) return "";

        StringBuilder firstHalf = new StringBuilder();
        long remainingK = k;

        for (int pos = 0; pos < L; pos++) {
            for (int c = 0; c < 26; c++) {
                if (half[c] == 0) continue;
                half[c]--;
                long cnt = countArrangements(half, remainingK);
                if (cnt >= remainingK) {
                    firstHalf.append((char) ('a' + c));
                    break;
                } else {
                    remainingK -= cnt;
                    half[c]++;
                }
            }
        }

        String h = firstHalf.toString();
        StringBuilder sb = new StringBuilder();
        sb.append(h);
        if (mid != -1) sb.append((char) ('a' + mid));
        sb.append(new StringBuilder(h).reverse());
        return sb.toString();
    }
    private long countArrangements(int[] counts, long cap) {
        long ways = 1;
        int usedSoFar = 0;
        for (int c = 0; c < 26; c++) {
            if (counts[c] == 0) continue;
            usedSoFar += counts[c];
            long comb = nCrCapped(usedSoFar, counts[c], cap);
            ways *= comb;
            if (ways >= cap) return cap;
        }
        return ways;
    }

    private long nCrCapped(int n, int r, long cap) {
        r = Math.min(r, n - r);
        if (r == 0) return 1;
        long result = 1;
        for (int t = 1; t <= r; t++) {
            result = result * (n - r + t) / t;
            if (result >= cap) return cap;
        }
        return result;
    }
}

// Approach
// 1. Count the frequency of each character in the string.
// 2. Determine the middle character (if any) and the half counts of each character.
// 3. Calculate the total number of palindromic arrangements using the countArrangements function
// 4. If the total arrangements are less than k, return an empty string.
// 5. Construct the first half of the palindrome by iterating through each position and selecting the appropriate character based on the remaining arrangements.
// 6. Append the middle character (if any) and the reverse of the first half to form the complete palindrome.
// 7. Return the constructed palindrome string. 

// Time Complexity: O(n^2) in the worst case due to the nested loops for constructing the palindrome and calculating combinations, where n is the length of the string.
// Space Complexity: O(1) as we are using a constant amount of extra space for frequency counts and other variables.