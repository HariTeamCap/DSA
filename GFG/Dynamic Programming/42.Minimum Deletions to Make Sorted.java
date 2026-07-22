class Solution {
    public int minDeletions(int[] arr) {
        // code here
        ArrayList<Integer> lis = new ArrayList<>();
        
        for (int num : arr){
            int idx = Collections.binarySearch(lis, num);
            
            if (idx < 0)
                idx = -(idx + 1);
            
            if (idx == lis.size())
                lis.add(num);
            
            else
                lis.set(idx, num);
        }
        return arr.length - lis.size();
    }
}

//Time Complexity: O(nlogn) where n is the size of the array.
//Space Complexity: O(n) where n is the size of the array.

//Algorithm: Explanation
// The problem is to find the minimum number of deletions required to make the array sorted.
// To solve this problem, we can use the concept of Longest Increasing Subsequence (LIS). The length of the LIS gives us the maximum number of elements that can be kept in sorted order. Therefore, the minimum number of deletions required to make the array sorted is equal to the total number of elements in the array minus the length of the LIS.   
