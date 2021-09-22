package fr.leroideskiwis.uno;

import fr.leroideskiwis.uno.commands.Command;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.internal.utils.PermissionUtil;

import java.util.HashMap;
import java.util.List;

public class CommandManager {

    private HashMap<String, Command> commands = new HashMap<>();
    private final String prefixe = ";";

    @Deprecated
    public void loadCommands(List<Guild> guilds){
    }

    public void registerCommand(Command command){
        commands.put(command.commandData().getName(), command);
    }

    public boolean isCommand(String msg){
        return msg.startsWith(prefixe) && commands.containsKey(removePrefixe(msg));
    }

    public String removePrefixe(String msg){
        return msg.substring(prefixe.length());
    }

    public void handleCommand(String commandName, MessageReceivedEvent event, String[] args){
        Command command = commands.get(commandName);
        Member member = event.getMember();
        if(member != null && PermissionUtil.checkPermission(member, command.permission())) {
            command.execute(commandName, event, args);
        }

    }

}
