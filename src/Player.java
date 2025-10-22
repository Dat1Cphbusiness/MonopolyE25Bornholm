import java.util.ArrayList;

public class Player {
    private String name;
    private Account playerAccount;
    private boolean hasPassedStart;
    private int position = 1;
    protected ArrayList<Property> deeds = new ArrayList<>();
    
    public Player(String name, int balance){
        this.name = name;
        this.playerAccount = new Account(balance);


    }
    public int updatePostion(int value){
        if (position+value > 40){
            receive(4000);
            position = (position + value) - 40;
        }else{
            position += value;
        }

        return this.position;

    }

    public void receive (int amount){
        playerAccount.deposit(amount);
    }

    public boolean pay(int amount){

        return playerAccount.withdraw(amount);

    }

    public boolean pay(int amount, Player recipient){
        recipient.receive(amount);
        return this.pay(amount);
    }

    public boolean buyProperty(Property p){
        if(this.pay(p.getCost())){
            deeds.add(p);
            return true;
        }
        return false;
    }

    public int getTotalWorth(){
        int totalDeedWorth = 0;

        for(Property p: deeds){
            totalDeedWorth += p.getCost();
        }
        int total = totalDeedWorth + playerAccount.getBalance();

        return total;
    }


    @Override
    public String toString(){
        return name + ", "+ playerAccount.getBalance();
    }

    public String getName(){
        return name;
    }

    public int getBalance() {
        return this.playerAccount.getBalance();
    }
}