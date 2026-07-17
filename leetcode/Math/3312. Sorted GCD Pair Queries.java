import java.util.*;

class Solution {

    public int[] gcdValues(int[] nums, long[] queries) {

        int max = 0;
        for (int x : nums)
            max = Math.max(max, x);

        int[] freq = new int[max + 1];

        for (int x : nums)
            freq[x]++;

        long[] divisible = new long[max + 1];

        // Count numbers divisible by every d
        for (int d = 1; d <= max; d++) {
            for (int m = d; m <= max; m += d) {
                divisible[d] += freq[m];
            }
        }

        long[] exact = new long[max + 1];

        // Inclusion-Exclusion
        for (int d = max; d >= 1; d--) {

            long c = divisible[d];
            exact[d] = c * (c - 1) / 2;

            for (int m = d * 2; m <= max; m += d) {
                exact[d] -= exact[m];
            }
        }

        long[] prefix = new long[max + 1];

        for (int i = 1; i <= max; i++) {
            prefix[i] = prefix[i - 1] + exact[i];
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {

            long target = queries[i] + 1;

            int l = 1;
            int r = max;

            while (l < r) {

                int mid = (l + r) / 2;

                if (prefix[mid] >= target)
                    r = mid;
                else
                    l = mid + 1;
            }

            ans[i] = l;
        }

        return ans;
    }
}

// Approach
// Generating all pairs takes O(n²), which is impossible for n = 10^5.
// Instead:
// Step 1. Frequency array
// freq[x] = occurrences of x
// Step 2. Count numbers divisible by every d
// For every divisor
// cnt[d] = count of numbers divisible by d
// using a sieve.
// Step 3. Count pairs having GCD exactly d
// Initially
// pairs[d] = C(cnt[d],2)
// This counts pairs whose gcd is multiple of d.
// Remove larger multiples using Inclusion-Exclusion.
// exact[d] = pairs[d]

// for multiples:
//     exact[d] -= exact[multiple]
// Step 4. Prefix counts
// Since gcdPairs is sorted,
// gcd =1 occupies

// exact[1] positions

// gcd=2 occupies

// exact[2] positions

// ...
// Build prefix sums.
// pref[d]
// Step 5. Binary Search
// For every query
// Find the first gcd whose prefix count exceeds the query index.
// Time Complexity: O(n log n + q log n) where n is the maximum number in nums and q is the number of queries.
// Space Complexity: O(n) for frequency and prefix arrays.