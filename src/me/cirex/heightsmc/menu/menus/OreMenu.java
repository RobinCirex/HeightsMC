package me.cirex.heightsmc.menu.menus;

import me.cirex.heightsmc.HeightsMC;
import me.cirex.heightsmc.menu.Menu;
import me.cirex.heightsmc.menu.MenuElement;
import me.cirex.heightsmc.util.MenuRunnable;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class OreMenu extends Menu {

    private Material material;
    private int price1, price32, price64;

    public OreMenu(String title, Player player, Material material, int price1) {
        super(title, player);
        this.material = material;
        this.price1 = price1;
        this.price32 = price1 * 32;
        this.price64 = price1 * 64;
        setup();
    }

    @Override
    public void setup() {
        this.addElement(new MenuElement(this.title, this.material, new MenuRunnable() {
            @Override
            public void run(MenuElement element, Player player) {
                long coins = HeightsMC.getInstance().getStatsManager().getCoins(player);
                if (coins >= price1) {
                    ItemStack stack = new ItemStack(material);
                    player.getInventory().addItem(stack);
                    player.sendMessage("§aYou have bought the ore successfully.");
                    HeightsMC.getInstance().getStatsManager().removeCoins(player, price1);
                } else {
                    player.sendMessage("§cYou don't have enough coins");
                }

            }
        }, "§5Price: " + price1 + " Coins"));
        this.addElement(new MenuElement(this.title, this.material, new MenuRunnable() {
            @Override
            public void run(MenuElement element, Player player) {
                long coins = HeightsMC.getInstance().getStatsManager().getCoins(player);

                if (coins >= price32) {

                    ItemStack stack = new ItemStack(material, 32);
                    player.getInventory().addItem(stack);
                    player.sendMessage("§aYou have bought the ore successfully.");
                    HeightsMC.getInstance().getStatsManager().removeCoins(player, price32);
                } else {
                    player.sendMessage("§cYou don't have enough coins");
                }

            }
        }, 32, "§5Price: " + price32 + " Coins"));
        this.addElement(new MenuElement(this.title, this.material, new MenuRunnable() {
            @Override
            public void run(MenuElement element, Player player) {
                long coins = HeightsMC.getInstance().getStatsManager().getCoins(player);

                if (coins >= price64) {

                    ItemStack stack = new ItemStack(material, 64);
                    player.getInventory().addItem(stack);
                    player.sendMessage("§aYou have bought the ore successfully.");
                    HeightsMC.getInstance().getStatsManager().removeCoins(player, price64);
                } else {
                    player.sendMessage("§cYou don't have enough coins");
                }

            }
        }, 64, "§5Price: " + price64 + " Coins"));
    }
}
