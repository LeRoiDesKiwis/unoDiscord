package fr.leroideskiwis.uno.commands;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

public interface Command {

    CommandData commandData();
    void execute(String commandName, MessageReceivedEvent event, String[] args);
    Permission permission();

}
