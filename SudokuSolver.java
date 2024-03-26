public class SudokuSolver {

    // Size of the Sudoku grid (9x9)
    private static final int N = 9;

    // Helper function to check if a number can be placed in a given cell
    private static boolean isValid(int[][] board, int row, int col, int num) {
        // Check row and column
        for (int i = 0; i < N; i++) {
            if (board[row][i] == num || board[i][col] == num) {
                return false;
            }
        }

        // Check 3x3 subgrid
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    // Recursive function to solve the Sudoku puzzle
    private static boolean solveSudoku(int[][] board) {
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (board[row][col] == 0) { // Empty cell
                    for (int num = 1; num <= 9; num++) {
                        if (isValid(board, row, col, num)) {
                            board[row][col] = num;
                            if (solveSudoku(board)) {
                                return true;
                            }
                            board[row][col] = 0; // Backtrack
                        }
                    }
                    return false; // No valid number found
                }
            }
        }
        return true; // All cells filled
    }

    // Main method to test the solver
    public static void main(String[] args) {
        int[][] puzzle = {
            // Your unsolved Sudoku puzzle (0 represents empty cells)
            // Example:
            {3, 0, 6, 5, 0, 8, 4, 0, 0},
            {5, 2, 0, 0, 0, 0, 0, 0, 0},
            {0, 8, 7, 0, 0, 0, 0, 3, 1},
            {0, 0, 3, 0, 1, 0, 0, 8, 0},
            {9, 0, 0, 8, 6, 3, 0, 0, 5},
            {0, 5, 0, 0, 9, 0, 6, 0, 0},
            {1, 3, 0, 0, 0, 0, 2, 5, 0},
            {0, 0, 0, 0, 0, 0, 0, 7, 4},
            {0, 0, 5, 2, 0, 6, 3, 0, 0}
        };

        if (solveSudoku(puzzle)) {
            // Print the solved Sudoku grid
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    System.out.print(puzzle[i][j] + " ");
                }
                System.out.println();
            }
        } else {
            System.out.println("No solution exists for the given puzzle.");
        }
    }
}
