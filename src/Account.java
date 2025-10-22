import util.TextUI;

public class Account {
    private int balance;
    TextUI ui = new TextUI();

    public Account(int balance){
        this.balance = balance;
    }

    public void deposit(int amount){
        this.balance += amount;
    }

    public boolean withdraw(int amount) {
        if(balance >= amount) {
            balance -= amount;
            return true;
        } else {
            ui.displayMessage("Du har ikke penge nok til at betale...");
            //DU ER DØD
        }
        return false;
    }

    public int getBalance() {
        return balance;
    }
}
