import java.util.ArrayList;
import java.util.Random;

public class Dice {
    Random random = new Random();
    int[] dice = new int[2];
    private boolean isDouble = false;

    public int rollDiceSum(){
        dice[0] = 3;//random.nextInt(1,7);
        dice[1] = 3;// random.nextInt(1,7);

        if (dice[0] == dice[1]){
            isDouble = true;
        } else {
            isDouble = false;
        }

        return dice[0] + dice[1];
    }

    public int getDice(int i){
        return dice[i];

    }

    public boolean getIsDouble() {
        return isDouble;
    }

    public void setIsDouble(boolean isDouble) {
        this.isDouble = isDouble;
    }


}
