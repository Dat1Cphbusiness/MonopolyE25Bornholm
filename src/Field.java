public class Field {

    private String label;
    private int ID;
    private int cost;
    private int income;
    private String option;



    public Field(int ID, String label, int cost, int income){
        this.ID = ID;
        this.label = label;
        this.cost = cost;
        this.income = income;
    }

    @Override
    public String toString() {
        return this.ID + ", "  + this.label;
    }

    public String onLand(Player p){
       return p + " er landet på felt " + this.toString();
    }

    public String onAccept(Player p){
        return null;
    }

    public String onReject(Player p){
        return null;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option){

        this.option = option;
    }

    public int getIncome() {
        return income;
    }

    public int getCost() {
        return cost;
    }

    public String getLabel() {

        return this.label;
    }

}
