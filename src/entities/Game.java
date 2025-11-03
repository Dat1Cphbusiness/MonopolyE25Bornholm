package entities;
import entities.spaces.Property;
import utils.FileIO;
import utils.TextUI;

import java.util.ArrayList;
import java.util.Collections;


public class Game {

    private String name;
    private int maxPlayers;
    private int currentPlayerIndex;
    private ArrayList<Player> players;
    private Board board;
    private Dice dice;
    TextUI ui = new TextUI();
    FileIO io = new FileIO();

    public Game(String name, int maxPlayers) {
        this.name = name;
        this.maxPlayers = maxPlayers;
        players = new ArrayList<>();
        board = new Board();
        dice = new Dice();
        currentPlayerIndex = 0;
    }

    public void setup() {

        // Read players from file or register player from console

        ArrayList<String> data = io.readData("data/playerData.csv");
        if (!data.isEmpty()) {
            for (String s : data) {
                String[] values = s.split(",");//  "tess, 0"
                int cash = Integer.parseInt(values[1].trim());
                createPlayer(values[0], cash);
            }

        } else {
            registerPlayers();
        }

        // Read board data from file

        ArrayList<String> boardData = io.readData("data/boardData.csv");

        if (!boardData.isEmpty()) {
            for (String s : boardData) {
                String[] values = s.split(",");  //  "2,Property,BabyBlue,Rødovrevej,1200,,,Jon"
                int id = Integer.parseInt(values[0].trim());
                String type = values[1].trim();

                switch (type){
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
        // Read chance cards from file

        // Read deeds from file


        // Shuffle players
        Collections.shuffle(players);
        displayPlayers();
        displayBoard();
    }

    public void gameLoop(){
        boolean continueGame = true;
        Player currentPlayer;
        int counter = 0;

        while (continueGame){

            // get currentPlayer
            currentPlayer = players.get(currentPlayerIndex);

            // roll
            int diceRoll = dice.roll();

            // move
            int newPosition = currentPlayer.move(diceRoll);

            // act
            ui.displayMsg(currentPlayer.getName() + " slag: " +
                    diceRoll + " Flyttet til: " + newPosition + " Cash: " + currentPlayer.getCash());

            // point to next player
            currentPlayerIndex = currentPlayerIndex + 1;
            if (currentPlayerIndex > players.size() - 1){
                currentPlayerIndex = 0;
            }

            counter++;

            if (counter > 100){
                continueGame = false;
            }

        }

    }

    public void registerPlayers() {
        while (this.players.size() < this.maxPlayers) {
            String playerName = ui.promptText("Tast spiller navn");
            this.createPlayer(playerName, 0);
        }
    }

    private void createPlayer(String name, int score) {
        Player p = new Player(name, score);
        players.add(p);
    }

    public void displayPlayers() {
        for (Player p : players) {
            System.out.println(p);
        }
    }

    public void displayBoard(){
        ui.displayMsg("***** Board: ********");
        for (Space s: board.getSpaces()){
            ui.displayMsg("Space: " + s.toString());
        }
    }

    public void endSession() {
        ArrayList<String> playerData = new ArrayList<>();

        //serialiserer player objekterne
        for (Player p : players) {
            String s = p.getName() + "," + p.getCash();
            playerData.add(s);
        }
        io.saveData(playerData, "data/playerData.csv", "Name, Score");
    }
}