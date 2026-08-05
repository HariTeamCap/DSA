class Solution {

    public int countSubarray(int[] arr, int l, int r) {
        return countAtMost(arr, r) - countAtMost(arr, l - 1);
    }

    private int countAtMost(int[] arr, int limit) {
        if (limit < 0)
            return 0;

        int left = 0;
        long sum = 0;
        int count = 0;

        for (int right = 0; right < arr.length; right++) {
            sum += arr[right];

            while (sum > limit) {
                sum -= arr[left++];
            }

            count += (right - left + 1);
        }

        return count;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = {1, 2, 3, 4};
        int l = 3;
        int r = 6;
        System.out.println(solution.countSubarray(arr, l, r)); // Output: 6
    }
}

//Time Complexity: O(n) where n is the size of the array (due to single pass through the array).
//Space Complexity: O(1) as we are using constant extra space.

//Approach:
//1. Use a helper function countAtMost to count the number of subarrays with sum less than or equal to a given limit.
//2. The countSubarray function calculates the number of subarrays with sum in the range [l, r] by subtracting the count of subarrays with sum less than l from the count of subarrays with sum less than or equal to r.
//3. In countAtMost, use a sliding window approach with two pointers (left and right)
//   to maintain the current sum of the subarray. If the sum exceeds the limit,
//   move the left pointer to reduce the sum until it is within the limit.      
//4. For each position of the right pointer, add the number of valid subarrays ending at that position to the count.
//5. Return the total count of subarrays with sum in the specified range.

