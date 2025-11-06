package factories;

import entities.*;
import entities.spaces.FreeParking;
import entities.spaces.Property;
import entities.spaces.Tax;
import utils.FileIO;
import utils.TextUI;

import java.util.ArrayList;
import java.util.Collections;

public class GameFactory {

    public GameFactory() {
    }

    // Read players from file or register player from console
    public void initializePlayers(FileIO io, TextUI ui, int START_CASH, int maxPlayers, ArrayList<Player> players) {
        ArrayList<String> data = io.readData("data/playerData.csv");
        if (!data.isEmpty()) {
            for (String s : data) {
                String[] values = s.split(",");//  "tess, 0
                String name = values[0];
                int cash = Integer.parseInt(values[1].trim());
                players.add(new Player(name, cash));
            }
        } else {
            registerPlayers(players, maxPlayers, ui, START_CASH);
        }
        Collections.shuffle(players);
    }

    public void registerPlayers( ArrayList<Player> players, int maxPlayers, TextUI ui, int START_CASH) {
        while (players.size() < maxPlayers) {
            String playerName = ui.promptText("Tast spiller navn");
            players.add(new Player(playerName, START_CASH));
        }
    }

    public void initializeBoard(FileIO io, TextUI ui, Board board) {
        ArrayList<String> boardData = io.readData("data/boardData.csv");

        if (!boardData.isEmpty()) {
            for (String s : boardData) {
                String[] values = s.split(",");  //  "2,Property,BabyBlue,Rødovrevej,1200,,,Jon"
                int id = Integer.parseInt(values[0].trim());
                String type = values[1].trim();

                switch (type) {
                    case "Property":
                        String color = values[2].trim();
                        String name = values[3].trim();
                        int price = Integer.parseInt(values[4].trim());
                        Space property = new Property(id, name, type, color, price);
                        board.addSpace(property);
                        break;
                    case "Tax":
                        name = values[3].trim();
                        String taxText = values[5].trim();
                        Space tax = new Tax(id, name, type, taxText);
                        board.addSpace(tax);
                        break;

                    case "Free parking":
                        name = values[3].trim();
                        Space freeParking = new FreeParking(id, name, type);
                        board.addSpace(freeParking);
                    default:
                        ui.displayMsg("type: " + type + " not implemented yet");
                }
            }
        }
    }

    public void initializeCards(FileIO io, ArrayList<ChanceCard> chanceCards) {
        // TODO: Skal kodes
        int amount = 0;
        ArrayList<String> chanceCardsData = io.readData("data/chanceCard.csv");
        if (!chanceCardsData.isEmpty()) {
            for (String s : chanceCardsData) {
                String[] values = s.split(",");//
                String type = values[0];

                if (!type.equals("MoveJail")) {
                    amount = Integer.parseInt(values[1].trim());

                } else
                    if (values[1].equals("To jail")) {
                        amount = 0;
                    }

                String instruction = values[2];
                chanceCards.add(new ChanceCard(type, amount, instruction));

            }
        }
        Collections.shuffle(chanceCards);
    }

    public void initializeDeeds(FileIO io, TextUI ui, ArrayList<Deed> deeds) {
        ArrayList<String> deedData = io.readData("data/deedData.csv");

        if (!deedData.isEmpty()) {
            for (String s : deedData) {
                if (s.startsWith("Number"))
                    continue;
                String[] values = s.split(",");//  "2, Property, rent,1,2,3

                int number = ui.getIntOrZero(values[0].trim());
                String type = values[1].trim();
                int basicRent = 0, rentTwo = 0, rentThree = 0, rentFour = 0, rentOne = 0, rentHotel = 0, housePrice = 0, hotelPrice = 0, mortgage = 0;
                if (values.length > 2) {
                    basicRent = ui.getIntOrZero(values[2].trim());
                    rentTwo = ui.getIntOrZero(values[4].trim());
                    rentThree = ui.getIntOrZero(values[5].trim());
                    rentFour = ui.getIntOrZero(values[6].trim());
                    mortgage = ui.getIntOrZero(values[10].trim());
                }

                switch (type) {
                    case "Property":

                        if (values.length > 3) {
                            rentOne = ui.getIntOrZero(values[3].trim());
                            rentHotel = ui.getIntOrZero(values[7].trim());
                            housePrice = ui.getIntOrZero(values[8].trim());
                            hotelPrice = ui.getIntOrZero(values[9].trim());
                            deeds.add(new Deed(number, type, basicRent, rentOne, rentTwo, rentThree, rentFour, rentHotel, housePrice, hotelPrice, mortgage));

                        }
                        break;

                    case "Shipping Company":
                        if (values.length > 1) {
                            deeds.add(new Deed(number, type, basicRent, rentTwo, rentThree, rentFour, mortgage));
                        }
                        break;

                    case "Brewery":
                        deeds.add(new Deed(mortgage));
                        break;
                }
            }
        }
    }

}
