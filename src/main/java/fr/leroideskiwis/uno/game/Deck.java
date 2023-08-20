package fr.leroideskiwis.uno.game;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Deck {

    private final List<Card> cards = new ArrayList<>();

    public Deck(){
        for(int i = 0; i < 7; i++){
            add(Card.randomCard());
        }
    }

    public Stream<Card> stream() {
        return cards.stream();
    }

    public int size() {
        return cards.size();
    }

    public void add(Card card){
        cards.add(card);
    }

    public void add(int number, Card.CardColor color){
        add(new Card(number, color));
    }

    public Card use(int index){
        Card card = cards.get(index);
        cards.remove(card);
        return card;
    }
}
