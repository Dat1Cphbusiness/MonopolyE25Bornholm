public class Brewery extends Property {
    public Brewery(int ID, String label, int cost, int income, int seriesID) {
        super(ID, label, cost, income, seriesID);
    }

    @Override
    public String toString() {

        return super.toString();
    }

    @Override
        public String onLand(Player p) {
            String msg = super.onLand(p);
            /*
                Modificerer cost ud fra terningkastet
             */
            return msg;
    }

    @Override
    public String onAccept(Player p) {
        return super.onAccept(p);
    }

    @Override
    public String onReject(Player p) {
        return super.onReject(p);
    }
}
