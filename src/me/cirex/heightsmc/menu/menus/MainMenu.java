package me.cirex.heightsmc.menu.menus;

import me.cirex.heightsmc.HeightsMC;
import me.cirex.heightsmc.menu.Menu;
import me.cirex.heightsmc.menu.MenuElement;
import me.cirex.heightsmc.util.MenuRunnable;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class MainMenu extends Menu {


    public MainMenu(Player player) {
        super("§6Menu", player);
        setup();
    }

    @Override
    public void setup() {
        long coins = HeightsMC.getInstance().getStatsManager().getCoins(player);
        this.addElement(new MenuElement("§eShop", Material.SUNFLOWER, new MenuRunnable() {
            @Override
            public void run(MenuElement element, Player player) {
                new ShopMenu(player).open();
            }
        }));
        this.addElement(new MenuElement("§dWarps", Material.ENDER_PEARL, new MenuRunnable() {
            @Override
            public void run(MenuElement element, Player player) {
                new WarpMenu(player).open();
            }
        }));
        this.addElement(new MenuElement("§6Super Boots", Material.DIAMOND_BOOTS, new MenuRunnable() {
            @Override
            public void run(MenuElement element, Player player) {
                new SuperBootMenu(player).open();
            }
        }));
        this.addElement(new MenuElement("§5" + player.getName(), Material.PLAYER_HEAD, new MenuRunnable() {
            @Override
            public void run(MenuElement element, Player player) {

            }
        }, "§6Coins: " + coins));
        this.addElement(new MenuElement("§aChat", Material.ORANGE_CONCRETE, new MenuRunnable() {
            @Override
            public void run(MenuElement element, Player player) {
                new ChatMenu(player).open();
            }
        }));
        this.addElement(new MenuElement("§7Information", Material.LEGACY_BOOK_AND_QUILL, new MenuRunnable() {
            @Override
            public void run(MenuElement element, Player player) {
                new InformationMenu(player).open();
            }
        }));
        long enderChestPrice = 500;
        boolean bought = HeightsMC.getInstance().getShopConfig().getBoolean(player.getUniqueId().toString() + ".enderchest");
        this.addElement(new MenuElement("§5Ender Chest", Material.ENDER_CHEST, new MenuRunnable() {
            @Override
            public void run(MenuElement element, Player player) {
                Inventory inv = Bukkit.createInventory(null, InventoryType.ENDER_CHEST, "§5Ender Chest");


                if (bought) {
                    player.openInventory(player.getEnderChest());
                    return;
                }
                if (coins < enderChestPrice) {
                    player.sendMessage("§cYou don't have enough coins");
                } else {
                    HeightsMC.getInstance().getStatsManager().removeCoins(player, enderChestPrice);
                    HeightsMC.getInstance().getShopConfig().set(player.getUniqueId().toString() + ".enderchest", true);
                    player.openInventory(player.getEnderChest());
                    HeightsMC.getInstance().getMenuManager().closeMenu(player);
                }
            }
        }, bought ? "" : "§5Price: " + enderChestPrice + " (One Time)"));
    }

}
