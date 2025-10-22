

import java.util.ArrayList;
import java.util.Scanner;

public class TextUI {


    Scanner sc = new Scanner(System.in);

    public ArrayList<String> promptChoice( ArrayList<String> options, int limit, String msg){

        displayList(options, "");

        ArrayList<String> choices = new ArrayList<>();  //Lave en beholder til at gemme brugerens valg

        while(choices.size() < limit){             //tjekke om brugeren skal vælge flere drinks

            int choice = promptNumeric(msg);
            choices.add(options.get(choice-1));
        }
        return choices;
    }

    public void displayList(ArrayList<String>list, String msg) {
        for (int i = 0; i < list.size();i++) {
            System.out.println(i+1+". "+list.get(i));
        }
    }

    public int promptNumeric(String msg){
        System.out.println(msg);//Stille brugeren et spørgsmål
        String input = sc.nextLine();          //Give brugere et sted at placere sit svar og vente på svaret
        int numInput = Integer.parseInt(input);       //Konvertere svaret til et tal

        return numInput;
    }

    public String promptText(String msg){
        System.out.println(msg);//Stille brugeren et spørgsmål
        String input = sc.nextLine();          //Give brugere et sted at placere sit svar og vente på svaret

        return input;
    }
}
