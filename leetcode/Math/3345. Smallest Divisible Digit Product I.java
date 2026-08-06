class Solution {
    public int smallestNumber(int n, int t) {

        while (true) {
            int product = 1;
            int num = n;

            while (num > 0) {
                product *= (num % 10);
                num /= 10;
            }

            if (product % t == 0)
                return n;

            n++;
        }
    }
}
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 10;
        int t = 5;
        System.out.println(solution.smallestNumber(n, t)); // Output: 10
    }
}

//Time Complexity: O(k * d) where k is the number of integers checked until we find the answer and d is the number of digits in each integer (since we calculate the product of digits).
//Space Complexity: O(1) as we are using constant extra space.

//Approach:
//1. Start with the given integer n and continuously check each subsequent integer.
//2. For each integer, calculate the product of its digits by repeatedly extracting the last digit (using modulo 10) and multiplying it to a product variable, while reducing the number by dividing it by 10.
//3. After calculating the product of digits, check if this product is divisible by t. If it is, return the current integer n as the result.
//4. If not divisible, increment n and repeat the process until a valid integer is found.
