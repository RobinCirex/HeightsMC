package me.cirex.heightsmc.menu.menus;

import me.cirex.heightsmc.menu.Menu;
import me.cirex.heightsmc.menu.MenuElement;
import me.cirex.heightsmc.util.MenuRunnable;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class OresMenu extends Menu {
    public OresMenu(Player player) {
        super("§2Ores", player);
        setup();
    }

    @Override
    public void setup() {
        this.addElement(new OreMenuElement("§aEmerald Block", Material.EMERALD_BLOCK, 100));
        this.addElement(new OreMenuElement("§aEmerald", Material.EMERALD, 75));
        this.addElement(new OreMenuElement("§bDiamond Block", Material.DIAMOND_BLOCK, 50));
        this.addElement(new OreMenuElement("§bDiamond", Material.DIAMOND, 25));
        this.addElement(new OreMenuElement("§eGold Block", Material.GOLD_BLOCK, 20));
        this.addElement(new OreMenuElement("§eGold", Material.GOLD_INGOT, 15));
        this.addElement(new OreMenuElement("§fIron Block", Material.IRON_BLOCK, 10));
        this.addElement(new OreMenuElement("§fIron", Material.IRON_INGOT, 5));
        this.addElement(new OreMenuElement("§8Coal Block", Material.COAL_BLOCK, 3));

    }

    private class OreMenuElement extends MenuElement {

        public OreMenuElement(String title, Material material, int price1,  String... lore) {
            super(title, material, new MenuRunnable() {
                @Override
                public void run(MenuElement element, Player player) {
                    new OreMenu(element.item.getItemMeta().getDisplayName(), player, material, price1).open();
                }
            }, lore);
        }


    }
}
