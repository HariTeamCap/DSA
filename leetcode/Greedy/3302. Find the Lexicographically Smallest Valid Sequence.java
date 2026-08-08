class Solution {
    public int[] validSequence(String word1, String word2) {

        int n = word1.length();
        int m = word2.length();

        // dp[i] = maximum number of characters from the
        // suffix of word2 that can be matched in word1[i...]
        int[] dp = new int[n + 1];

        int j = m - 1;

        for (int i = n - 1; i >= 0; i--) {

            dp[i] = dp[i + 1];

            if (j >= 0 && word1.charAt(i) == word2.charAt(j)) {
                dp[i]++;
                j--;
            }
        }

        int[] ans = new int[m];

        int i = 0;
        j = 0;

        while (i < n && j < m) {

            // If characters match, always take this index.
            if (word1.charAt(i) == word2.charAt(j)) {

                ans[j] = i;
                j++;
                i++;
            }

            // Characters don't match.
            else {

                // Use our one allowed modification.
                //
                // After choosing i, we need to match
                // the remaining characters exactly.
                if (dp[i + 1] >= m - j - 1) {

                    ans[j] = i;
                    j++;
                    i++;

                    // Modification has been used.
                    break;
                }

                i++;
            }
        }

        // Match the remaining characters exactly.
        while (i < n && j < m) {

            if (word1.charAt(i) == word2.charAt(j)) {
                ans[j] = i;
                j++;
            }

            i++;
        }

        if (j != m) {
            return new int[0];
        }

        return ans;
    }
}

public class FindTheLexicographicallySmallestValidSequence {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String word1 = "abcde";
        String word2 = "ace";
        int[] result = solution.validSequence(word1, word2);

        if (result.length == 0) {
            System.out.println("No valid sequence found.");
        } else {
            System.out.print("Valid sequence indices: ");
            for (int index : result) {
                System.out.print(index + " ");
            }
        }
    }
}

//Time Complexity: O(n + m), where n is the length of word1 and m is the length of word2. We traverse both strings once.
//Space Complexity: O(n), where n is the length of word1. We use an additional array dp of size n + 1 to store the maximum number of characters from the suffix of word2 that can be matched in word1[i...].

//Algorithm Explanation:
//1. We initialize a dp array of size n + 1, where dp[i] represents the maximum number of characters from the suffix of word2 that can be matched in word1[i...]. We fill this array by iterating through word1 from the end to the beginning, checking if the characters match with word2 and updating the dp array accordingly.
//2. We then iterate through word1 and word2 simultaneously, trying to find the lexicographically smallest valid sequence of indices.
//3. If the characters match, we add the index to the answer array and move both pointers forward. If they don't match, we check if we can use our one allowed modification to skip a character in word1 and still match the remaining characters in word2. If we can, we add the index to the answer array and break out of the loop, as we can only use one modification.
//4. After using the modification, we continue to match the remaining characters in word2 exactly. If we reach the end of word1 or word2 and haven't matched all characters in word2, we return an empty array indicating that no valid sequence was found. Otherwise, we return the answer array containing the indices of the valid sequence. 
