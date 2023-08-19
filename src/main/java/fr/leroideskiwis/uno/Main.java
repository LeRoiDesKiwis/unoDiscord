package fr.leroideskiwis.uno;

import fr.leroideskiwis.uno.commands.CreateGroupCommand;
import fr.leroideskiwis.uno.game.GameManager;
import fr.leroideskiwis.uno.groups.GroupManager;
import fr.leroideskiwis.uno.listeners.MessageListener;
import fr.leroideskiwis.uno.listeners.ReactionGameListener;
import fr.leroideskiwis.uno.listeners.ReactionInviteListener;
import fr.leroideskiwis.uno.menus.Menu;
import fr.leroideskiwis.uno.menus.MenuManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;

public class Main extends ListenerAdapter {

    private JDA jda;
    private final CommandManager commandManager = new CommandManager();
    private GroupManager groupManager = new GroupManager();
    private MenuManager menuManager = new MenuManager();
    private GameManager gameManager = new GameManager();

    public void start(String... args) throws LoginException {
        this.jda = JDABuilder
                .createDefault(args[0])
                .build();
        menuManager.registerMenu("invit", new Menu(
                    new Menu.MenuPart("U+274c", "deny"),
                    new Menu.MenuPart("U+2705", "accept"),
                    new Menu.MenuPart("\uD83C\uDFC1", "start")
                )
        );

        jda.addEventListener(new MessageListener(commandManager));
        jda.addEventListener(new ReactionInviteListener(groupManager, menuManager, gameManager));
        jda.addEventListener(new ReactionGameListener(menuManager, gameManager));
        jda.addEventListener(this);
        commandManager.registerCommand(new CreateGroupCommand(groupManager, menuManager));
    }
}
