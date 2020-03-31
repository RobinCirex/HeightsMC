package me.cirex.heightsmc.management;

import com.google.common.collect.Maps;
import me.cirex.heightsmc.HeightsMC;
import me.cirex.heightsmc.boots.SuperBoot;
import me.cirex.heightsmc.scoreboard.ScoreboardManagement;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class StatsManager {

    public HashMap<String, Long> coins = Maps.newHashMap();
    public HashMap<String, Long> kills = Maps.newHashMap();
    public HashMap<String, Long> deaths = Maps.newHashMap();

    public HashMap<String, FileConfiguration> cfg = Maps.newHashMap();

    public void loadStats() {
        FileConfiguration cfg = HeightsMC.getInstance().getStatsSaveConfig();
        List<String> list = cfg.getStringList("players");

        for (String player : list) {
            coins.put(player, cfg.getLong(player + ".coins"));
            kills.put(player, cfg.getLong(player + ".kills"));
            deaths.put(player, cfg.getLong(player + ".deaths"));
        }

        Bukkit.getScheduler().scheduleSyncRepeatingTask(HeightsMC.getInstance(), new Runnable() {
            @Override
            public void run() {
                saveStats();
            }
        }, 20, 20 * 60 * 5);

        for (World world : Bukkit.getWorlds()) {
            File worldConfig = new File("plugins//GroupManager//worlds//" + world.getName() + "//users.yml");
            this.cfg.put(world.getName(), YamlConfiguration.loadConfiguration(worldConfig));
        }

    }

    public void registerPlayer(Player player) {
        FileConfiguration cfg = HeightsMC.getInstance().getShopConfig();
        List<String> list = cfg.getStringList("players");

        cfg.addDefault(player.getUniqueId().toString() + ".chilllounge", false);
        cfg.addDefault(player.getUniqueId().toString() + ".enderchest", false);


        for(SuperBoot boot : HeightsMC.getInstance().getSuperBootManager().boots) {
            cfg.addDefault(player.getUniqueId().toString()+"."+boot.name, false);
        }


        if (!list.contains(player.getUniqueId().toString())) {
            list.add(player.getUniqueId().toString());
            cfg.set("players", list);
        }
        try {
            cfg.save(HeightsMC.getInstance().getStatsSaveFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveStats() {
        FileConfiguration cfg = HeightsMC.getInstance().getStatsSaveConfig();
        List<String> list = cfg.getStringList("players");

        for (String player : list) {
            cfg.set(player + ".coins", getCoins(player));
            cfg.set(player + ".kills", getKills(player));
            cfg.set(player + ".deaths", getDeaths(player));
        }

        try {
            cfg.save(HeightsMC.getInstance().getStatsSaveFile());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getRank(Player player) {
        FileConfiguration config = null;
        if (cfg.containsKey(player.getWorld().getName())) {
            config = cfg.get(player.getWorld().getName());
        } else {
            File worldConfig = new File("plugins//GroupManager//worlds//" + player.getWorld().getName() + "//users.yml");
            this.cfg.put(player.getWorld().getName(), config = YamlConfiguration.loadConfiguration(worldConfig));
        }
        return config.getString("users." + player.getUniqueId().toString() + ".group");
    }

    public void addKill(Player player, long kill) {
        if (kills.containsKey(player.getUniqueId().toString()))
            kills.put(player.getUniqueId().toString(), kills.get(player.getUniqueId().toString()) + kill);
        else
            kills.put(player.getUniqueId().toString(), kill);
        ScoreboardManagement.setScoreboard(player);
    }


    public void removeKill(Player player, long kill) {
        long newValue = 0;
        if (kills.containsKey(player.getUniqueId().toString()))
            newValue = Math.max(0, kills.get(player.getUniqueId().toString()) - kill);
        else
            newValue = kill;
        kills.put(player.getUniqueId().toString(), newValue);
        ScoreboardManagement.setScoreboard(player);
    }

    public void addDeath(Player player, long death) {
        if (deaths.containsKey(player.getUniqueId().toString()))
            deaths.put(player.getUniqueId().toString(), deaths.get(player.getUniqueId().toString()) + death);
        else
            deaths.put(player.getUniqueId().toString(), death);
        ScoreboardManagement.setScoreboard(player);
    }

    public void removeDeath(Player player, long death) {
        long newValue = 0;
        if (deaths.containsKey(player.getUniqueId().toString()))
            newValue = Math.max(0, deaths.get(player.getUniqueId().toString()) - death);
        else
            newValue = death;
        deaths.put(player.getUniqueId().toString(), newValue);
        ScoreboardManagement.setScoreboard(player);
    }

    public void addCoins(Player player, long coin) {
        if (coins.containsKey(player.getUniqueId().toString()))
            coins.put(player.getUniqueId().toString(), coins.get(player.getUniqueId().toString()) + coin);
        else
            coins.put(player.getUniqueId().toString(), coin);
        ScoreboardManagement.setScoreboard(player);
    }

    public void removeCoins(Player player, long coin) {
        long newValue = 0;
        if (coins.containsKey(player.getUniqueId().toString()))
            newValue = Math.max(0, coins.get(player.getUniqueId().toString()) - coin);
        else
            newValue = coin;
        coins.put(player.getUniqueId().toString(), newValue);
        ScoreboardManagement.setScoreboard(player);
    }

    public long getCoins(Player player) {
        return getCoins(player.getUniqueId().toString());
    }

    public long getKills(Player player) {
        return getKills(player.getUniqueId().toString());
    }

    public long getDeaths(Player player) {
        return getDeaths(player.getUniqueId().toString());
    }

    public long getCoins(String player) {
        return coins.containsKey(player) ? coins.get(player) : 0;
    }

    public long getKills(String player) {
        return kills.containsKey(player) ? kills.get(player) : 0;
    }

    public long getDeaths(String player) {
        return deaths.containsKey(player) ? deaths.get(player) : 0;
    }
}

