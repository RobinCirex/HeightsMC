package me.cirex.heightsmc.scoreboard;

import me.cirex.heightsmc.HeightsMC;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.*;

public class ScoreboardManagement {

    public static void setScoreboard(Player player) {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();
        Objective objective = board.registerNewObjective("test", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(ChatColor.RED + "Stats");
        Score score0 = objective.getScore("§7Kills: §b" + HeightsMC.getInstance().getStatsManager().getKills(player));
        score0.setScore(10);
        Score score1 = objective.getScore("§7Deaths: §b" + HeightsMC.getInstance().getStatsManager().getDeaths(player));
        score1.setScore(9);
        Score score2 = objective.getScore("§7Coins: §b" + HeightsMC.getInstance().getStatsManager().getCoins(player));
        score2.setScore(8);
        player.setScoreboard(board);


    }

}


