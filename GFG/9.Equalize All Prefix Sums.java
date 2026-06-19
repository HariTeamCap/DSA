class Solution {
    public ArrayList<Integer> optimalArray(int[] arr) {

        int n = arr.length;

        long[] prefix = new long[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + arr[i];
        }

        ArrayList<Integer> ans = new ArrayList<>();

        for (int i = 0; i < n; i++) {

            int mid = i / 2;
            long median = arr[mid];

            long leftCost =
                    median * (mid + 1L)
                    - (prefix[mid + 1] - prefix[0]);

            long rightCost =
                    (prefix[i + 1] - prefix[mid + 1])
                    - median * (i - mid);

            ans.add((int)(leftCost + rightCost));
        }

        return ans;
    }
}
//Time: O(n)
//Space: O(n)