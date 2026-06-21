class Solution {
    public int maxIceCream(int[] costs, int coins) {
        int[] freq = new int[100001];
        for (int cost : costs){
            freq[cost]++;
        } 
        int count = 0;
        
        for (int cost = 1; cost <= 100000; cost++){
            if(freq[cost] == 0) continue;
            int canBuy = Math.min(freq[cost], coins/cost);
            count += canBuy;
            coins -= canBuy * cost;
            if (coins < cost){
                continue;
            }
        }
        return count;
    }
}
//Time : O(n + 100000)
//Space: O(n + 100000)

// Approach (Counting Sort)
// costs[i] ≤ 10^5, so create a frequency array of size 100001.
// Count how many ice creams have each cost.
// Traverse costs from smallest to largest.
// Buy as many ice creams as possible at each cost.
// This ensures we always buy the cheapest bars first, maximizing the count.