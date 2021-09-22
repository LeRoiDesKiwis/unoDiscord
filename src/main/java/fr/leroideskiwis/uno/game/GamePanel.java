package fr.leroideskiwis.uno.game;

import fr.leroideskiwis.uno.groups.InviteStatus;
import fr.leroideskiwis.uno.menus.Menu;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.*;

public class GamePanel {

    private boolean dm;
    private Deck deck;
    private Message message;
    private Game game;

    private MessageEmbed createEmbed(){
        EmbedBuilder embedBuilder = new EmbedBuilder();
        game.completeEmbed(embedBuilder);

        if(dm) deck.stream().forEach(card -> embedBuilder.addField(card.toEmote(), card.toString(), false));

        return embedBuilder.build();
    }

    public void sendEmbed(MessageChannel messageChannel, Menu menu){
        this.dm = messageChannel instanceof PrivateChannel;
        messageChannel.sendMessage(createEmbed()).queue(msg -> {
            message = msg;
            menu.addEmotes(message);
        });
    }

    public void updateEmbed(){
        message.editMessage(createEmbed()).queue();
    }

}
