package fr.leroideskiwis.uno.menus;

import java.util.HashMap;
import java.util.Map;

public class MenuManager {

    private Map<String, Menu> menus = new HashMap<>();

    public Menu getById(String id){
        return menus.get(id);
    }

    public void registerMenu(String id, Menu menu){
        menus.put(id, menu);
    }

}
