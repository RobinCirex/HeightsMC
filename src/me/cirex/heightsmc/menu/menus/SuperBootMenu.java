package me.cirex.heightsmc.menu.menus;

import me.cirex.heightsmc.HeightsMC;
import me.cirex.heightsmc.boots.SuperBoot;
import me.cirex.heightsmc.menu.Menu;
import me.cirex.heightsmc.menu.MenuElement;
import me.cirex.heightsmc.util.MenuRunnable;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.IOException;

public class SuperBootMenu extends Menu {
    public SuperBootMenu(Player player) {
        super("§6Super Boots", player);
        setup();
    }

    @Override
    public void setup() {
        this.menuElements.clear();
        MenuRunnable runnable = new MenuRunnable() {
            @Override
            public void run(MenuElement element, Player player) {
                SuperBoot currentBoot = HeightsMC.getInstance().getSuperBootManager().currentBoot.get(player);
                if (currentBoot != null) {
                    currentBoot.stopTask(player);
                    HeightsMC.getInstance().getSuperBootManager().currentBoot.remove(player);
                    player.getInventory().setBoots(null);
                }
            }
        };
        this.addElement(new MenuElement("No Boots", Material.BARRIER, runnable));
        this.addElement(new SuperBootMenuElement("§aRegeneration Boots", Material.LEATHER_BOOTS, HeightsMC.getInstance().getSuperBootManager().getBoot("regen")));
        this.addElement(new SuperBootMenuElement("§aStrength Boots", Material.LEATHER_BOOTS, HeightsMC.getInstance().getSuperBootManager().getBoot("strength")));
        this.addElement(new SuperBootMenuElement("§aGlow Boots", Material.LEATHER_BOOTS, HeightsMC.getInstance().getSuperBootManager().getBoot("glow")));
        this.addElement(new SuperBootMenuElement("§aAnti Fall Damage Boots", Material.LEATHER_BOOTS, HeightsMC.getInstance().getSuperBootManager().getBoot("nofall")));
        this.addElement(new MenuElement("No Boots", Material.BARRIER, runnable));
        this.addElement(new SuperBootMenuElement("§6Jump Boost Boots", Material.LEATHER_BOOTS, HeightsMC.getInstance().getSuperBootManager().getBoot("jumpboost")));
        this.addElement(new SuperBootMenuElement("§6Speed Boots", Material.LEATHER_BOOTS, HeightsMC.getInstance().getSuperBootManager().getBoot("speed")));
        this.addElement(new SuperBootMenuElement("§6Double Jump Boots", Material.LEATHER_BOOTS, HeightsMC.getInstance().getSuperBootManager().getBoot("doublejump")));
        this.addElement(new SuperBootMenuElement("§6Absorption", Material.LEATHER_BOOTS, HeightsMC.getInstance().getSuperBootManager().getBoot("absorption")));
    }

    private class SuperBootMenuElement extends MenuElement {

        public SuperBoot boot;

        public SuperBootMenuElement(String title, Material material, SuperBoot boot, String... lore) {
            super(title, material, new MenuRunnable() {
                @Override
                public void run(MenuElement element, Player player) {
                    SuperBootMenuElement e = (SuperBootMenuElement) element;

                    if (e.boot.isBought(player)) {
                        e.boot.putOn(player);
                        setup();
                    } else {
                        long coins = HeightsMC.getInstance().getStatsManager().getCoins(player);

                        if (e.boot.price == -1) {
                            player.sendMessage("§cYou need to buy this superboot online at http://store.heightsmc.com/");
                            return;
                        }

                        if (coins < e.boot.price) {
                            player.sendMessage("§cYou don't have enough coins");
                        } else {
                            HeightsMC.getInstance().getStatsManager().removeCoins(player, e.boot.price);
                            HeightsMC.getInstance().getShopConfig().set(player.getUniqueId().toString() + "." + boot.name, true);
                            player.sendMessage("§aYou have bought the boots.");
                            setup();
                            try {
                                HeightsMC.getInstance().getShopConfig().save(HeightsMC.getInstance().getShopFile());
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }


                }
            }, boot.isBought(player) ? "§6Owned" : "§5Price: " + boot.price);
            this.boot = boot;
            if (boot.isBought(player)) {
                this.item.setType(Material.DIAMOND_BOOTS);
            }

            SuperBoot currentBoot = HeightsMC.getInstance().getSuperBootManager().currentBoot.get(player);
            if (currentBoot != null) {
                if (currentBoot.name.equalsIgnoreCase(boot.name)) {
                    this.item.addEnchantment(Enchantment.PROTECTION_FALL, 1);
                }
            }
        }

    }


    @Override
    public void addElement(MenuElement element) {
        this.menuElements.add(element);
        this.inv.clear();

        ItemStack stack = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(" ");
        stack.setItemMeta(meta);

        int i = 0;
        for (MenuElement menuElement : this.menuElements) {
            if (menuElement instanceof SuperBootMenuElement) {
                this.inv.setItem(i, stack);
                i++;
            }
            this.inv.setItem(i, menuElement.item);
            i++;

        }
    }

}
