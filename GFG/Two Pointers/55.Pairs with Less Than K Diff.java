import java.util.Arrays;

class Solution {
    public static int countPairs(int arr[], int k) {
        Arrays.sort(arr);

        int n = arr.length;
        int left = 0;
        int right = 1;
        int count = 0;

        while (right < n) {
            if (arr[right] - arr[left] < k) {
                count += (right - left);
                right++;
            } else {
                left++;
                if (left == right)
                    right++;
            }
        }

        return count;
    }
}
public class Main {
    public static void main(String[] args) {
        int arr[] = {1, 5, 3, 4, 2};
        int k = 3;
        System.out.println(Solution.countPairs(arr, k)); // Output: 4
    }
}
//Time Complexity: O(n log n) where n is the size of the array (due to sorting).
//Space Complexity: O(1) as we are using constant extra space.

//Aproach:
//1. Sort the array to make it easier to find pairs with less than k difference.
//2. Use two pointers, left and right, to traverse the array.
//3. If the difference between arr[right] and arr[left] is less than k, increment the count by (right - left) and move the right pointer forward.
//4. If the difference is greater than or equal to k, move the left pointer forward. If left equals right, move the right pointer forward as well.
//5. Continue this process until the right pointer reaches the end of the array.
//6. Return the count of pairs found.   

