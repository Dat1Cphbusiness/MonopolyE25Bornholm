package entities.spaces;
import entities.Deed;
import entities.Player;
import entities.Space;
import utils.TextUI;

import java.util.ArrayList;

public class Brewery extends Space {

    private int price;
    private Player owner;

    public Brewery(int id, String name, String type, int price) {
        super(id, name, type);
        this.price = price;
        this.owner = null;

    }

    protected void act(Player player, ArrayList<Deed> deeds, TextUI ui) {

        if (owner == null) {
            ui.displayMsg("Du er landet på " + getName() + ". den koster " + price + " kr.");
            String input = ui.promptText("Vil du købe den? (j/n)");
            if (input.equalsIgnoreCase("j")) {
                if (player.getCash() >= price) {
                    player.addProperty(this);
                    player.drawCash(price);
                    this.owner = player;
                    ui.displayMsg("Du har købt bryggeriet " + getName());
                } else {
                    ui.displayMsg("Du har ikke råd til at købe" + getName());
                }
            }
                return;
            }
            if (owner != player) {
                int diceRoll = player.getLastRoll();
                int breweriesOwned = countBreweries(owner, deeds);

                int multiplier = (breweriesOwned == 2) ? 200 : 100;
                int rent = diceRoll * multiplier;

                ui.displayMsg((owner.getName() + " ejer " + breweriesOwned) + " Bryggerier, så du skal betale " + rent + " kr. (" + diceRoll + " x " + multiplier + ")");
                player.drawCash(rent);
                owner.addCash(rent);
            } else {
                ui.displayMsg("Du ejer selv dette bryggeri.");
            }

        }

        private int countBreweries (Player owner, ArrayList < Deed > deeds){
            int count = 0;
            for (Deed deed : deeds) {
                if (deed.getOwner() == owner && deed.getType().equalsIgnoreCase("Brewery")) {
                    count++;
                }
            }
            return count;
        }
        public Player getOwner () {
            return owner;
        }
        public int getPrice () {
            return price;
        }
    }



