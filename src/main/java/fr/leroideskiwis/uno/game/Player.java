package fr.leroideskiwis.uno.game;

import net.dv8tion.jda.api.entities.User;

public class Player {

    private final User user;

    public Player(User user) {
        this.user = user;
    }

    public String getName(){
        return user.getName();
    }
}
