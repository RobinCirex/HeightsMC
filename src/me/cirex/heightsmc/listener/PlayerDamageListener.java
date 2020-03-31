package me.cirex.heightsmc.listener;

import me.cirex.heightsmc.HeightsMC;
import me.cirex.heightsmc.boots.AntiFallDamageSuperBoot;
import me.cirex.heightsmc.boots.SuperBoot;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class PlayerDamageListener implements Listener {

    @EventHandler
    public void onFallDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();

            SuperBoot currentBoot = HeightsMC.getInstance().getSuperBootManager().currentBoot.get(player);
            if (currentBoot != null) {
                if(currentBoot instanceof AntiFallDamageSuperBoot) {
                    if(event.getCause() == EntityDamageEvent.DamageCause.FALL) {
                        event.setCancelled(true);
                    }
                }
            }
        }
    }
}
