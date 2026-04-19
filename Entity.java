public class Entity {
    protected int row, col;
    protected int hp;
    protected int attack;

    public Entity(int row, int col, int hp, int attack) {
        this.row = row;
        this.col = col;
        this.hp = hp;
        this.attack = attack;
    }

    public int getRow() { return row; }
    public int getCol() { return col; }
    public int getHp() { return hp; }
    public int getAttack() { return attack; }

    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public void takeDamage(int dmg) {
        this.hp -= dmg; 
    }

    public boolean isAlive() {
        return this.hp > 0;
    }

    public void saySomething() {
        System.out.println("Sesuatu bersiap untuk bertarung...");
    }
}