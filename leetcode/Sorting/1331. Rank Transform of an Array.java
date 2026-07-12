// Approach
// Copy the original array.
// Sort the copied array.
// Traverse the sorted array and assign ranks.
// If an element is already ranked, skip it.
// Otherwise assign the next available rank.
// Traverse the original array and replace each element with its rank using the HashMap.

class Solution {
    public int[] arrayRankTransform(int[] arr) {
        int n = arr.length;

        int[] sorted = arr.clone();
        Arrays.sort(sorted);

        Map<Integer, Integer> map = new HashMap<>();

        int rank = 1;
        for (int num : sorted){
            if (!map.containsKey(num)){
                map.put(num, rank++);
            }
        }

        int[] ans = new int[n];

        for (int i = 0; i < n; i++){
            ans[i] = map.get(arr[i]);
        }
        return ans;
    }
}

// Time Complexity: O(n log n) due to sorting the array.
// Space Complexity: O(n) for storing the ranks in the HashMap and the sorted array.