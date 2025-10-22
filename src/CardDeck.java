import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class CardDeck {

    private Card[] cards;
    private int counter;

    public CardDeck(String[] carddata) {
        counter = 0;
        this.cards = new Card[carddata.length];
        createCards(carddata);

    }

    public Card getNext() {
        Card c = cards[counter];
        counter++;
        return c;
    }

    public void createCards(String[] data) {
        for (int i = 0; i < data.length; i++) {
            String[] values = data[i].split(";");
            String message = values[0];
            int income = Integer.parseInt(values[1].trim());
            int cost = Integer.parseInt(values[2].trim());
            String event = values[3];

            Card c = new Card(message, income, cost, event);
            cards[i] = c;
        }
        Collections.shuffle(Arrays.asList(cards));
    }
}
