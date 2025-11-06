package entities;

import entities.spaces.Property;

import java.util.ArrayList;

public class Player {
    private String name;
    private int cash;
    private int lastRoll;
    private int position;  // 1..40
    private int streak;
    private ArrayList<Property> properties;
    private ArrayList<Space> ownedSpaces = new ArrayList<>();
    
    public Player(String name, int cash){
        this.name = name;
        this.cash = cash;
        this.position = 1;  // starting position
        this.streak = 0;
        properties = new ArrayList<>();
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

    public void addCash(int amount){
        cash = cash + amount;
    }

    public int getPosition() {
        return position;
    }

    public void addProperty(Space space) {
        ownedSpaces.add(space);
    }

    public int getStreak() {
        return streak;
    }

    public void increaseStreak(){
        streak++;
    }

    public void resetStreak(){
        streak = 0;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int addProperty(Property property){
        properties.add(property);
        return properties.size();
    }



    public int drawCash(int amount){
        cash = cash - amount;
        return cash;
    }

    public void setLastRoll(int roll){
        this.lastRoll = roll;
    }

    public int getLastRoll(){
        return lastRoll;
    }


    @Override
    public String toString() {
        return "Player{" + "name='" + name + '\'' + ", cash=" + cash + ", position=" + position + ", streak=" + streak + ", properties=" + properties + '}';
    }
}
