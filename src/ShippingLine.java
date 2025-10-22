public class ShippingLine extends Property{
    public ShippingLine(int ID, String label, int cost, int income, int seriesID) {
        super(ID, label, cost, income, seriesID);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public String onLand(Player p) {
        return super.onLand(p);
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
