class Solution {
    int minSubsets(int arr[]) {
        // code here
        Arrays.sort(arr);
        
        int ans = 1;
        
        for (int i = 1; i < arr.length; i++){
            if (arr[i] != arr[i - 1] + 1)
                ans++;
        }
        return ans;
    }
}

// Approach
// 1. Sort the array in ascending order.
// 2. Initialize a variable ans to 1, which will keep track of the number of subsets.
// 3. Iterate through the sorted array starting from the second element.
// 4. For each element, check if it is not equal to the previous element plus one. If it is not, increment ans by 1, indicating the start of a new subset
// 5. Return the value of ans, which represents the minimum number of subsets needed to split the array such that each subset contains consecutive integers.

// Time Complexity: O(n log n) due to sorting the array, where n is the number of elements in the array.
//Space Complexity: O(1) as we are using a constant amount of extra space.