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
import java.util.Collections;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Group {

    private Map<User, InviteStatus> players = new HashMap<>();
    private Message invite;

    private List<User> readyUsers(){
        return players.keySet().stream().filter(user -> players.get(user) != InviteStatus.IDLE).collect(Collectors.toList());
    }

    public void add(User user){
        add(Collections.singletonList(user));
    }
    
    public void add(List<User> users){
        users.forEach(user -> players.put(user, InviteStatus.IDLE));
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
        Color[] colors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN};

        int color = ((colors.length-1)*readyUsers().size())/players.size();
        EmbedBuilder builder = new EmbedBuilder().setColor(colors[color]);
        builder.setTitle("Groupe de "+players.size()+" joueurs");
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
        return players.containsKey(user);
    }

    public Game toGame() {
        return new Game(players.keySet().stream().map(Player::new).collect(Collectors.toList()));
    }

    public void handleClick(String id, GameManager gameManager, User user) {
        switch(id){
            case "accept":
                accept(user);
                break;
            case "deny":
                deny(user);
                break;
            case "start":
                gameManager.startGame(this);
            default:
                break;
        }

        updateEmbed();
    }
}
