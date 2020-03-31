package me.cirex.heightsmc.menu.menus;

import me.cirex.heightsmc.HeightsMC;
import me.cirex.heightsmc.menu.Menu;
import me.cirex.heightsmc.menu.MenuElement;
import me.cirex.heightsmc.util.MenuRunnable;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class TeleportationMenu extends Menu {
    public TeleportationMenu(Player player) {
        super("§5Teleportations", player);
    }

    @Override
    public void setup() {
        this.addElement(new TeleportationMenuElement("Iron Blocks", Material.IRON_BLOCK, -556, 63, -560, 100));
        this.addElement(new TeleportationMenuElement("Iron Ingots", Material.IRON_INGOT, -574, 76, -610, 25));
        this.addElement(new TeleportationMenuElement("§eGold Ingots", Material.GOLD_INGOT, -570, 67, -533, 200));
        this.addElement(new TeleportationMenuElement("§eGold Blocks", Material.GOLD_BLOCK, -557, 77, -521, 300));
        this.addElement(new TeleportationMenuElement("§bDiamond Blocks", Material.DIAMOND_BLOCK, -560, 88, -492, 500));
        this.addElement(new TeleportationMenuElement("§bDiamonds", Material.DIAMOND, -557, 85, -511, 400));
        this.addElement(new TeleportationMenuElement("§aEmeralds", Material.EMERALD, -561, 90, -473, 700));
    }

    private class TeleportationMenuElement extends MenuElement {

        public TeleportationMenuElement(String title, Material material, double x, double y, double z, long price, String... lore) {
            super(title, material, new MenuRunnable() {
                @Override
                public void run(MenuElement element, Player player) {
                    long coins = HeightsMC.getInstance().getStatsManager().getCoins(player);

                    if (coins >= price) {
                        Location location = player.getLocation().clone();
                        location.setX(x);
                        location.setY(y);
                        location.setZ(z);
                        player.teleport(location);
                        HeightsMC.getInstance().getStatsManager().removeCoins(player, price);

                    } else {
                        player.sendMessage("§cYou don't have enough coins");
                    }
                }
            }, "§5Price: " + price + " Coins");
        }
    }
}
