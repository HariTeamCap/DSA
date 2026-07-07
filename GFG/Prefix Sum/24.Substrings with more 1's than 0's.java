// Use Prefix Sum + Fenwick Tree (BIT).
// Idea
// Convert the string into values:
// '1' -> +1
// '0' -> -1
// Let pref[i] be the prefix sum till index i.
// For a substring (l...r):

// sum=pref[r]−pref[l−1]
// We need:
// sum>0
// which means
// pref[l−1]<pref[r]
// So for every prefix sum, we need to count how many previous prefix sums are smaller than the current prefix sum.
// Since prefix sums can be negative, compress them using an offset.

class Solution {
    public int countSubstring(String s) {
        int n = s.length();

        int offset = n + 2;
        int size = 2 * n + 5;

        long ans = 0;
        int[] bit = new int[size];

        int prefix = 0;

        update(bit, offset, 1);

        for (char ch : s.toCharArray()) {
            if (ch == '1')
                prefix++;
            else
                prefix--;

            ans += query(bit, prefix + offset - 1);
            update(bit, prefix + offset, 1);
        }

        return (int) ans;
    }

    private void update(int[] bit, int idx, int val) {
        while (idx < bit.length) {
            bit[idx] += val;
            idx += idx & -idx;
        }
    }

    private int query(int[] bit, int idx) {
        int sum = 0;
        while (idx > 0) {
            sum += bit[idx];
            idx -= idx & -idx;
        }
        return sum;
    }
}

// Complexity
// Time: O(n log n)
// Space: O(n)
// This is the expected optimal solution for |s| ≤ 6 × 10^4