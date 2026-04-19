import java.util.Scanner;

public class BattleSystem {
    public void displayStatus(Entity e, String label) {
        System.out.println(label + " HP: " + e.getHp() + " | Attack: " + e.getAttack());
    }

    public boolean startBattle(Player player, Enemy enemy, Scanner input) {
        System.out.println("\n--- PERTEMPURAN DIMULAI ---");
        
        player.saySomething();
        enemy.saySomething();
        
        while (player.isAlive() && enemy.isAlive()) {
            System.out.println("-----------------------------------");
            displayStatus(player, "Player");
            displayStatus(enemy, "Musuh");
            
            System.out.print("1. Serang\n2. Lari\nPilih: ");
            String choice = input.nextLine();

            if (choice.equals("1")) {
                int pAtk = player.getAttack();
                enemy.takeDamage(pAtk);
                System.out.println(">> Kamu menyerang sebesar " + pAtk + " poin!");
                
                if (enemy.isAlive()) {
                    int eAtk = enemy.getAttack();
                    player.takeDamage(eAtk);
                    System.out.println(">> Musuh membalas sebesar " + eAtk + " poin!");
                } else {
                    System.out.println("\n[!] Musuh dikalahkan! Kamu mendapat bonus +15 HP.");
                    player.heal(15);
                    return true;
                }
            } else if (choice.equals("2")) {
                System.out.println("Kamu melarikan diri dari pertempuran!");
                return false;
            }
        }
        return false;
    }
}