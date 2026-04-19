import java.util.Random;

public class Maze {
    private char[][] grid;
    private final int rows = 12; 
    private final int cols = 15;
    private final Random rand = new Random();

    public Maze() {
        generateMaze();
    }

    private void generateMaze() {
        grid = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = '#';
            }
        }

        int r = 1, c = 1;
        grid[r][c] = '.';

        while (r < rows - 2 || c < cols - 2) {
            if (r == rows - 2) c++;
            else if (c == cols - 2) r++;
            else {
                if (rand.nextBoolean()) r++; else c++;
            }
            grid[r][c] = '.';
        }

        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < cols - 1; j++) {
                if (grid[i][j] == '#' && rand.nextDouble() < 0.6) {
                    grid[i][j] = '.';
                }
            }
        }
        // --- TAMBAHAN: JAMINAN JALAN KELUAR ---
        grid[rows - 2][cols - 3] = '.'; // Sel di sebelah kiri X
        grid[rows - 3][cols - 2] = '.'; // Sel di sebelah atas X

        // Tentukan titik keluar
        grid[rows - 2][cols - 2] = 'X';
        
        // PASTIKAN START BERSIH
        grid[1][1] = '.'; 

        int count = 0;
        while (count < 5) {
            int rr = rand.nextInt(rows - 2) + 1;
            int cc = rand.nextInt(cols - 2) + 1;
            
            if (grid[rr][cc] == '.' && (rr != 1 || cc != 1) && grid[rr][cc] != 'X') {
                grid[rr][cc] = 'H';
                count++;
            }
        }
    }
    public boolean isWalkable(int row, int col) {
        if (row < 0 || col < 0 || row >= rows || col >= cols) return false;
        return grid[row][col] != '#';
    }

    public char getCell(int row, int col) {
        if (row < 0 || col < 0 || row >= rows || col >= cols) return '#';
        return grid[row][col];
    }

    public void clearCell(int row, int col) {
        if (row >= 0 && col >= 0 && row < rows && col < cols) {
            grid[row][col] = '.';
        }
    }

    public int getRows() { return rows; }
    public int getCols() { return cols; }
}