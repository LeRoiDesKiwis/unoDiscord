package fr.leroideskiwis.uno.commands;

import fr.leroideskiwis.uno.managers.GroupManager;
import fr.leroideskiwis.uno.menus.MenuManager;
import fr.leroideskiwis.uno.util.MessageHandler;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

import java.util.List;
import java.util.stream.Collectors;

public class CreateGroupCommand implements Command {

    private final GroupManager groupManager;
    private final MenuManager menuManager;

    public CreateGroupCommand(GroupManager groupManager, MenuManager menuManager){
        this.groupManager = groupManager;
        this.menuManager = menuManager;
    }

    @Override
    public CommandData commandData() {
        int MAX_PLAYERS = 10;
        CommandData commandData = new CommandData("creategroup", "Créer un groupe de "+MAX_PLAYERS+" personnes maximum");
        for(int i = 1; i <= MAX_PLAYERS; i++){
            commandData.addOption(OptionType.USER, "player"+i, "joueur "+i);
        }
        return commandData;
    }

    @Override
    public void execute(String commandName, MessageReceivedEvent event, String[] args) {
        List<User> mentionedUsers = event
                .getMessage()
                .getMentionedUsers()
                .stream()
                .filter(user -> !user.isBot())
                .collect(Collectors.toList());
        TextChannel textChannel = event.getTextChannel();
        mentionedUsers.remove(event.getAuthor());

        if(mentionedUsers.isEmpty()) {
            MessageHandler.sendError(textChannel, "Vous devez entrer un nom minimum !");
            return;
        }
        groupManager.createGroup(textChannel, menuManager.getById("invit"), event.getAuthor(), mentionedUsers);
    }

    @Override
    public Permission permission() {
        return Permission.MESSAGE_READ;
    }
}
