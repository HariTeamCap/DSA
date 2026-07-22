import java.util.*;

class Solution {
    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
        int n = s.length();

        // Decompose s into maximal runs (blocks)
        List<int[]> blockList = new ArrayList<>();
        int i = 0;
        while (i < n) {
            int j = i;
            char c = s.charAt(i);
            while (j < n && s.charAt(j) == c) j++;
            blockList.add(new int[]{i, j - 1, c - '0'});
            i = j;
        }
        int m = blockList.size();
        int[] start = new int[m], end = new int[m], ch = new int[m], len = new int[m];
        for (int k = 0; k < m; k++) {
            start[k] = blockList.get(k)[0];
            end[k]   = blockList.get(k)[1];
            ch[k]    = blockList.get(k)[2];
            len[k]   = end[k] - start[k] + 1;
        }

        int[] posToBlock = new int[n];
        for (int k = 0; k < m; k++)
            for (int p = start[k]; p <= end[k]; p++) posToBlock[p] = k;

        int wholeOnes = 0;
        for (int k = 0; k < m; k++) if (ch[k] == 1) wholeOnes += len[k];

        final int NEG = Integer.MIN_VALUE / 2;
        int[] gainFull = new int[Math.max(m, 1)];
        Arrays.fill(gainFull, NEG);
        // B = block k (a '1' block) strictly interior with both flanks fully counted
        for (int k = 1; k <= m - 2; k++) {
            if (ch[k] == 1) gainFull[k] = len[k - 1] + len[k + 1];
        }

        // Sparse table for range-max on gainFull
        int LOG = 1;
        while ((1 << LOG) <= Math.max(m, 1)) LOG++;
        int[][] sparse = new int[LOG][Math.max(m, 1)];
        if (m > 0) {
            sparse[0] = gainFull.clone();
            for (int p = 1; p < LOG; p++) {
                int half = 1 << (p - 1);
                for (int k = 0; k + (1 << p) <= m; k++) {
                    sparse[p][k] = Math.max(sparse[p - 1][k], sparse[p - 1][k + half]);
                }
            }
        }
        int[] logTable = new int[m + 2];
        for (int k = 2; k <= m + 1; k++) logTable[k] = logTable[k / 2] + 1;

        List<Integer> result = new ArrayList<>();

        for (int[] q : queries) {
            int l = q[0], r = q[1];
            int bl = posToBlock[l];
            int br = posToBlock[r];

            int bestGain = 0;

            if (br >= bl + 2) {
                if (br == bl + 2) {
                    // only one possible B: k = bl+1, both flanks touch the query boundary
                    if (ch[bl] == 0) { // guarantees ch[bl+1] == 1
                        int leftClipped  = end[bl] - l + 1;
                        int rightClipped = r - start[br] + 1;
                        bestGain = Math.max(bestGain, leftClipped + rightClipped);
                    }
                } else {
                    // br >= bl + 3
                    // candidate: k = bl+1 (left flank clipped, right flank fully interior)
                    if (ch[bl] == 0) {
                        int leftClipped = end[bl] - l + 1;
                        int rightFull   = len[bl + 2];
                        bestGain = Math.max(bestGain, leftClipped + rightFull);
                    }
                    // candidate: k = br-1 (right flank clipped, left flank fully interior)
                    if (ch[br] == 0) {
                        int rightClipped = r - start[br] + 1;
                        int leftFull      = len[br - 2];
                        bestGain = Math.max(bestGain, leftFull + rightClipped);
                    }
                    // candidates fully interior on both sides: k in [bl+2, br-2]
                    if (br >= bl + 4) {
                        int lo = bl + 2, hi = br - 2;
                        int rangeLen = hi - lo + 1;
                        int p = logTable[rangeLen];
                        int maxMid = Math.max(sparse[p][lo], sparse[p][hi - (1 << p) + 1]);
                        if (maxMid > NEG / 2) bestGain = Math.max(bestGain, maxMid);
                    }
                }
            }

            result.add(wholeOnes + bestGain);
        }

        return result;
    }
}

//Time complexity: O(n + m log m + q log m), where n = s.length(), m = number of blocks, q = number of queries
//space complexity: O(n + m log m), for posToBlock, gainFull, sparse, logTable

//Algorithm: Explanation
//1. Decompose the input string s into maximal runs (blocks) of '0's and '1's, storing their start and end indices, character type, and length.
//2. Create an array posToBlock to map each position in s to its corresponding block index.
//3. Calculate the total number of '1's in the entire string (wholeOnes).
//4. For each block of '1's that is strictly interior (not touching the boundaries), calculate the potential gain if it were to be traded, and store these gains in the gainFull array.
//5. Build a sparse table for range maximum queries on the gainFull array to efficiently find the maximum gain for any range of blocks.
//6. For each query, determine the blocks that correspond to the query range and calculate the best possible gain by considering different scenarios (clipped flanks, fully interior blocks, etc.).
//7. Return the total number of '1's plus the best gain for each query