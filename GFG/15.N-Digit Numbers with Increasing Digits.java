class Solution {
    public static ArrayList<Integer> increasingNumbers(int n) {
        ArrayList<Integer> ans = new ArrayList<>();

        if (n > 10) return ans;

        if (n == 1) {
            for (int i = 0; i <= 9; i++)
                ans.add(i);
            return ans;
        }

        dfs(n, 1, 0, ans);

        return ans;
    }

    static void dfs(int n, int start, int num, ArrayList<Integer> ans) {
        if (n == 0) {
            ans.add(num);
            return;
        }

        for (int d = start; d <= 9; d++) {
            dfs(n - 1, d + 1, num * 10 + d, ans);
        }
    }
}
// Approach
// If n > 10, return an empty list.
// For n == 1, return 0 to 9.
// Otherwise, use DFS:
// Start from digit 1.
// Pick the next digit from start to 9.
// Append it to the current number.
// Recurse with the next larger starting digit.
// Whenever n digits are formed, add the number to the answer.

//Time: O(1)
//Space: O(n)