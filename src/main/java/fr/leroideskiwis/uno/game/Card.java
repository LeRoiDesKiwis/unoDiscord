package fr.leroideskiwis.uno.game;

import java.util.Random;

public class Card {
    private final int number;
    private final CardColor color;

    public Card(int number, CardColor color){
        this.number = number;
        this.color = color;
    }

    public String toEmote() {
        return String.format(":%s:", color.emote);
    }

    public String getNumber(){
        return String.valueOf(number);
    }

    @Override
    public String toString(){
        return toEmote()+" "+getNumber();
    }

    public static Card randomCard() {
        Random random = new Random();
        int number = random.nextInt(9)+1;
        CardColor color = CardColor.get(random.nextInt(CardColor.values().length));
        return new Card(number, color);
    }

    public enum CardColor{
        BLUE("blue_square"), RED("red_square"), YELLOW("yellow_square"), GREEN("green_square"),
        PLUS_FOUR("fire"), CHANGE_COLOR("arrows_counterclockwise");

        public final String emote;
        CardColor(String emote) {
            this.emote = emote;
        }

        public static CardColor get(int index){
            return values()[index];
        }
    }
}
