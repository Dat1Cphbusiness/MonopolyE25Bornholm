import util.TextUI;

public class Prison extends Field implements IOption{
    private TextUI ui;
    // Attributes
    // Constructor
    public Prison(int ID, String label, int cost) {
        super(ID, label, cost, 0);
    }

    // Methods
    public String onLand(Player p){
        boolean useWildcard = false;
        String msg = super.onLand(p);
        msg += "\nDu er arresteret, og skal i fængsel";

 /*       // Check if player has wildcard
        if(p.getWildcard()){
            useWildcard = ui.promptBinary("Vil du bruge dit wildcard? (Y/N)");

            // Check if player uses wildcard - override and return message
            if (useWildcard){
                msg = "\nDu har kontakter hos Politiet og løslades.";
                p.setWildcard = false;
                return msg;
            }

            // If player does not use wildcard, add to message
            else if(!useWildcard){
                msg += "\nDu har valgt ikke at bruge det wildcard, du ryger i fængsel.";  // Change "wildcard" according to game rules

            }
        }
        // Move player to field 31 - "Visit"
        p.moveToPostion(31);
        msg += "\n Næste gang det bliver din tur, kan du vælge om du vil betale dig ud, eller prøve et dobbeltslag." +
                "\n når du har siddet over i tre omgange er du løsladt.";
*/
        return msg;
    }
}
