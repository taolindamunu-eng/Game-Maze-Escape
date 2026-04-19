import java.util.ArrayList;

public class Renderer {
    public void printMaze(Maze maze, Player player, ArrayList<Enemy> enemies) {
        for (int i = 0; i < maze.getRows(); i++) {
            for (int j = 0; j < maze.getCols(); j++) {
                
                // 1. PRIORITAS TERTINGGI: Player
                if (i == player.getRow() && j == player.getCol()) {
                    System.out.print("P ");
                } 
                
                // 2. PRIORITAS KEDUA: Musuh
                else if (getEnemyAt(i, j, enemies) != null) {
                    System.out.print("E ");
                } 
                
                // 3. PRIORITAS TERAKHIR: Objek Maze
                else {
                    System.out.print(maze.getCell(i, j) + " ");
                }
            }
            System.out.println();
        }
    }

    private Entity getEnemyAt(int r, int c, ArrayList<Enemy> enemies) {
        for (Enemy e : enemies) {
            if (e.getRow() == r && e.getCol() == c) {
                return e;
            }
        }
        return null;
    }
}