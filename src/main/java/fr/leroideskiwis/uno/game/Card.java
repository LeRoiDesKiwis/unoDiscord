package fr.leroideskiwis.uno.game;

public class Card {
    private int number;

    public String toEmote() {
        return null;
    }

    public String getNumber(){
        return String.valueOf(number);
    }

    @Override
    public String toString(){
        return toEmote()+" "+getNumber();
    }
}
