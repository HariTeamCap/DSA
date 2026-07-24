class Solution{
    public static void main(String[] args){
        int n = 36;
        int i = 5;
        int switchMask = 1 << i;
        if ((n & switchMask) == 0){
            System.out.println("Switch is ON");
        } else {
            System.out.println("Switch is OFF");
        }
    }
}

//Algorithm: Explanation
//1. The code defines a class Solution with a main method that checks whether the ith bit of an integer n is ON or OFF using the AND operation.
//2. The integer n is initialized to 36, and the bit position i is set to 5. The switchMask is created by left-shifting 1 by i positions, which results in a binary number with a 1 at the ith position and 0s elsewhere.
//3. The AND operation (&) is then used to check the ith bit of n. If the result of (n & switchMask) is 0, it means the ith bit is OFF (0), and the program prints "Switch is ON". If the result is not 0, it means the ith bit is ON (1),and the program prints "Switch is OFF".

//Time Complexity: O(1), as the operations performed (bit shifting and AND) take constant time.
//Space Complexity: O(1), as no additional space is used that scales with input size