import util.TextUI;
import java.util.ArrayList;


public class Property extends Field implements IOption{
    private Player owner;
    private int seriesID;
    protected boolean isMonopolized = false;
    private TextUI ui = new TextUI();
    public Property(int ID, String label, int cost, int income, int seriesID){
        super(ID,label,cost,income);
        this.seriesID = seriesID;
    }

    @Override
    public String toString() {
        return super.toString() + ", " + this.seriesID;
    }

    @Override
    public String onLand(Player p) {

        String msg = super.onLand(p);
        if (owner == null){
            msg += "\nVil du købe? (Y/N):";
            setOption ("buy");


        } else if (p != owner) {
            msg += "du skal betale " + getIncome();
            p.pay(this.getIncome(), this.owner);
            setOption (null);

        }
        return msg;
    }

 protected boolean checkForMonopoly(Player p){
        int seriesSize = 3;
        switch (this.seriesID) {
            case 2:
            case 3:
            case 10:
                seriesSize = 2;
                break;
            case 1:
                seriesSize = 4;
                break;
            default:
                break;

        }
        ArrayList<Property> deedsInSeries = new ArrayList<>();

        for(Property pr: p.deeds) {
            if(pr.seriesID == this.seriesID) {
                deedsInSeries.add(pr);
            }
        }

        if(deedsInSeries.size() == seriesSize) {
            for(Property pr: deedsInSeries) {
                pr.isMonopolized = true;
            }
            return true;
        }
        return false;
    }


        /*
        * WRITE YOUR PSEUDOCODE HERE
        * */

    @Override
    public String onAccept(Player p) {

        String msg = "";

        if (this.getOption().equals("buy")) {
            p.buyProperty(this);
            this.owner = p;
            msg+= "Du har købt " + super.getLabel();

            if (checkForMonopoly(p)) {
                msg += "\n Du har nu monopol";

            }
            return msg;
        }
        return msg;
    }

    @Override
    public String onReject(Player p) {

        String s = p.getName() + " afviste at købe " + super.getLabel();
        return s;
    }

    public Player getOwner(){
        return this.owner;
    }
}
