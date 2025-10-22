public class Card {
    private String message;
    private int income;
    private int cost;
    private String event;

    public Card(String message, int income, int cost, String event) {
        this.message = message;
        this.income = income;
        this.cost = cost;
        this.event = event;
    }

    public String getMessage() {
        return message;
    }

    public int getIncome() {
        return income;
    }

    public String getEvent() {
        return event;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public String toString() {
        String s = this.message + " " + this.income + " " + this.cost + " " + this.event;

        return s;
    }
}
