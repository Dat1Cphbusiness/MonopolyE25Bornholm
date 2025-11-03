package entities;

public class Player {
    private String name;
    private int cash;
    
    public Player(String name, int cash){
        this.name = name;
        this.cash = cash;
    }

    @Override
    public String toString(){
        return name + ", "+ cash;
    }


}
