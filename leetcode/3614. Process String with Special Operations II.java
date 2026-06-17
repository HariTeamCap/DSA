class Solution {
    public char processStr(String s, long k) {

        long LIMIT = (long) 1e15 + 1;
        int n = s.length();

        long[] len = new long[n + 1];

        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);

            if (ch >= 'a' && ch <= 'z') {
                len[i + 1] = Math.min(LIMIT, len[i] + 1);
            }
            else if (ch == '*') {
                len[i + 1] = Math.max(0, len[i] - 1);
            }
            else if (ch == '#') {
                len[i + 1] = Math.min(LIMIT, len[i] * 2);
            }
            else { // '%'
                len[i + 1] = len[i];
            }
        }

        if (k >= len[n]) {
            return '.';
        }

        for (int i = n - 1; i >= 0; i--) {

            char ch = s.charAt(i);
            long prevLen = len[i];
            long curLen = len[i + 1];

            if (ch >= 'a' && ch <= 'z') {

                if (k == prevLen) {
                    return ch;
                }
                // otherwise k stays the same

            } 
            else if (ch == '*') {

                // deletion of last character
                // remaining indices are unchanged
                continue;

            } 
            else if (ch == '#') {

                // result = old + old
                if (k >= prevLen) {
                    k -= prevLen;
                }

            } 
            else { // '%'

                // result = reverse(old)
                k = prevLen - 1 - k;
            }
        }

        return '.';
    }
}

//Time:O(n)