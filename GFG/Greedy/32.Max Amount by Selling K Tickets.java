// Approach
// Always sell the ticket from the seller having the maximum remaining tickets.
// Use a Max Heap (PriorityQueue in reverse order).
// Insert all ticket counts into the heap.
// Repeat k times:
// Remove the maximum value.
// Add it to the answer.
// If it becomes greater than 1, insert value - 1 back into the heap.
// Since each sale should maximize the current profit, this greedy strategy is optimal.

class Solution {
    static final int MOD = 1_000_000_007;
    public int maxAmount(int[] arr, int k) {
        // code here
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int tickets : arr){
            pq.offer(tickets);
        }
        long ans = 0;
        
        while (k-- > 0 && !pq.isEmpty()) {
            int curr = pq.poll();
            ans = (ans + curr) % MOD;
            if (curr > 1) {
                pq.offer(curr - 1);
            }
        }
        return (int) ans;
    }
}

// Time complexity: O(n + k log n)
// Space complexity: O(n) for the priority queue