import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    Game game;
    Player p;
    Board b;
    @BeforeEach
    void setUp() {

    }

    @Test
    void getField() {

        //ARRANGE
        game = new Game("testgame", 6);
        p = new Player("Egon", 10000);
        game.buildBoard();

        //ACT

        Field f = game.board.getField(5);

        //ASSERT

        String actual = f.toString();
        String expected ="5, Skat";
        assertEquals(expected,actual);


    }
}