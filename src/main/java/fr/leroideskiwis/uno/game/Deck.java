package fr.leroideskiwis.uno.game;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Deck {

    private List<Card> cards = new ArrayList<>();

    public Stream<Card> stream() {
        return cards.stream();
    }
}
