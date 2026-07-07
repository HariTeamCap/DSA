import java.util.*;

class Solution {
    public int minInsAndDel(int[] a, int[] b) {

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < b.length; i++) {
            map.put(b[i], i);
        }

        ArrayList<Integer> list = new ArrayList<>();

        for (int x : a) {
            if (map.containsKey(x)) {
                list.add(map.get(x));
            }
        }

        ArrayList<Integer> lis = new ArrayList<>();

        for (int x : list) {

            int idx = Collections.binarySearch(lis, x);

            if (idx < 0)
                idx = -(idx + 1);

            if (idx == lis.size())
                lis.add(x);
            else
                lis.set(idx, x);
        }

        int lcs = lis.size();

        return (a.length - lcs) + (b.length - lcs);
    }
}

// Complexity
// Building map: O(m)
// Traversing a: O(n)
// LIS using binary search: O(n log m)
// Overall: O(n log m)
// Space: O(n + m)
// Interview Tip
// Normally, "Minimum Insertions and Deletions to Convert One Array to Another" is solved using LCS DP (O(nm)). However, because the problem guarantees that b is sorted and contains distinct elements, you can optimize it by converting the problem into an LIS problem, reducing the complexity to O(n log m).


// Efficient Approach (O(n log m))
// Because b is sorted and has distinct elements:
// Store every element of b with its index using a HashMap.
// Traverse a.
// If a[i] exists in b, replace it with its index in b.
// The problem now becomes finding the Longest Increasing Subsequence (LIS) of these indices.
// Why?
// Since b is sorted and unique, any common subsequence must appear in increasing index order.


//LCS approach
// Since:
// b is sorted
// all elements in b are distinct
// we can find the Longest Common Subsequence (LCS) between a and b.
// Then,
// Delete all elements from a that are not in the LCS.
// Insert all elements from b that are not in the LCS.
// So,
// Answer = (n - LCS) + (m - LCS)
//        = n + m - 2 × LCS