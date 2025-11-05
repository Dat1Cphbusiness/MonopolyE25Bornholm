package entities.spaces;

import entities.Deed;
import entities.Player;
import entities.Space;
import utils.TextUI;

import java.util.ArrayList;

public class Property extends Space {
    private String color;
    private int price;
    private Player owner;

    public Property(int id, String name, String type, String color, int price) {
        super(id, name, type);
        this.color = color;
        this.price = price;
        this.owner = null;
    }

    public Deed getDeed(ArrayList<Deed> deeds) {
        for (Deed deed : deeds) {
            if (deed.getNumber() == super.getId()) {
                return deed;
            }
        }
        return null;
    }

    @Override
    protected void act(Player player, ArrayList<Deed> deeds, TextUI ui) {

        if (this.owner == null) {
            ui.displayMsg("Du er landet på " + super.getName() + ". Den koster " + price + " kr. og har farven: " + color);
            String input = ui.promptText("Den kan købes. Vil du det (j/n)");
            if (input.equals("j")) {
                // buy
                ui.displayMsg("Du har købt grunden");
                // add property to owner
                player.addProperty(this);
                // Add player to property as owner
                this.owner = player;
                Deed deed = getDeed(deeds);
                ui.displayMsg("Skødet ser således ud: " + deed.toString());
            }
        } else {
            ui.displayMsg("Denne grund er allerede ejet af: " + owner.getName());
            int rent = getDeed(deeds).getBasicRent();
            ui.displayMsg("Det kommer til at koste dig nogle kroner i leje: " + rent);
            player.drawCash(rent);
        }

    }
}
