class Solution {
    public int countFriendsPairings(int n) {

        if (n <= 2)
            return n;

        long prev2 = 1;
        long prev1 = 2;

        for (int i = 3; i <= n; i++) {
            long curr = prev1 + (i - 1) * prev2;
            prev2 = prev1;
            prev1 = curr;
        }

        return (int) prev1;
    }
}
public class FriendsPairingProblem {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 4; // Example input
        int result = solution.countFriendsPairings(n);
        System.out.println("Number of ways to pair " + n + " friends: " + result);
    }
}

// Time Complexity: O(n) - We are iterating from 3 to n, so the time complexity is linear with respect to n.
// Space Complexity: O(1) - We are using a constant amount of space to store the previous two values, so the space complexity is constant.  

//Approach: Explanation:
//1. The problem is to count the number of ways to pair n friends, where each friend can either remain single or be paired with another friend.
//2. We can use dynamic programming to solve this problem. The recurrence relation for the number of ways to pair n friends is:
//   f(n) = f(n-1) + (n-1) * f(n-2)
//   - f(n-1): The first friend remains single, and we need to pair the remaining n-1 friends.
//   - (n-1) * f(n-2): The first friend pairs with any of the remaining n-1 friends, and we need to pair the remaining n-2 friends.
//3. We initialize the base cases:
//   - f(1) = 1 (only one way for one friend to remain single)
//   - f(2) = 2 (either both friends remain single or they pair up)
//4. We then iterate from 3 to n, calculating the number of ways to pair friends using the recurrence relation and storing only the last two computed values to optimize space usage.


