package me.cirex.heightsmc.util;

import me.cirex.heightsmc.menu.MenuElement;
import org.bukkit.entity.Player;

public interface MenuRunnable {

    public void run(MenuElement element, Player player);

}
