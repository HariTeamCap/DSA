// Approach (Mathematical Observation)
// Suppose the sequence starts from a and has length k.
// Then,
// a + (a + 1) + (a + 2) + ... + (a + k - 1) = n
// Using the sum of an AP,
// k * (2a + k - 1) / 2 = n
// Rearrange to get:
// 2n = k * (2a + k - 1)
// For every possible length k (>= 2), we can compute:
// a = (n - k*(k-1)/2) / k
// The sequence is valid if:
// a > 0
// (n - k*(k-1)/2) is divisible by k
// We only need to check while
// k * (k + 1) / 2 <= n
// because the smallest sum of k consecutive positive integers is
// 1 + 2 + ... + k



class Solution {
    public int getCount(int n) {
        // code here
        int count = 0;
        
        for (int k = 2; (long) k * (k + 1) / 2 <= n; k++){
            int remaining = n - (k * (k - 1)) / 2;
            
            if (remaining > 0 && remaining % k == 0){
                count++;
            }
        }
        return count;
    }
}

// This runs in:
// Time Complexity: O(√n)
// Space Complexity: O(1)