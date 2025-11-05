package entities;

import entities.spaces.Property;
import utils.FileIO;
import utils.TextUI;

import java.util.ArrayList;
import java.util.Collections;


public class Game {

    private final int START_CASH = 30000;

    private String name;
    private int maxPlayers;
    private int currentPlayerIndex;
    Player currentPlayer;
    private ArrayList<Player> players;
    private ArrayList<Deed> deeds;
    private ArrayList<ChanceCard> chanceCards;
    private Board board;
    private Dice dice;
    TextUI ui = new TextUI();
    FileIO io = new FileIO();

    public Game(String name, int maxPlayers) {
        this.name = name;
        this.maxPlayers = maxPlayers;
        players = new ArrayList<>();
        deeds = new ArrayList<>();
        chanceCards = new ArrayList<>();
        board = new Board();
        dice = new Dice();
        currentPlayerIndex = 0;
    }

    public void setup() {

        initializePlayers();
        initializeBoard();
        initializeCards();
        initializeDeeds();

        displayPlayers();
        displayBoard();

    }

    private void initializeDeeds() {
        // TODO: Skal kodes
    }

    private void initializeCards() {
        // TODO: Skal kodes
        ArrayList<String> chanceCardsData = io.readData("data/chanceCard.csv");
        if (!chanceCardsData.isEmpty()) {
            for (String s : chanceCardsData) {
                String[] values = s.split(",");//
                String type = values[0];
                if (type != "MoveJail") {
                    int amount = Integer.parseInt(values[1].trim());

                    String instruction = values[2];
                    chanceCards.add(new ChanceCard(type, amount, instruction));
                }
            }
        }
    }
    private void showGameMenu(){
        ArrayList<String> menuItems = new ArrayList<>();
        menuItems.add("1. Rul");


    }

    public void gameLoop() {
        boolean continueGame = true;
        int newPosition;

        int counter = 0;

        while (continueGame) {

            // get currentPlayer
            currentPlayer = players.get(currentPlayerIndex);

            // roll
            int diceRoll = dice.roll();

            // Move and check for double eyes

            if (dice.pair()) {
                currentPlayer.increaseStreak();
                ui.displayMsg(currentPlayer.getName() + ": " + dice.toString());
                if (currentPlayer.getStreak() > 2) {
                    newPosition = 11;  // Jail
                    ui.displayMsg("Ryk i spjældet, staks");
                    nextPlayer();
                } else {
                    newPosition = currentPlayer.move(diceRoll);
                }
            } else {
                newPosition = currentPlayer.move(diceRoll);
                nextPlayer();
            }

            ui.displayMsg(currentPlayer.getName() + " slag: " + diceRoll + " Flyttet til: " + newPosition + " Cash: " + currentPlayer.getCash());

            // TODO: Act on the player just landet on space nr. newPosition


            // TODO: Remove this counter stuff below when we're done and want to manually play
            counter++;
            if (counter > 50) {
                System.out.println(counter + " runder. Forlader game-loop nu.");
                continueGame = false;
            }
        }
    }

    private void nextPlayer() {
        currentPlayer.resetStreak();
        currentPlayerIndex = currentPlayerIndex + 1;
        if (currentPlayerIndex > players.size() - 1) {
            currentPlayerIndex = 0;
        }
    }

    // Read players from file or register player from console
    private void initializePlayers() {
        ArrayList<String> data = io.readData("data/playerData.csv");
        if (!data.isEmpty()) {
            for (String s : data) {
                String[] values = s.split(",");//  "tess, 0
                String name = values[0];
                int cash = Integer.parseInt(values[1].trim());
                players.add(new Player(name, cash));
            }
        } else {
            registerPlayers();
        }
        Collections.shuffle(players);
    }

    private void initializeBoard() {
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
                    default:
                        ui.displayMsg("type: " + type + " not implemented yet");
                }
            }
        }

    }

    public void registerPlayers() {
        while (this.players.size() < this.maxPlayers) {
            String playerName = ui.promptText("Tast spiller navn");
            players.add(new Player(name, START_CASH));
        }
    }

    public void displayPlayers() {
        ui.displayMsg("***** Players: ********");
        for (Player player : players) {
            ui.displayMsg(player.toString());
        }
    }

    public void displayBoard() {
        ui.displayMsg("***** Board: ********");
        for (Space s : board.getSpaces()) {
            ui.displayMsg("Space: " + s.toString());
        }
    }

    public void endSession() {
        ArrayList<String> playerData = new ArrayList<>();
        ui.displayMsg("***** Saving players ********");
        //serialiserer player objekterne
        for (Player p : players) {
            String s = p.getName() + "," + p.getCash();
            playerData.add(s);
        }
        io.saveData(playerData, "data/playerData.csv", "Name, Score");
        ui.displayMsg("***** Game saved and stopped for now ********");
    }
}