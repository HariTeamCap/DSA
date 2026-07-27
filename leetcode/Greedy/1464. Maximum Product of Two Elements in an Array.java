class Solution {
    public int maxProduct(int[] nums) {

        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;

        for (int num : nums) {

            if (num >= max1) {
                max2 = max1;
                max1 = num;
            } else if (num > max2) {
                max2 = num;
            }
        }

        return (max1 - 1) * (max2 - 1);
    }
}

//Time Complexity: O(n) where n is the length of the input array. We are traversing the array once to find the two maximum elements.
//Space Complexity: O(1) as we are using only a constant amount of extra space

//Algorithm:
//1. Initialize two variables max1 and max2 to store the two maximum elements in the array. Set them to Integer.MIN_VALUE initially.
//2. Traverse the input array nums:
   - If the current number is greater than or equal to max1, update max2 to max1 and set max1 to the current number.
   - Else if the current number is greater than max2, update max2 to the current number.
//3. After traversing the array, return the product of (max1 - 1) and (max2 - 1) as the result.