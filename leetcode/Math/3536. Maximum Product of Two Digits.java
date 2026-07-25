class Solution {
    public int maxProduct(int n) {
        int m1 = -1;
        int m2 = -1;

        while (n > 0){
            int d = n % 10;

            if (d >= m1){
                m2 = m1;
                m1 = d;
            } else if (d > m2){
                m2 = d;
            }
            n /= 10;
        }
        return m1 * m2;
    }
}

//Algorithm:
//1. Initialize two variables m1 and m2 to -1. These will hold the two largest digits found in the number n.
//2. Use a while loop to iterate through each digit of the number n until n becomes 0.
//3. Inside the loop, extract the last digit of n using the modulus operator (% 10) and store it in variable d.
//4. Compare the extracted digit d with m1 and m2 to determine if it is larger than either of them.
//   - If d is greater than or equal to m1, update m2 to be the current value of m1, and then update m1 to be d.
//   - If d is greater than m2 but less than m1, update m2 to be d.
//5. Remove the last digit from n by performing integer division by 10 (n /= 10).
//6. After the loop ends, return the product of m1 and m2, which are the two largest digits found in the number n.

//Time Complexity: O(log n), where n is the input number. The while loop iterates through each digit of n, and the number of digits in n is proportional to log n.  
//Space Complexity: O(1), as we are using a constant amount of extra space for the variables m1 and m2, regardless of the size of the input number n.
