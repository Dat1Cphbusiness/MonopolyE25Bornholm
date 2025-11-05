package entities;

import utils.TextUI;

import java.util.ArrayList;

public abstract class Space {
    private int id;
    private String name;
    private String type;

    public Space(int id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    protected abstract void act(Player player, ArrayList<Deed> deeds, TextUI ui);

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Space{" + "id=" + id + ", name='" + name + '\'' + ", type='" + type + '\'' + '}';
    }
}
