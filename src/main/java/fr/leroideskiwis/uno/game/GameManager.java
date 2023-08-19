package fr.leroideskiwis.uno.game;

import fr.leroideskiwis.uno.groups.Group;
import fr.leroideskiwis.uno.groups.GroupManager;

import java.util.ArrayList;
import java.util.List;

public class GameManager {

    private final List<Game> games = new ArrayList<>();

    public void startGame(Group group, GroupManager groupManager){
        Game game = group.toGame();
        games.add(game);
        game.startGame();
        groupManager.deleteGroup(group);

    }

}
