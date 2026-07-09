// Approach (Hashing + Remainders)
// Instead of checking every pair (O(n²)), observe:
// If
// (a + b) % k == 0
// then
// remainder(a) + remainder(b) = k
// (modulo k).
// Steps
// Create an array freq[k] to store the frequency of each remainder.
// Traverse the array.
// For every element:
// Compute rem = arr[i] % k.
// Required remainder:
// If rem == 0, need another 0.
// Otherwise, need k - rem.
// Add the frequency of the required remainder to the answer.
// Increment the frequency of rem.
// This counts each pair exactly once.



class Solution {
    public int countKdivPairs(int[] arr, int k) {
        // code here
        int[] freq = new int[k];
        int count = 0;
        
        for (int num : arr){
            int rem = num % k;
            
            if (rem == 0)
                count += freq[0];
            
            else 
                count += freq[k - rem];
            
            freq[rem]++;
        }
        return count;
    }
}

// Complexity
// Time: O(n)
// Space: O(k)