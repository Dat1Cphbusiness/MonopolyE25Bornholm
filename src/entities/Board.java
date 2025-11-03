package entities;

import java.util.ArrayList;

public class Board {
    private ArrayList<Space> spaces;

    public Board() {
        this.spaces = new ArrayList<>();
    }

    public void addSpace(Space space){
        this.spaces.add(space);
    }

    public ArrayList<Space> getSpaces() {
        return spaces;
    }

    @Override
    public String toString() {
        return "Board{" + "spaces=" + spaces + '}';
    }
}
