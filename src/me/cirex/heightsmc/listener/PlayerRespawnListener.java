package me.cirex.heightsmc.listener;

import me.cirex.heightsmc.HeightsMC;
import me.cirex.heightsmc.boots.AbsorptionSuperBoot;
import me.cirex.heightsmc.boots.SuperBoot;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerRespawnListener implements Listener {

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        SuperBoot boot = HeightsMC.getInstance().getSuperBootManager().currentBoot.get(event.getPlayer());
        if(boot != null) {
            if(boot instanceof AbsorptionSuperBoot) {
                boot.onPutOn(event.getPlayer());
            }
        }
    }
}
