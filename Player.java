public class Player extends Entity {

    public Player(int row, int col) {
        super(row, col, 100, 20);
    }
    public void heal(int amount) {
        this.hp += amount;
    }

    @Override
    public void saySomething() {
        System.out.println("Pahlawan bersiap untuk bertualang!");
    }

}