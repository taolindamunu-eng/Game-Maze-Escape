public class Enemy extends Entity {
    public Enemy(int row, int col) {
        super(row, col, 50, 10);
    }

    @Override
    public void saySomething() {
        System.out.println("Musuh menggeram bersiap menyerang!");
    }

}