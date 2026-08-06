class Solution {
    public int countMinOperations(int[] arr) {

        int increments = 0;
        int maxBits = 0;

        for (int num : arr) {
            increments += Integer.bitCount(num);
            if (num != 0) {
                maxBits = Math.max(maxBits, 31 - Integer.numberOfLeadingZeros(num));
            }
        }

        return increments + maxBits;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = {1, 5, 2};
        System.out.println(solution.countMinOperations(arr)); // Output: 6
    }
}
//Time Complexity: O(n) where n is the size of the array (due to single pass through the array).
//Space Complexity: O(1) as we are using constant extra space.

//Approach:
//1. Initialize two variables: increments to count the total number of increment operations needed, and maxBits to track the maximum number of bits required for any number in the array.
//2. Iterate through each number in the array:
//   - Use Integer.bitCount(num) to count the number of 1s in the binary representation of the number and add it to increments.
//   - If the number is not zero, calculate the position of the highest set bit using 31 - Integer.numberOfLeadingZeros(num) and update maxBits if this position is greater than the current maxBits.
//3. After processing all numbers, the total number of operations required is the sum of increments and maxBits, which is returned as the result.
