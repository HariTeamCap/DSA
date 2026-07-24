import java.util.Scanner;

public class Programming {

    // Convert decimal to binary string
    public static String decimalToBinary(int n) {
        if (n == 0) return "0";

        String s = "";
        while (n > 0) {
            s = (n % 2) + s;
            n = n / 2;
        }
        return s;
    }

    // Brute force: Find position of rightmost set bit
    public static int mostRightSetBit(int n) {
        if (n == 0)
            return -1;   // No set bit

        String binary = decimalToBinary(n);

        int position = 1;
        for (int i = binary.length() - 1; i >= 0; i--) {
            if (binary.charAt(i) == '1') {
                return position;
            }
            position++;
        }

        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        System.out.println("Binary: " + decimalToBinary(n));
        System.out.println("Rightmost set bit position: " + mostRightSetBit(n));

        sc.close();
    }
}

//Algorithm: Explanation
//1. The code defines a class Programming with methods to convert a decimal number to its binary representation and to find the position of the rightmost set bit in a given integer using a brute-force approach.
//2. The decimalToBinary method converts a decimal integer n to its binary string representation by repeatedly dividing n by 2 and prepending the remainder to the result string.
//3. The mostRightSetBit method checks for the rightmost set bit in the binary representation of n. It iterates through the binary string from right to left, returning the position of the first '1' encountered. If no set bit is found, it returns -1.
//4. The main method reads an integer input from the user, prints its binary representation, and displays the position of the rightmost set bit.


//Time Complexity: O(log n), where n is the input integer, as the number of bits in the binary representation is proportional to log n.
//Space Complexity: O(log n), as the binary string representation of the integer is stored in memory.