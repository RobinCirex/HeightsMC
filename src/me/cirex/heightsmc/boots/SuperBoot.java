package me.cirex.heightsmc.boots;

import com.google.common.collect.Maps;
import me.cirex.heightsmc.HeightsMC;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.Arrays;
import java.util.HashMap;

public abstract class SuperBoot {

    public String name;
    public ItemStack stack;
    public int price;

    public SuperBoot(String displayName, String name, int price, Color color) {
        this.name = name;
        this.price = price;
        stack = new ItemStack(Material.LEATHER_BOOTS);
        LeatherArmorMeta armorMeta = (LeatherArmorMeta) stack.getItemMeta();
        armorMeta.setColor(color);
        armorMeta.setDisplayName(displayName);
        armorMeta.setUnbreakable(true);
        stack.setItemMeta(armorMeta);

    }

    public abstract void onTick(Player player);

    public void onPutOn(Player player) {

    }

    public boolean isBought(Player player) {
        return HeightsMC.getInstance().getShopConfig().getBoolean(player.getUniqueId().toString() + "." + this.name);
    }

    private HashMap<Player, Integer> taskIDs = Maps.newHashMap();

    public void putOn(Player player) {
        if (HeightsMC.getInstance().getSuperBootManager().currentBoot.containsKey(player)) {
            SuperBoot currentBoot = HeightsMC.getInstance().getSuperBootManager().currentBoot.get(player);
            currentBoot.stopTask(player);
        }
        onPutOn(player);
        HeightsMC.getInstance().getSuperBootManager().currentBoot.put(player, this);

        player.getInventory().setBoots(this.stack);
        int id = Bukkit.getScheduler().scheduleSyncRepeatingTask(HeightsMC.getInstance(), new Runnable() {
            @Override
            public void run() {
                onTick(player);
            }
        }, 1, 1);
        taskIDs.put(player, id);
    }

    public void stopTask(Player player) {
        Bukkit.getScheduler().cancelTask(taskIDs.get(player));
    }


}
