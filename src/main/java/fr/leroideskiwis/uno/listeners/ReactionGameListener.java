package fr.leroideskiwis.uno.listeners;

import fr.leroideskiwis.uno.managers.GameManager;
import fr.leroideskiwis.uno.menus.MenuManager;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class ReactionGameListener extends ListenerAdapter {
    private final MenuManager menuManager;
    private final GameManager gameManager;

    public ReactionGameListener(MenuManager menuManager, GameManager gameManager) {
        this.menuManager = menuManager;
        this.gameManager = gameManager;
    }

    @Override
    public void onReady(@NotNull ReadyEvent event) {

    }
}
