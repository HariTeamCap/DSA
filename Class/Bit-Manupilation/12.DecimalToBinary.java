import java.util.Scanner;

class DecimalToBinary {
    static String decimalToBinary(int n) {
        String b = "";
        while (n >= 1) {
            int x = n % 2;
            n = n / 2;
            b = x + b;
        }
        return b;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        System.out.println(decimalToBinary(a));
    }
}
//Time Complexity: O(log n) where n is the decimal number.
//Space Complexity: O(1) as we are using constant space.

//Algorithm:
//1. Initialize an empty string b to store the binary representation.
//2. While n is greater than or equal to 1, do the following:
//   a. Find the remainder of n when divided by 2 and store it in x.
//   b. Divide n by 2 and update its value.
//   c. Prepend x to the string b.