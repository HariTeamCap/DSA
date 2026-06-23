class Solution {
    int maxPeopleDefeated(int p) {
        long left = 0;
        long right = 10000;
        while (left <= right) {
            long mid = left + (right - left) / 2;
            long sum = mid * (mid + 1) * (2 * mid + 1) / 6;
            if (sum <= p) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
            
        }
        return (int) right;
    }
}
// Time: O(log √p) ≈ O(log p)
// Space: O(1)