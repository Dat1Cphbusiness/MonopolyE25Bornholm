package entities;
import utils.FileIO;
import utils.TextUI;

import java.util.ArrayList;


public class Game {

    private String name;
    private int maxPlayers;
    private ArrayList<Player> players;
    TextUI ui = new TextUI();
    FileIO io = new FileIO();

    public Game(String name, int maxPlayers) {
        this.name = name;
        this.maxPlayers = maxPlayers;
        players = new ArrayList<>();
    }

    public void startSession() {
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
        displayPlayers();
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

    public void endSession() {
        ArrayList<String> playerData = new ArrayList<>();

        //serialiserer player objekterne
        for (Player p : players) {
            String s = p.toString();
            playerData.add(s);
        }
        io.saveData(playerData, "data/playerData.csv", "Name, Score");
    }
}