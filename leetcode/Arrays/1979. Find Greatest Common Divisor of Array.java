class Solution {

    public int findGCD(int[] nums) {

        int min = nums[0];
        int max = nums[0];

        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        return gcd(min, max);
    }

    private int gcd(int a, int b) {

        while (b != 0) {

            int temp = b;
            b = a % b;
            a = temp;

        }

        return a;
    }
}

// Algorithm:
// 1. Initialize two variables, `min` and `max`, to the first element of the array `nums`.
// 2. Iterate through each number in the array `nums`:
//    - Update `min` to be the smaller of the current `min` and the current number.
//    - Update `max` to be the larger of the current `max` and the current number.
// 3. After finding the minimum and maximum values in the array, call the `gcd` function with `min` and `max` as arguments to compute their greatest common divisor.   
// 4. The `gcd` function uses the Euclidean algorithm to compute the greatest common divisor:
//    - While `b` is not zero, repeatedly set `temp` to `b`, update `b` to `a % b`, and set `a` to `temp`.
// 5. Return the value of `a`, which will be the greatest common divisor of the two numbers.

// Time Complexity: O(n + log(min, max)), where n is the length of the input array `nums`. The first part of the algorithm iterates through the array to find the minimum and maximum values, which takes O(n) time. The second part computes the GCD using the Euclidean algorithm, which takes O(log(min, max)) time.
// Space Complexity: O(1), as we are using a constant amount of extra space for variables `min`, `max`, and the temporary variable in the GCD function.

