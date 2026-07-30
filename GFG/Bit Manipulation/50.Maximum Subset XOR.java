class Solution {
    public int maxSubsetXOR(int[] arr) {

        int n = arr.length;
        int index = 0;

        for (int bit = 31; bit >= 0; bit--) {

            int maxIndex = -1;

            for (int i = index; i < n; i++) {
                if (((arr[i] >> bit) & 1) == 1) {
                    maxIndex = i;
                    break;
                }
            }

            if (maxIndex == -1)
                continue;

            int temp = arr[index];
            arr[index] = arr[maxIndex];
            arr[maxIndex] = temp;

            for (int i = 0; i < n; i++) {
                if (i != index && ((arr[i] >> bit) & 1) == 1) {
                    arr[i] ^= arr[index];
                }
            }

            index++;
        }

        int ans = 0;

        for (int i = 0; i < index; i++) {
            ans = Math.max(ans, ans ^ arr[i]);
        }

        return ans;
    }
}

// Approach
// 1. Initialize a variable index to 0, which will keep track of the current position in the array.
// 2. Iterate through the bits from 31 to 0 (assuming 32-bit integers).
// 3. For each bit, find the maximum index of the element in the array that has that bit set to 1. If no such element exists, continue to the next bit.
// 4. Swap the element at the current index with the element at the maximum index found in step 3.
// 5. For each element in the array, if it is not the current index and has the current bit set to 1, perform an XOR operation with the element at the current index.
// 6. Increment the index to move to the next position in the array.    
// 7. After processing all bits, initialize a variable ans to 0.
// 8. Iterate through the elements in the array up to the current index and calculate the maximum XOR value by performing an XOR operation with ans and each element.
// 9. Return the value of ans, which represents the maximum subset XOR value.

// Time Complexity: O(n * 32) = O(n), where n is the number of elements in the array. The outer loop runs for 32 bits, and the inner loops run for n elements.
// Space Complexity: O(1) as we are using a constant amount of extra space.