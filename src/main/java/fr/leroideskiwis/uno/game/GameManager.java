package fr.leroideskiwis.uno.game;

import fr.leroideskiwis.uno.groups.Group;

import java.util.ArrayList;
import java.util.List;

public class GameManager {

    private List<Game> games = new ArrayList<>();

    public void startGame(Group group){
        games.add(group.toGame());
    }

}
