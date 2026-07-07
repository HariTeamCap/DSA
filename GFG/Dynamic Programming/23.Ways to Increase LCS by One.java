class Solution {

    public int waysToIncreaseLCSBy1(String s1, String s2) {

        int n = s1.length();
        int m = s2.length();

        int[][] pre = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    pre[i][j] = pre[i - 1][j - 1] + 1;
                else
                    pre[i][j] = Math.max(pre[i - 1][j], pre[i][j - 1]);
            }
        }

        int[][] suf = new int[n + 1][m + 1];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                if (s1.charAt(i) == s2.charAt(j))
                    suf[i][j] = suf[i + 1][j + 1] + 1;
                else
                    suf[i][j] = Math.max(suf[i + 1][j], suf[i][j + 1]);
            }
        }

        int lcs = pre[n][m];
        int ans = 0;

        for (int pos = 0; pos <= n; pos++) {

            boolean[] used = new boolean[26];

            for (int j = 0; j < m; j++) {

                int c = s2.charAt(j) - 'a';

                if (used[c])
                    continue;

                if (pre[pos][j] + 1 + suf[pos][j + 1] == lcs + 1) {
                    ans++;
                    used[c] = true;
                }
            }
        }

        return ans;
    }
}

// Idea
// Let:
// L[i][j] = LCS of s1[0..i-1] and s2[0..j-1]
// R[i][j] = LCS of s1[i..] and s2[j..]
// For every insertion position in s1 and every character 'a' to 'z':
// Find every occurrence of that character in s2.
// If
// L[pos][idx] + 1 + R[pos][idx+1] == originalLCS + 1
// then inserting that character at pos increases the LCS by exactly one.
// To avoid counting the same character twice at the same position, use a boolean array.


// Complexity
// Time: O(n × m) (n, m ≤ 100)
// Space: O(n × m)
