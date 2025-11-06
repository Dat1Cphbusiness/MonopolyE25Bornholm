package entities;

import UI.GameUI;
import factories.GameFactory;
import utils.FileIO;
import utils.TextUI;
import java.util.ArrayList;

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
    GameFactory gameFactory = new GameFactory();
    GameUI gameUI = new GameUI();

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
        gameFactory.initializePlayers(io, ui, START_CASH, maxPlayers, players);
        gameFactory.initializeBoard(io, ui, board);
        gameFactory.initializeCards(io, chanceCards);
        gameFactory.initializeDeeds(io, ui, deeds);
        gameUI.displayPlayers(ui, players);
        gameUI.displayBoard(ui, board);
    }

    public void gameLoop() {
        boolean continueGame = true;
        int newPosition;

        int counter = 0;

        while (continueGame) {
            currentPlayer = players.get(currentPlayerIndex);
            int diceRoll = dice.roll();
                diceRoll = 20;
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
            currentPlayer.setPosition(newPosition);

            // TODO: Act on the player just landed on space nr. newPosition

            Space spaceToActOn = board.getSpaceById(newPosition);
            if (spaceToActOn != null) {
                spaceToActOn.act(currentPlayer, deeds, ui);
            } else {
                ui.displayMsg("Vi har ikke implementeret denne felttype endnu");
            }

            gameUI.showGameMenu(ui, players);

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