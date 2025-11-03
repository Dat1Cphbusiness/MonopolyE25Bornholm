/*
 * Vi startede dette program op for at lære om composition:
 * Vi lavede et simpelt klassediagram med to klasser der viser at
 * entities.Game har en eller flere Players.
 *
 * entities.Player instanser bliver oprettet via en dialog med brugeren
 * Det indtastede bliver gemt i en csv fil, når programmet slutter
 * Når programmet starter op igen bliver samme data anvendt til at oprette entities.Player instanser, så man ikke skal taste dem igen.
 *
 * */

import entities.Game;

public class Main {

    public static void main(String[] args) {

        Game monopoly = new Game("Monopoly", 3);
        monopoly.startSession();

        // missing here is the logic of the game

        monopoly.endSession();
    }
}