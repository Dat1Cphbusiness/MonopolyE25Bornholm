package entities;

public class Player {
    private String name;
    private int cash;
    private int position;  // 1..40
    
    public Player(String name, int cash){
        this.name = name;
        this.cash = cash;
        this.position = 1;  // starting position
    }

    public int move(int diceRoll){
        position = position + diceRoll;
        if (position > 40) {
            cash = cash + 4000;
            position = position - 40;
        }
        return position;
    }

    public String getName() {
        return name;
    }

    public int getCash() {
        return cash;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "Player{" + "name='" + name + '\'' + ", cash=" + cash + ", position=" + position + '}';
    }
}
