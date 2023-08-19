package fr.leroideskiwis.uno.menus;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageReaction;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Menu {

    private final Map<String, String> menu = new HashMap<>();

    public Menu(MenuPart... menuParts){
        for(MenuPart menuPart : menuParts){
            menu.put(menuPart.emote, menuPart.description);
        }
    }

    public void addEmotes(Message message){
        menu.forEach((emote, desc) -> message.addReaction(emote).queue());
    }

    public Optional<String> retrieveDescription(MessageReaction.ReactionEmote emote){
        String name = emote.getName();
        if(menu.containsKey(name)) return Optional.of(menu.get(name));
        String codepoints = emote.getAsCodepoints();
        if(menu.containsKey(codepoints)) return Optional.of(menu.get(codepoints));
        return Optional.empty();
    }

    public static class MenuPart{
        private final String emote;
        private final String description;

        public MenuPart(String emote, String description) {
            this.emote = emote;
            this.description = description;
        }
    }

}
