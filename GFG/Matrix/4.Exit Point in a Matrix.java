class Solution {
    public List<Integer> exitPoint(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;

        int row = 0, col = 0;
        int dir = 0; // 0=right, 1=down, 2=left, 3=up

        int[] dr = {0, 1, 0, -1};
        int[] dc = {1, 0, -1, 0};

        while (row >= 0 && row < n && col >= 0 && col < m) {

            if (mat[row][col] == 1) {
                dir = (dir + 1) % 4;
                mat[row][col] = 0;
            }

            row += dr[dir];
            col += dc[dir];
        }

        // step back to last valid cell
        row -= dr[dir];
        col -= dc[dir];

        return Arrays.asList(row, col);
    }
}