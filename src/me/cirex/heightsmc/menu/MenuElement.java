package me.cirex.heightsmc.menu;

import me.cirex.heightsmc.util.MenuRunnable;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class MenuElement {

    public ItemStack item;
    public MenuRunnable runnable;

    public MenuElement(String title, Material material, MenuRunnable runnable, String... lore) {
        this.item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(title);
        meta.setLore(Arrays.asList(lore));
        this.item.setItemMeta(meta);
        this.runnable = runnable;
    }

    public MenuElement(String title, Material material, MenuRunnable runnable, int amount, String... lore) {
        this.item = new ItemStack(material, amount);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(title);
        meta.setLore(Arrays.asList(lore));
        this.item.setItemMeta(meta);
        this.runnable = runnable;
    }
}
