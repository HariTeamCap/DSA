// Approach
// Start from every digit 1 to 9.
// Continuously append the next digit to form sequential numbers.
// Stop when the next digit becomes 10.
// If the generated number lies in [low, high], add it to the answer.
// Finally, sort the answer list.
// Since there are only 36 possible sequential numbers, this approach is very efficient.

class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> ans = new ArrayList<>();

        for (int start = 1; start <= 9; start++){
            int num = start;

            for (int next = start + 1; next <= 9; next++){
                num = num * 10 + next;

                if (num >= low && num <= high){
                    ans.add(num);
                }
            }
        }
        Collections.sort(ans);

        return ans;
    }
}
// Time Complexity: O(1) - The number of sequential digits is constant (36), so the time complexity is O(1).
// Space Complexity: O(1) - The space used for the answer list is constant since the maximum number of sequential digits is 36, which is also considered O(1) space complexity.    