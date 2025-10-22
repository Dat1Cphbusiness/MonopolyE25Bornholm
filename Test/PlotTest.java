import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlotTest {

    Game game;
    Player p;
    Player p2;
    @BeforeEach
    void setUp() {
        //Arrange
        game = new Game("Matador", 6);
        game.buildBoard();
        p = new Player("Egon", 30000);
        p2 = new Player("Benny", 30000000);


    }
    @Test
    void onLand_Buy() {
        //ARRANGE: få fat i Rådhuspladsen
        Plot  f = (Plot)game.board.getField(40);
        String msg = f.onLand(p);
        System.out.println(msg);

        //ACT: Egon lander
        String actual = f.getOption();
        String expected = "Buy";
        //ASSERT: Egon får købstilbud
        assertEquals(actual,expected);

    }

    @Test
    void onAccept_Buy(){
        //ARRANGE: få fat i Rådhuspladsen
        Plot  f = (Plot)game.board.getField(40);
        String msg = f.onLand(p);


        //ACT: Egon accepterer købstilbud
        f.onAccept(p);

        int actual = p.getBalance();
        int expected = 2000;

        assertEquals(expected, actual);
    }

    @Test
    void onLand_Build() {

        //Arrange
        Plot  f = (Plot)game.board.getField(40);//Rådhuspladsen
        Plot  f2 = (Plot)game.board.getField(38);//


        f.onLand(p);
        String msg = f.onAccept(p);
        System.out.println(msg);

        f2.onLand(p);
        msg = f2.onAccept(p);
        System.out.println(msg);

        //Act
        msg =f.onLand(p);
        System.out.println(msg);
        //Assert
        Boolean actual = f.isMonopolized;
        Boolean expected = true;
        assertEquals(expected,actual);




    }
}