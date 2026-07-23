class Solution{
    public static void main(String args[]){
        int n = 36;
        int i = 5;
        int offMask = ~(1 << i);
        System.out.println(n & offMask);
    }
}

// Algorithm
// 1. Create a mask by left shifting 1 by i positions (1 << i) and then taking the bitwise NOT of it (~(1 << i)).
// 2. Perform a bitwise AND operation between n and the mask (n & offMask).
// 3. The result will have the ith bit set to 0, regardless of its original value in n.

// Complexity
// Time: O(1), as the operations are constant time.
// Space: O(1), as we are using a constant amount of space.