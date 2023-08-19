package fr.leroideskiwis.uno.game;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.List;

public class Game {

    private final TextChannel textChannel;
    private final List<Player> players;
    private Player currentPlayer;
    private Card middleCard;

    public Game(TextChannel textChannel, List<Player> players) {
        this.players = players;
        this.textChannel = textChannel;
    }

    public void startGame(){
        EmbedBuilder embedBuilder = new EmbedBuilder().setTitle("PARTIE DE UNO");
        for(Player player : players){
            embedBuilder.addField(player.getName(), "", true);
        }
        textChannel.sendMessage(embedBuilder.setTitle("PARTIE DE UNO").build()).queue();
    }

    public void completeEmbed(EmbedBuilder embedBuilder) {
        embedBuilder.setTitle("Au tour de "+currentPlayer.toString());
        embedBuilder.setTitle("Carte au centre : "+middleCard.toString());
    }
}
