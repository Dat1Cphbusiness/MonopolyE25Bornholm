package entities.spaces;

import entities.Deed;
import entities.Player;
import entities.Space;
import utils.TextUI;

import java.util.ArrayList;

public class FreeParking extends Space {


    public FreeParking(int id, String name, String type){
        super(id,name,type);

    }

    @Override
    protected void act(Player player, ArrayList<Deed> deeds, TextUI ui) {
       ui.displayMsg("Du er landet på gratis parkering");
    }


}
