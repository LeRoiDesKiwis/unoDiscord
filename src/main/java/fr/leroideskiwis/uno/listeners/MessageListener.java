package fr.leroideskiwis.uno.listeners;

import fr.leroideskiwis.uno.CommandManager;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;

public class MessageListener extends ListenerAdapter {

    private final CommandManager commandManager;

    public MessageListener(CommandManager commandManager){
        this.commandManager = commandManager;
    }

    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {

        if(event.getAuthor().equals(event.getJDA().getSelfUser())) return;

        String content = event.getMessage().getContentDisplay();
        String[] splitted = content.split(" ");
        String commandName = commandManager.removePrefixe(splitted[0]);

        if(!commandManager.isCommand(splitted[0])) return;

        String[] args = new String[splitted.length-1];
        System.arraycopy(splitted, 1, args, 0, args.length);

        commandManager.handleCommand(commandName, event, args);
    }
}
