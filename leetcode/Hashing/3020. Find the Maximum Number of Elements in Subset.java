class Solution {
    public int maximumLength(int[] nums) {

        Map<Long, Integer> freq = new HashMap<>();

        for (int x : nums) {
            freq.put((long) x, freq.getOrDefault((long) x, 0) + 1);
        }

        int ans = 1;

        // Handle value 1 separately
        if (freq.containsKey(1L)) {
            int cnt = freq.get(1L);
            ans = Math.max(ans, (cnt % 2 == 0) ? cnt - 1 : cnt);
        }

        for (long x : freq.keySet()) {

            if (x == 1) continue;

            long cur = x;
            int len = 0;

            while (true) {

                int cnt = freq.getOrDefault(cur, 0);

                if (cnt >= 2) {
                    len += 2;

                    if (cur > 1000000000L) break;

                    cur = cur * cur;

                    if (cur > (long) 1e18) break;
                } else {
                    if (cnt == 1)
                        len++;
                    else
                        len--;

                    break;
                }
            }

            ans = Math.max(ans, len);
        }

        return ans;
    }
}

// Approach
// Build a frequency map.
// Treat 1 separately because squaring it never changes its value.
// For every other number, repeatedly square it while at least two copies exist.
// The last number with one remaining copy becomes the center.
// Track the maximum valid pattern length.

//Time: O(n)
//Space: O(n)

// Algorithm
// Count frequency of every number.
// Handle 1 separately.
// For every other number:
// Keep squaring it.
// If current value has at least 2 copies, use both and continue.
// Otherwise stop.
// Last valid number becomes the center (used once).
// Compute maximum length.