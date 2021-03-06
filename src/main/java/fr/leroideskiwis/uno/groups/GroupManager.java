package fr.leroideskiwis.uno.groups;

import fr.leroideskiwis.uno.menus.Menu;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class GroupManager {

    private List<Group> groups = new ArrayList<>();

    public void createGroup(TextChannel textChannel, Menu menu, User owner, List<User> users){
        Group group = new Group();
        group.add(owner);
        group.add(users);
        groups.add(group);
        group.sendEmbed(textChannel, menu);
    }

    public Optional<Group> retrieveGroup(User user){
        return groups.stream().filter(group -> group.isInTheGroup(user)).findAny();
    }

    public void deleteGroup(User user){
        retrieveGroup(user).ifPresent(group -> groups.remove(group));
    }

}
