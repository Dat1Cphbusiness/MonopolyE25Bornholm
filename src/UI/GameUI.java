package UI;

import entities.Board;
import entities.Player;
import entities.Space;
import utils.TextUI;

import java.util.ArrayList;

public class GameUI {

    public GameUI() {
    }

    public void displayPlayers(TextUI ui, ArrayList<Player> players) {
        ui.displayMsg("***** Players: ********");
        for (Player player : players) {
            ui.displayMsg(player.toString());
        }
    }

    public void displayBoard(TextUI ui, Board board) {
        ui.displayMsg("***** Board: ********");
        for (Space s : board.getSpaces()) {
            ui.displayMsg("Space: " + s.toString());
        }
    }

    public void showGameMenu(TextUI ui, ArrayList<Player> players) {
        ArrayList<String> menuItems = new ArrayList<>();
        ArrayList<String> result = new ArrayList<>();
        menuItems.add("Rul");
        menuItems.add("Se spillerinfo");
        menuItems.add("Se brættet");
        menuItems.add("Afslut spil");
        result = ui.promptChoice(menuItems, 1, "Hvad vil du nu?");
        String choice = result.get(0);
        ui.displayMsg("Du har valgt: " + choice);

        switch (choice) {
            case "Se spillerinfo":
                displayPlayers(ui, players);
                break;
            default:
                break;
        }
    }

}
