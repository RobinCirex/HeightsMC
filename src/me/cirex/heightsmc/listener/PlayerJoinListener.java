package me.cirex.heightsmc.listener;

import me.cirex.heightsmc.HeightsMC;
import me.cirex.heightsmc.scoreboard.ScoreboardManagement;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        HeightsMC.getInstance().getStatsManager().registerPlayer(event.getPlayer());
        ScoreboardManagement.setScoreboard(event.getPlayer());
    }

}
