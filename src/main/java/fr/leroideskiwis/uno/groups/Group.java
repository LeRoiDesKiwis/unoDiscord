package fr.leroideskiwis.uno.groups;

import fr.leroideskiwis.uno.game.Game;
import fr.leroideskiwis.uno.game.GameManager;
import fr.leroideskiwis.uno.game.Player;
import fr.leroideskiwis.uno.menus.Menu;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

import java.awt.Color;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

public class Group {

    private Map<User, InviteStatus> players = new HashMap<>();
    private boolean started;

    private User owner;
    private Message invite;

    public Group(User owner){
        this.owner = owner;
    }

    private List<User> readyUsers(boolean includesOwner){
        List<User> players1 = players.keySet().stream().filter(user -> players.get(user) == InviteStatus.ACCEPTED).collect(Collectors.toList());
        if(includesOwner) players1.add(owner);
        return players1;
    }

    public void add(User user){
        players.put(user, InviteStatus.WAITING);
    }
    
    public void add(List<User> users){
        users.forEach(this::add);
    }

    public void accept(User user){
        players.replace(user, InviteStatus.ACCEPTED);
    }

    public void deny(User user){
        players.replace(user, InviteStatus.DENIED);
    }

    public boolean isMessage(long msg){
        return invite.getIdLong() == msg;
    }

    private MessageEmbed createEmbed(){
        if(started) return new EmbedBuilder().setColor(Color.GRAY).setTitle("Partie commencee").build();
        Color[] colors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN};

        int color = ((colors.length-1)*(readyUsers(false).size()))/(players.size());
        EmbedBuilder builder = new EmbedBuilder().setColor(colors[color]);
        builder.setTitle(String.format("Groupe de %d joueur(s) (cree par %s)", players.size()+1, owner.getName()));
        for(Map.Entry<User, InviteStatus> player : players.entrySet()){
            User user = player.getKey();
            builder.addField(user.getName()+"#"+ user.getDiscriminator(), player.getValue().toString(), false);
        }
        return builder.build();
    }

    public void sendEmbed(TextChannel textChannel, Menu menu){
        textChannel.sendMessage(createEmbed()).queue(message -> {
            invite = message;
            menu.addEmotes(message);
        });
    }

    public void updateEmbed(){
        invite.editMessage(createEmbed()).queue();
    }

    public boolean isInTheGroup(User user){
        return user.equals(owner) || players.containsKey(user);
    }

    public Game toGame() {
        this.started = true;
        return new Game(invite.getTextChannel(), readyUsers(true).stream().map(Player::new).collect(Collectors.toList()));
    }

    public void handleClick(String id, GroupManager groupManager, GameManager gameManager, User user) {
        System.out.println(id);
        switch(id){
            case "accept":
                accept(user);
                break;
            case "deny":
                deny(user);
                break;
            case "start":
                if(user.equals(owner)) gameManager.startGame(this, groupManager);
            default:
                break;
        }

        updateEmbed();
    }
}
