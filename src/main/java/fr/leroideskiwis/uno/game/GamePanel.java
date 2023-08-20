package fr.leroideskiwis.uno.game;

import fr.leroideskiwis.uno.menus.Menu;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageEmbed;

public class GamePanel {

    private Message message;
    private Game game;

    private MessageEmbed createEmbed(){
        EmbedBuilder embedBuilder = new EmbedBuilder();
        game.completeEmbed(embedBuilder);

        return embedBuilder.build();
    }

    public void sendEmbed(MessageChannel messageChannel, Menu menu){
        messageChannel.sendMessage(createEmbed()).queue(msg -> {
            message = msg;
            menu.addEmotes(message);
        });
    }

    public void updateEmbed(){
        message.editMessage(createEmbed()).queue();
    }

}
