class Solution {

    static final int MOD = 1000000007;

    public int countSubsets(int[] arr) {

        int[] freq = new int[31];
        for (int x : arr) freq[x]++;

        int[] primes = {2,3,5,7,11,13,17,19,23,29};

        int[] mask = new int[31];
        boolean[] valid = new boolean[31];

        for (int x = 2; x <= 30; x++) {

            int num = x;
            int m = 0;
            boolean ok = true;

            for (int i = 0; i < 10; i++) {

                int cnt = 0;

                while (num % primes[i] == 0) {
                    num /= primes[i];
                    cnt++;
                }

                if (cnt > 1) {
                    ok = false;
                    break;
                }

                if (cnt == 1)
                    m |= (1 << i);
            }

            if (num == 1 && ok) {
                valid[x] = true;
                mask[x] = m;
            }
        }

        long[] dp = new long[1 << 10];
        dp[0] = 1;

        for (int x = 2; x <= 30; x++) {

            if (freq[x] == 0 || !valid[x]) continue;

            long[] next = dp.clone();

            for (int s = 0; s < (1 << 10); s++) {

                if ((s & mask[x]) == 0) {
                    int ns = s | mask[x];
                    next[ns] = (next[ns] + dp[s] * freq[x]) % MOD;
                }
            }

            dp = next;
        }

        long mul = 1;

        for (int i = 0; i < freq[1]; i++)
            mul = (mul * 2) % MOD;

        long ans = 0;

        for (int s = 1; s < (1 << 10); s++)
            ans = (ans + dp[s]) % MOD;

        ans = (ans * mul) % MOD;

        return (int) ans;
    }
}

// Approach
// 1. Create an array freq to count the frequency of each number from 1 to 30 in the input array arr.
// 2. Define an array primes containing the first 10 distinct prime numbers.
// 3. Create an array mask to store the bitmask representation of each number from 2 to 30 based on its prime factorization, and a boolean array valid to indicate whether a number has distinct prime factors.
// 4. Iterate through numbers from 2 to 30, and for each number, calculate its prime factorization. If it has distinct prime factors, update the mask and valid arrays accordingly.
// 5. Initialize a dynamic programming array dp of size 2^10 to store the count of subsets for each bitmask representation of prime factors. Set dp[0] to 1, representing the empty subset.
// 6. Iterate through numbers from 2 to 30, and for each valid number with a non-zero frequency, update the dp array by considering all possible subsets that can be formed by including the current number. Use bitwise operations to ensure that the prime factors do not overlap with existing subsets.
// 7. Calculate the multiplier for the number of subsets that can be formed using the number 1, which can be included in any subset without affecting the distinct prime factor condition. The multiplier is 2 raised to the power of the frequency of 1, modulo MOD.
// 8. Sum the counts of all non-empty subsets from the dp array and multiply by the multiplier to account for the subsets that can include the number 1.
// 9. Return the final answer modulo MOD, which represents the total number of valid subsets with products of distinct primes.

// Time Complexity: O(n + 2^10 * 30) = O(n + 10240), where n is the number of elements in the input array. The first part counts frequencies, and the second part processes each number from 2 to 30 with bitmasking.
// Space Complexity: O(2^10) = O(1024), for the dp array that stores counts of subsets for each bitmask representation of prime factors.    