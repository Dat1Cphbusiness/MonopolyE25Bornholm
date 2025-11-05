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

    public Space getSpaceById(int id){
       for (Space s : spaces){
           if (s.getId() == id)
               return s;
       }
       return null;  // in case there is no space with the given id
    }

    public ArrayList<Space> getSpaces() {
        return spaces;
    }

    @Override
    public String toString() {
        return "Board{" + "spaces=" + spaces + '}';
    }
}
