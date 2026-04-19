public class Main {
    public static void main(String[] args) {
        int currentEnemyCount = 3; // Mulai dari 3
        boolean keepPlaying = true;
        java.util.Scanner sc = new java.util.Scanner(System.in);

        while (keepPlaying) {
            Game game = new Game(currentEnemyCount);
            boolean isVictory = game.start();

            if (isVictory) {
                currentEnemyCount += 2;
                if (currentEnemyCount > 12) currentEnemyCount = 12; // Batas Maksimal
            }

            System.out.print("Main lagi? (y/n): ");
            String choice = sc.nextLine();
            if (!choice.equalsIgnoreCase("y")) keepPlaying = false;
        }
    }
}