package entities;

import java.util.Random;

public class Dice {
    private int firstDie;
    private int secondDie;
    private boolean pair;
    private Random randomizer;

    public Dice() {
        pair = false;
        randomizer = new Random();

    }

    public int roll(){
        firstDie = randomizer.nextInt(6) + 1;
        secondDie = randomizer.nextInt(6) + 1;
        pair = (firstDie == secondDie);
        return firstDie + secondDie;
    }

    public boolean pair(){
        return pair;
    }

    @Override
    public String toString() {
        return "Dice{" + "firstDie=" + firstDie + ", secondDie=" + secondDie + ", pair=" + pair + '}';
    }
}
