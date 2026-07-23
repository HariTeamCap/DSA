class Solution{
    public static void main(String args[]){
        int n = 36;
        int i = 3;
        int onMask = 1 << i;
        System.out.println(n | onMask);
    }
}

// Algorithm
// 1. Create a mask by left shifting 1 by i positions (1 << i).
// 2. Perform a bitwise OR operation between n and the mask (n | onMask).
// 3. The result will have the ith bit set to 1, regardless of its original value in n.

// Complexity
// Time: O(1), as the operations are constant time.
// Space: O(1), as we are using a constant amount of space.

