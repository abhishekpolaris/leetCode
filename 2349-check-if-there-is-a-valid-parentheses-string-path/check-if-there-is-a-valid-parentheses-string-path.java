class Solution {
    private int rows, cols;
    private char[][] grid;
    private boolean[][][] hasvisited;

    public boolean hasValidPath(char[][] grid) {
        rows = grid.length;
        cols = grid[0].length;

        // quick invalid checks
        if ((rows + cols - 1) % 2 == 1 || grid[0][0] == ')' || grid[rows - 1][cols - 1] == '(') {
            return false;
        }

        this.grid = grid;
        hasvisited = new boolean[rows][cols][rows + cols + 1]; // initialize

        return dfs(0, 0, 0);
    }

    private boolean dfs(int row, int col, int balance) {
        if (hasvisited[row][col][balance]) return false;
        hasvisited[row][col][balance] = true;

        balance += (grid[row][col] == '(' ? 1 : -1);

        // pruning
        if (balance < 0 || balance > (rows - row - 1) + (cols - col - 1)) return false;

        if (row == rows - 1 && col == cols - 1) return balance == 0;

        int[][] dirs = {{1,0}, {0,1}};
        for (int[] dir : dirs) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];
            if (nextRow < rows && nextCol < cols && dfs(nextRow, nextCol, balance)) {
                return true;
            }
        }
        return false;
    }
}
