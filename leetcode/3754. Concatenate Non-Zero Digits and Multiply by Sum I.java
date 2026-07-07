class Solution {
    public long sumAndMultiply(int n) {
        String s = String.valueOf(n);
        long x = 0;

        for (int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);

            if (ch != '0'){
                x = x * 10 + (ch - '0');
            }
        }
        long temp = x;
        long sum = 0;

        while (temp > 0){
            sum += temp % 10;
            temp /= 10;
        }
        return x * sum;
    }
}

// You can solve it by:
// Extracting the non-zero digits in their original order.
// Forming the new number x.
// Calculating the sum of digits of x.
// Returning x * sum.

// Time Complexity
// O(d), where d is the number of digits in n.
// Space Complexity
// O(d) due to the string representation.