package fr.leroideskiwis.uno.game;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;

public class Player {

    private final User user;
    private final Deck deck = new Deck();

    public Player(User user) {
        this.user = user;
    }

    public String getName(){
        return user.getName();
    }

    public boolean isUser(User user){
        return user.equals(this.user);
    }

    public void sendDeck(){
        EmbedBuilder builder = new EmbedBuilder().setTitle(String.format("Deck (%d cartes)", deck.size()));
        deck.stream().forEach(card -> builder.addField(card.toEmote(), card.getNumber(), true));
        user.openPrivateChannel().queue(privateChannel -> privateChannel.sendMessage(builder.build()).queue());
    }
}
