import java.util.*;

class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        HashSet<Integer> set = new HashSet<>();

        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
            set.add(num);
        }

        List<Integer> ans = new ArrayList<>();

        for (int i = min + 1; i < max; i++) {
            if (!set.contains(i)) {
                ans.add(i);
            }
        }

        return ans;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1, 3, 5, 7};
        List<Integer> missingElements = solution.findMissingElements(nums);
        System.out.println(missingElements); // Output: [2, 4, 6]
    }
}

//Time Complexity: O(n) where n is the size of the input array (for finding min, max, and adding to the set).
//Space Complexity: O(n) for storing the elements in the HashSet and the missing elements

//Approach:
//1. Initialize min and max to track the minimum and maximum values in the array.
//2. Use a HashSet to store the elements of the array for O(1) lookups.
//3. Iterate through the array to find the min and max values and populate the HashSet with the elements of the array.
//4. Create a list to store the missing elements.
//5. Iterate from min + 1 to max - 1 and check if each number is present in the HashSet. If not, add it to the list of missing elements.
//6. Return the list of missing elements.