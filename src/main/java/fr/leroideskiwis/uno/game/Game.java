package fr.leroideskiwis.uno.game;

import net.dv8tion.jda.api.EmbedBuilder;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private List<Player> players;
    private Player currentPlayer;
    private Card middleCard;

    public Game(List<Player> players) {
        this.players = players;
    }

    public void completeEmbed(EmbedBuilder embedBuilder) {
        embedBuilder.setTitle("Au tour de "+currentPlayer.toString());
        embedBuilder.setTitle("Carte au centre : "+middleCard.toString());
    }
}
