package me.cirex.heightsmc.menu.menus;

import me.cirex.heightsmc.menu.Menu;
import me.cirex.heightsmc.menu.MenuElement;
import me.cirex.heightsmc.util.MenuRunnable;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class ShopMenu extends Menu {

    public ShopMenu(Player player) {
        super("§eShop", player);
        setup();
    }

    @Override
    public void setup() {
        this.addElement(new MenuElement("§5Teleportations", Material.CHORUS_FRUIT, new MenuRunnable() {
            @Override
            public void run(MenuElement element, Player player) {
                new TeleportationMenu(player).open();
            }
        }));
        this.addElement(new MenuElement("§2Ores", Material.EMERALD_ORE, new MenuRunnable() {
            @Override
            public void run(MenuElement element, Player player) {
                new OresMenu(player).open();
            }
        }));
        this.addElement(new MenuElement("§cLong Range", Material.BOW, new MenuRunnable() {
            @Override
            public void run(MenuElement element, Player player) {

            }
        }));
    }
}
