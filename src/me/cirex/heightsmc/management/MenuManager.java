package me.cirex.heightsmc.management;

import com.google.common.collect.Maps;
import me.cirex.heightsmc.menu.Menu;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class MenuManager {

    private HashMap<Player, Menu> currentMenus = Maps.newHashMap();

    public void openMenu(Player player, Menu menu) {
        currentMenus.put(player, menu);
    }

    public Menu getCurrentMenu(Player player) {
        return currentMenus.get(player);
    }

    public void closeMenu(Player player) {
        currentMenus.remove(player);
    }

}
