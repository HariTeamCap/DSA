class Solution {

    static final int MOD = 1000000007;

    public int[] pathsWithMaxScore(List<String> board) {

        int n = board.size();

        int[][] score = new int[n][n];
        int[][] ways = new int[n][n];

        for (int[] row : score)
            Arrays.fill(row, -1);

        score[0][0] = 0;
        ways[0][0] = 1;

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                if (board.get(i).charAt(j) == 'X')
                    continue;

                if (i == 0 && j == 0)
                    continue;

                int best = -1;
                long cnt = 0;

                int[][] dir = {{-1,0},{0,-1},{-1,-1}};

                for (int[] d : dir) {

                    int x = i + d[0];
                    int y = j + d[1];

                    if (x < 0 || y < 0)
                        continue;

                    if (score[x][y] == -1)
                        continue;

                    if (score[x][y] > best) {
                        best = score[x][y];
                        cnt = ways[x][y];
                    } else if (score[x][y] == best) {
                        cnt = (cnt + ways[x][y]) % MOD;
                    }
                }

                if (best == -1)
                    continue;

                char ch = board.get(i).charAt(j);

                int val = (ch == 'S' || ch == 'E') ? 0 : ch - '0';

                score[i][j] = best + val;
                ways[i][j] = (int)(cnt % MOD);
            }
        }

        if (ways[n-1][n-1] == 0)
            return new int[]{0,0};

        return new int[]{score[n-1][n-1], ways[n-1][n-1]};
    }
}


// Idea
// For every cell maintain:
// score[i][j] = maximum score to reach E from this cell.
// ways[i][j] = number of paths giving that maximum score.
// Since movement is only
// up
// left
// up-left
// it's easier to compute DP from top-left (E) towards bottom-right (S) by reversing the moves.
// So for every cell (i,j) we look at
// (i-1,j)
// (i,j-1)
// (i-1,j-1)
// These are exactly the cells from which we could have come.

// Time : O(n²)

// Space : O(n²)