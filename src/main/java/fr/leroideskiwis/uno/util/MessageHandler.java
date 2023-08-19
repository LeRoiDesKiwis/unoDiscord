package fr.leroideskiwis.uno.util;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class MessageHandler {

    public static void sendError(TextChannel textChannel, String description){
        EmbedBuilder embedBuilder = new EmbedBuilder()
                .setColor(Color.RED)
                .setTitle("ERROR")
                .setDescription(description);
        textChannel.sendMessage(embedBuilder.build()).queue();
    }

}
