package entities;

public abstract class Space {
    private int id;
    private String name;
    private String type;

    public Space(int id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Space{" + "id=" + id + ", name='" + name + '\'' + ", type='" + type + '\'' + '}';
    }
}
