package entities.spaces;

import entities.Deed;
import entities.Player;
import entities.Space;
import utils.TextUI;

import java.util.ArrayList;

public class Tax extends Space {

    private String taxText;

    public Tax(int id, String name, String type, String taxText) {
        super(id, name, type);
        this.taxText = taxText;
    }

    @Override
    protected void act(Player player, ArrayList<Deed> deeds, TextUI ui) {
        // TODO: Skal kodes, så der trækkes et beløb i afgift
        if (super.getId() == 5){
            ArrayList<String> taxAlternatives = new ArrayList<>();
            ArrayList<String> result = new ArrayList<>();
            taxAlternatives.add("Betal 4000 bobs");
            taxAlternatives.add("10% af min formue (" + player.getCash() * 0.1 + " kr.)" );
            result = ui.promptChoice(taxAlternatives, 1, "Hvad vil du betale?");
            ui.displayMsg("Du har valgt: " + result.get(0));
            if (result.get(0).equals("Betal 4000 bobs")){
                player.drawCash(4000);
            } else {
                player.drawCash((int) (player.getCash() * 0.1));
            }
        }

    }
}
