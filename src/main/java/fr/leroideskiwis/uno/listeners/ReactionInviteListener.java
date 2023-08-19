package fr.leroideskiwis.uno.listeners;

import fr.leroideskiwis.uno.game.GameManager;
import fr.leroideskiwis.uno.groups.Group;
import fr.leroideskiwis.uno.groups.GroupManager;
import fr.leroideskiwis.uno.menus.Menu;
import fr.leroideskiwis.uno.menus.MenuManager;
import net.dv8tion.jda.api.entities.MessageReaction;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.util.Optional;

public class ReactionInviteListener extends ListenerAdapter {

    private final GroupManager groupManager;
    private final MenuManager menuManager;
    private final GameManager gameManager;

    public ReactionInviteListener(GroupManager groupManager, MenuManager menuManager, GameManager gameManager) {
        this.groupManager = groupManager;
        this.menuManager = menuManager;
        this.gameManager = gameManager;
    }

    @Override
    public void onMessageReactionAdd(@Nonnull MessageReactionAddEvent event) {
        User user = event.getUser();
        Optional<Group> groupOpt = groupManager.retrieveGroup(user);
        if(groupOpt.isEmpty()) return;

        Group group = groupOpt.get();
        if(!group.isMessage(event.getMessageIdLong())) return;

        Menu menu = menuManager.getById("invit");

        MessageReaction.ReactionEmote reaction = event.getReactionEmote();
        menu.retrieveDescription(reaction).ifPresent(id -> group.handleClick(id, groupManager, gameManager, user));
    }
}
