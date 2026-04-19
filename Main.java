public class Main {
    public static void main(String[] args) {
        int currentEnemyCount = 3; // Mulai dari 3
        boolean keepPlaying = true;
        java.util.Scanner sc = new java.util.Scanner(System.in);

        while (keepPlaying) {
            Game game = new Game(currentEnemyCount);
            boolean isVictory = game.start();

            if (isVictory) {
                if (currentEnemyCount >= 15) {
                    System.out.println("SELAMAT! KAMU TELAH MENAMATKAN GAME INI!");
                    System.out.println("Tantangan 15 musuh telah ditaklukkan.");
                    break; // Langsung keluar dari loop while (game selesai)
                }

                currentEnemyCount += 3;
                // Memastikan batas tidak melampaui 12
                if (currentEnemyCount > 15) currentEnemyCount = 15;
                
                System.out.println("\nLevel Berhasil! Musuh bertambah menjadi: " + currentEnemyCount);
            }

            // Jika kalah atau belum tamat, tanya mau main lagi atau tidak
            System.out.print("Main lagi? (y/n): ");
            String choice = sc.nextLine();
            if (!choice.equalsIgnoreCase("y")) keepPlaying = false;
        }
        
        System.out.println("Sampai jumpa di petualangan berikutnya!");
        sc.close();
    }
}