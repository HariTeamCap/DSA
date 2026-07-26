class Solution {
    public int maximumProduct(int[] nums) {

        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;

        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;

        for (int num : nums) {

            if (num >= max1) {
                max3 = max2;
                max2 = max1;
                max1 = num;
            } else if (num >= max2) {
                max3 = max2;
                max2 = num;
            } else if (num > max3) {
                max3 = num;
            }

            if (num <= min1) {
                min2 = min1;
                min1 = num;
            } else if (num < min2) {
                min2 = num;
            }
        }

        return Math.max(max1 * max2 * max3,
                        min1 * min2 * max1);
    }
}

// Time Complexity: O(n)
// Space Complexity: O(1)

// Algorithm:
// 1. Initialize three variables max1, max2, and max3 to store the three largest numbers in the array, and two variables min1 and min2 to store the two smallest numbers in the array.
// 2. Iterate through each number in the input array nums.
// 3. For each number, update max1, max2, and max3 accordingly to keep track of the three largest numbers.
// 4. Similarly, update min1 and min2 to keep track of the two smallest numbers.
// 5. After processing all numbers, calculate the maximum product by comparing the product of the three largest numbers (max1 * max2 * max3)
//    with the product of the two smallest numbers and the largest number (min1 * min2 * max1).
// 6. Return the maximum of these two products as the result.   