package me.cirex.heightsmc.menu;

import com.google.common.collect.Lists;
import me.cirex.heightsmc.HeightsMC;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public abstract class Menu {

    public Inventory inv;

    public ArrayList<MenuElement> menuElements = Lists.newArrayList();
    protected Player player;
    protected String title;

    public Menu(String title, Player player) {
        this.inv = Bukkit.createInventory(null, 27, title);
        this.title = title;
        this.player = player;
    }

    public void open() {
        player.openInventory(inv);
        HeightsMC.getInstance().getMenuManager().openMenu(player, this);
    }

    public abstract void setup();

    public void addElement(MenuElement element) {
        this.inv.clear();
        this.menuElements.add(element);


        ItemStack stack = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(" ");
        stack.setItemMeta(meta);

        int i = 0;
        for (i = 0; i < 9; i++) {
            this.inv.setItem(i, stack);
        }

        int diff = 9 - menuElements.size();

        int iTemp = i;

        for (i = i; i < iTemp + diff / 2; i++) {
            this.inv.setItem(i, stack);
        }

        for (MenuElement menuElement : this.menuElements) {
            this.inv.setItem(i, menuElement.item);
            i++;
        }
        iTemp = i;
        for (i = i; i < iTemp + diff / 2; i++) {
            this.inv.setItem(i, stack);
        }

        iTemp = i;
        for (i = i; i < iTemp + 9; i++) {
            this.inv.setItem(i, stack);
        }
        this.inv.setItem(26, stack);
    }

}
