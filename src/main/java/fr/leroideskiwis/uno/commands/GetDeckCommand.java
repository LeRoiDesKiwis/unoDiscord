package fr.leroideskiwis.uno.commands;

import fr.leroideskiwis.uno.managers.GameManager;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

public class GetDeckCommand implements Command {
    private final GameManager gameManager;

    public GetDeckCommand(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    public CommandData commandData() {
        return new CommandData("getdeck", "envoie le deck par mp");
    }

    @Override
    public void execute(String commandName, MessageReceivedEvent event, String[] args) {
        User user = event.getAuthor();
        gameManager.retrieveGame(user).ifPresent(game -> game.sendDeck(user));

    }

    @Override
    public Permission permission() {
        return null;
    }
}
