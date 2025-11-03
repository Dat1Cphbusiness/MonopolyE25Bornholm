package entities;

public class Property extends Space {
    private String color;
    private int price;

    public Property(int id, String name, String type, String color, int price) {
        super(id, name, type);
        this.color = color;
        this.price = price;
    }
}
