import util.FileIO;
import util.TextUI;

import java.util.ArrayList;
import java.util.Collections;


public class Game {
    static TextUI ui = new TextUI();
    static FileIO io = new FileIO();

    Dice dice = new Dice();
    private String name;
    private int maxPlayers;
    private ArrayList<Player> players;
    private Player currentPlayer;
    private String cardDataPath = "data/carddata.csv";
    private String fieldDataPath = "data/fielddata.csv";
    public Board board;
    public int doubleDiceCounter = 0;
    int count = 0;

    public Game(String name, int maxPlayers) {
        this.name = name;
        this.maxPlayers = maxPlayers;
        players = new ArrayList<>();
    }


    public void startSession() {
        ArrayList<String> data = io.readData("data/playerData.csv");
        ui.displayMessage("Velkommen til " + this.name);

        if (!data.isEmpty() && ui.promptBinary("Gammel data fundet, vil du fortsætte herfra?: Y/N")) {
            for (String s : data) {
                String[] values = s.split(",");//  "tess, 0"
                int score = Integer.parseInt(values[1].trim());
                createPlayer(values[0], score);
            }

        } else {

            registerPlayers();
        }
        displayPlayers();
        //BUILD THE BOARD
        buildBoard();
    }

    public void buildBoard() {

        String[] fielddata = io.readData("data/fielddata.csv", 40);
        String[] carddata = io.readData("data/carddata.csv", 44);
        board = new Board(fielddata, carddata);
        Field f = board.getField(11);

    }

    public void registerPlayers() {

        int totalPlayers = 0;

        totalPlayers = ui.promptNumeric("Tast antal spillere:");       //Konvertere svaret til et tal

        if (totalPlayers > this.maxPlayers || totalPlayers < 2) { // vi kan evt. udvide promptNumeric med parametre til og min og max værdier (eller bruge overloading)
            ui.displayMessage("Antal spillere skal være mindst 2 og højest " + this.maxPlayers);
            registerPlayers();
            return;
        }

        while (this.players.size() < totalPlayers) {
            String playerName = ui.promptText("Tast spiller navn");
            this.createPlayer(playerName, 30000);
        }

        Collections.shuffle(this.players);
    }

    public void runGameLoop() {

        boolean continueGame = true;

        while (continueGame) {
            if (count == players.size()) {
                count = 0;
            }
            currentPlayer = players.get(count);
            this.throwAndMove();
            count++;
            continueGame = ui.promptBinary("Fortsæt? (Y/N): ");
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


    public void endSession() {
        ArrayList<String> playerData = new ArrayList<>();

        //serialiserer player objekterner
        for (Player p : players) {

            String s = p.toString();
            playerData.add(s);

        }
        //Test om promptChoice virker
        //ui.displayList(ui.promptChoice(playerData, 3, "vælg en spiller"), "Din spiller liste");
        io.saveData(playerData, "data/playerData.csv", "Name, Score");
        ui.displayMessage("Spillet er gemt og afsluttet.");
    }

    private void throwAndMove() {
        ui.displayMessage("Det er: " + currentPlayer.toString() + " tur");
        int result = dice.rollDiceSum();
        ui.displayMessage(currentPlayer.getName() + " slog " + result);
        int newPosition = currentPlayer.updatePostion(result);
        Field f = board.getField(newPosition);

        if(dice.getIsDouble()){
            doubleDiceCounter++;
            if(doubleDiceCounter < 3){
                ui.displayMessage("Du slog to ens, du har fået en ekstra tur");
                count--;
            } else {
                ui.displayMessage("Du slog 2 ens igen!");
                doubleDiceCounter = 0;
                f = board.getField(31);
            }
        } else {
            doubleDiceCounter = 0;
        }


        landAndAct(f);


        //after land and act, check if dice was double, call recursively

    }

    private void landAndAct(Field f) {
        String msg = f.onLand(currentPlayer);
        boolean response = true;

        if (f.getOption() != null) {
            response = ui.promptBinary(msg);
            if (response) {
                msg = f.onAccept(currentPlayer);
            } else {
                msg = f.onReject(currentPlayer);
            }
        }
            ui.displayMessage(msg);




    }

}
