import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Game {
    private Player player;
    private Maze maze;
    private ArrayList<Enemy> enemies; 
    private Renderer renderer;
    private BattleSystem battleSystem;
    private boolean justEscaped = false;
    private int enemyCount;

    public Game(int enemyCount) {
        this.enemyCount = enemyCount;
        this.player = new Player(1, 1);
        this.maze = new Maze();
        this.enemies = new ArrayList<>();
        this.renderer = new Renderer();
        this.battleSystem = new BattleSystem();
        spawnEnemies();
    }

    private void spawnEnemies() {
    Random rand = new Random();
    int spawned = 0;
    int attempts = 0;

    while (spawned < enemyCount && attempts < 500) {
        int r = rand.nextInt(maze.getRows());
        int c = rand.nextInt(maze.getCols());
        int distanceFromPlayer = Math.abs(r - 1) + Math.abs(c - 1);
        
        if (maze.isWalkable(r, c) && distanceFromPlayer > 3 && 
            maze.getCell(r, c) != 'X' && !isOccupied(r, c)) {
            
            enemies.add(new Enemy(r, c));
            spawned++;
        }
        attempts++;
    }
}
    public boolean start() {
        Scanner input = new Scanner(System.in);

        while (player.isAlive()) {
            // --- 1. LOGIKA CEK ITEM (Lakukan SEBELUM Render) ---
            // Ini memastikan data Maze bersih dari 'H' sebelum Renderer menggambar
            char currentTile = maze.getCell(player.getRow(), player.getCol());
            if (currentTile == 'H') {
                player.heal(20);
                maze.clearCell(player.getRow(), player.getCol());
                System.out.println("\n[ ITEM ] Kamu mendapatkan bonus HP +20 poin!");
            } 
            else if (currentTile == 'X') {
                renderer.printMaze(maze, player, enemies);
                System.out.println("\nSELAMAT! KAMU MENANG!");
                return true;
            }
            // --- 2. RENDER (Menampilkan Data Terkini) ---
            System.out.println("\n[ STATUS HP: " + player.getHp() + " ]");
            renderer.printMaze(maze, player, enemies);
            // --- 3. INPUT GERAKAN ---
            System.out.print("Gerak (w/a/s/d): ");
            String move = input.nextLine().toLowerCase();
            
            int nr = player.getRow();
            int nc = player.getCol();

            if (move.equals("w")) nr--;
            else if (move.equals("s")) nr++;
            else if (move.equals("a")) nc--;
            else if (move.equals("d")) nc++;

            // --- 4. VALIDASI POSISI ---
            if (maze.isWalkable(nr, nc)) {
                Enemy target = getEnemyAt(nr, nc);
                if (target != null) {
                    if (battleSystem.startBattle(player, target, input)) {
                        enemies.remove(target);
                        player.setPosition(nr, nc);
                    } else {
                        justEscaped = true;
                    }
                } else {
                    player.setPosition(nr, nc);
                }
            }

            // --- 5. PERGERAKAN MUSUH ---
            if (!justEscaped) {
                moveEnemies();
            } else {
                justEscaped = false;
            }
        }
        
        System.out.println("GAME OVER! KAMU KALAH.");
        return false;
    }

    private Enemy getEnemyAt(int r, int c) {
        for (Enemy e : enemies) {
            if (e.getRow() == r && e.getCol() == c) return e;
        }
        return null;
    }

    private boolean isOccupied(int r, int c) {
        if (player.getRow() == r && player.getCol() == c) return true;
        for (Enemy e : enemies) {
            if (e.getRow() == r && e.getCol() == c) return true;
        }
        return false;
    }

    private void moveEnemies() {
    for (Enemy e : enemies) {
        int er = e.getRow(), ec = e.getCol();
        int pr = player.getRow(), pc = player.getCol();
        
        int dr = (pr > er) ? 1 : (pr < er ? -1 : 0);
        int dc = (pc > ec) ? 1 : (pc < ec ? -1 : 0);

        if (dr != 0 && maze.isWalkable(er + dr, ec) && !isOccupied(er + dr, ec) 
            && maze.getCell(er + dr, ec) != 'H' && maze.getCell(er + dr, ec) != 'X') {
            
            e.setPosition(er + dr, ec);
        } 
        else if (dc != 0 && maze.isWalkable(er, ec + dc) && !isOccupied(er, ec + dc) 
                 && maze.getCell(er, ec + dc) != 'H' && maze.getCell(er, ec + dc) != 'X') {
            
            e.setPosition(er, ec + dc);
        }
    }
}
}