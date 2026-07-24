class Solution{
    public static void main(String[] args){
        int n = 36;
        int i = 5;
        int toggleMask = 1 << i;
        System.out.println(n ^ toggleMask);
    }
}

//Algorithm: Explanation
//1. The code defines a class Solution with a main method that demonstrates how to toggle a specific bit in an integer using the XOR operation.
//2. The integer n is initialized to 36, and the bit position i is set to 5. The toggleMask is created by left-shifting 1 by i positions, which results in a binary number with a 1 at the ith position and 0s elsewhere.
//3. The XOR operation (^) is then used to toggle the ith bit of n. If the ith bit of n is 0, it will become 1, and if it is 1, it will become 0. The result of the XOR operation is printed to the console.

//Time Complexity: O(1), as the operations performed (bit shifting and XOR) take constant time.
//Space Complexity: O(1), as no additional space is used that scales with input size 