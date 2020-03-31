package me.cirex.heightsmc.listener;

import me.cirex.heightsmc.HeightsMC;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerFightListener implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        if (player.getKiller() != null) {
            Player killer = player.getKiller();
            HeightsMC.getInstance().getStatsManager().addKill(killer, 1);
            HeightsMC.getInstance().getStatsManager().addDeath(player, 1);
            HeightsMC.getInstance().getStatsManager().addCoins(killer, 1);
            if (HeightsMC.getInstance().getStatsManager().getCoins(player) > 0)
                HeightsMC.getInstance().getStatsManager().removeCoins(player, 1);
        }

    }

}
