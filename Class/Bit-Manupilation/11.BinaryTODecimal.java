
import java.util.Scanner;

class BinaryToDecimal {
    static int binaryToDecimal(String n) {
        int result = 0;
        int power2 = 1;
        for (int i = n.length() - 1; i >= 0; i--) {
            if (n.charAt(i) == '1') {
                result = result + power2;
            }
            power2 =  power2 * 2;
        }
        return result;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String a = sc.next();
        int x = binaryToDecimal(a);
        System.out.println(x);
    }
}

//Time Complexity: O(n) where n is the length of the binary string.
//Space Complexity: O(1) as we are using constant space.

//Algorithm:
//1. Initialize result as 0 and power2 as 1.
//2. Loop through the binary string from the last character to the first.
//3. If the current character is '1', add power2 to result.
//4. Multiply power2 by 2 for the next iteration.