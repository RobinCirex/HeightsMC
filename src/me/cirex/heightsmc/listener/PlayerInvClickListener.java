package me.cirex.heightsmc.listener;

import me.cirex.heightsmc.HeightsMC;
import me.cirex.heightsmc.menu.Menu;
import me.cirex.heightsmc.menu.MenuElement;
import me.cirex.heightsmc.menu.menus.SuperBootMenu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class PlayerInvClickListener implements Listener {

    @EventHandler
    public void onInvClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Menu currentMenu = HeightsMC.getInstance().getMenuManager().getCurrentMenu(player);

        if (currentMenu != null) {
            if (event.getCurrentItem() != null) {
                if (event.getInventory() == currentMenu.inv) {
                    for (MenuElement element : currentMenu.menuElements) {
                        if (element.item.getType() == event.getCurrentItem().getType()) {
                            if (element.item.equals(event.getCurrentItem())) {
                                if (element.item.getItemMeta().getDisplayName().equalsIgnoreCase(event.getCurrentItem().getItemMeta().getDisplayName())) {
                                    element.runnable.run(element, player);
                                    event.setCancelled(true);
                                    return;
                                }
                            }
                        }
                    }
                }
                event.setCancelled(true);

            }
        }
    }

    @EventHandler
    public void onInvClose(InventoryCloseEvent event) {
        HeightsMC.getInstance().getMenuManager().closeMenu((Player) event.getPlayer());
    }

}
