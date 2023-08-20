package fr.leroideskiwis.uno.managers;

import fr.leroideskiwis.uno.game.Game;
import fr.leroideskiwis.uno.groups.Group;
import net.dv8tion.jda.api.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GameManager {

    private final List<Game> games = new ArrayList<>();

    public void startGame(Group group, GroupManager groupManager){
        Game game = group.toGame();
        games.add(game);
        game.startGame();
        groupManager.deleteGroup(group);

    }

    public Optional<Game> retrieveGame(User user){
        return games.stream().filter(game -> game.hasUser(user)).findAny();
    }

}
