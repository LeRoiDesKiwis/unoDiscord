package fr.leroideskiwis.uno.menus;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageReaction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Menu {

    private final Map<String, String> menu = new HashMap<>();

    public Menu(MenuPart... menuParts){
        for(MenuPart menuPart : menuParts){
            menu.put(menuPart.emote, menuPart.description);
        }
    }

    public void addEmote(String emote, String description){
        menu.put(emote, description);
    }

    public void addEmotes(Message message){
        menu.forEach((emote, desc) -> message.addReaction(emote).queue());
    }

    public String retrieveDescription(MessageReaction.ReactionEmote emote){
        String name = emote.getName();
        if(menu.containsKey(name)) return menu.get(name);
        String codepoints = emote.getAsCodepoints();
        if(menu.containsKey(codepoints)) return menu.get(codepoints);
        return "null";
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
