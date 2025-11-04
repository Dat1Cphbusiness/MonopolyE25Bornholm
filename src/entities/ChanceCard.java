package entities;

public class ChanceCard {

    private String type;
    private int amount;
    private String instruction;

    public ChanceCard(String type, int amount, String instruction) {
        this.type = type;
        this.amount = amount;
        this.instruction = instruction;
    }

    public String getType(){
        return type;
    }
    public int getAmount(){
        return amount;
    }
    public String getInstruction(){
        return instruction;
    }

    @Override
    public String toString() {
        return "ChanceCard{" +
                "type='" + type + '\'' +
                ", amount=" + amount +
                ", instruction='" + instruction + '\'' +
                '}';
    }

    }


