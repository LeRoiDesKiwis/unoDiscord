package fr.leroideskiwis.uno;

import fr.leroideskiwis.uno.game.GameManager;
import fr.leroideskiwis.uno.menus.MenuManager;

public class ReactionGameListener {
    private final MenuManager menuManager;
    private final GameManager gameManager;

    public ReactionGameListener(MenuManager menuManager, GameManager gameManager) {
        this.menuManager = menuManager;
        this.gameManager = gameManager;
    }
}
