public class Plot extends Property{

    private int buildings;

    public Plot(int ID, String label, int cost, int income, int seriesID) {
        super(ID, label, cost, income, seriesID);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public String onLand(Player p) {


        //return super.onLand(p);

        String msg = super.onLand(p);

        if(getOwner()!=null && getOwner()==p && isMonopolized){
            setOption("build");
            msg += "\n Vil du bygge? (Y/N):";
        }

        return msg;
    }

    @Override
    public String onAccept(Player p) {
        String msg =super.onAccept(p);

        if(this.getOption().equals("build")){
            buildings++;
            msg += "Du har bygget";
            //p.build(4000);
        }
        //checkForMonopoly();//tjekker om købet skabte et monopol og sætter isMonopolized til true
        return msg;

    }

    public int getBuildings() {
        return buildings;
    }

    @Override
    public String onReject(Player p) {
        return super.onReject(p);
    }
}
