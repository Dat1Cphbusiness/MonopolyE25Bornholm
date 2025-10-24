/*
 * Vi startede dette program op for at lære om composition:
 * Vi lavede et simpelt klassediagram med to klasser der viser at
 * Game har en eller flere Players.
 *
 * Player instanser bliver oprettet via en dialog med brugeren
 * Det indtastede bliver gemt i en csv fil, når programmet slutter
 * Når programmet starter op igen bliver samme data anvendt til at oprette Player instanser, så man ikke skal taste dem igen.
 *
 * */

public class Main {

    public static void main(String[] args) {

        Game g = new Game("Yatzy",3);
        g.startSession();

        // missing here is the logic of the game

        g.endSession();
    }
}