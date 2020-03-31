package me.cirex.heightsmc;

import me.cirex.heightsmc.commands.MenuCommand;
import me.cirex.heightsmc.commands.StatsCommand;
import me.cirex.heightsmc.listener.*;
import me.cirex.heightsmc.management.MenuManager;
import me.cirex.heightsmc.management.StatsManager;
import me.cirex.heightsmc.management.SuperBootManager;
import me.cirex.heightsmc.scoreboard.ScoreboardManagement;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class HeightsMC extends JavaPlugin {

    private static HeightsMC instance;

    private MenuManager menuManager;
    private StatsManager statsManager;
    private SuperBootManager superBootManager;

    private FileConfiguration statsSaveConfig;
    private File statsSaveFile;

    private FileConfiguration messageConfig;
    private File messageFile;

    private FileConfiguration shopConfig;
    private File shopFile;


    @Override
    public void onEnable() {
        instance = this;
        getCommand("menu").setExecutor(new MenuCommand());
        getCommand("stats").setExecutor(new StatsCommand());

        Bukkit.getPluginManager().registerEvents(new PlayerInvClickListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerFightListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerDamageListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerRespawnListener(), this);

        menuManager = new MenuManager();
        setupFiles();
        this.statsManager = new StatsManager();
        this.statsManager.loadStats();
        this.superBootManager = new SuperBootManager();


        for (Player player : Bukkit.getOnlinePlayers()) {
            ScoreboardManagement.setScoreboard(player);
        }

        super.onEnable();
    }

    @Override
    public void onDisable() {
        for(Player player : Bukkit.getOnlinePlayers()) {
            player.getInventory().setBoots(null);
        }
        this.statsManager.saveStats();
        super.onDisable();
    }

    private void setupFiles() {
        File file = new File("plugins//HeightsMC");
        if (!file.exists()) {
            file.mkdirs();
        }

        this.statsSaveFile = file = new File("plugins//HeightsMC//stats.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.statsSaveConfig = YamlConfiguration.loadConfiguration(file);

        this.messageFile = file = new File("plugins//HeightsMC//messages.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.messageConfig = YamlConfiguration.loadConfiguration(file);
        //    this.messageConfig.addDefault("stats.kills", "You have %kills% kills.");

        this.shopFile = file = new File("plugins//HeightsMC//shop.yml");


        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.shopConfig = YamlConfiguration.loadConfiguration(file);

    }

    public static HeightsMC getInstance() {
        return instance;
    }

    public MenuManager getMenuManager() {
        return menuManager;
    }

    public FileConfiguration getStatsSaveConfig() {
        return statsSaveConfig;
    }

    public File getStatsSaveFile() {
        return statsSaveFile;
    }

    public FileConfiguration getMessageConfig() {
        return messageConfig;
    }

    public File getMessageFile() {
        return messageFile;
    }

    public File getShopFile() {
        return shopFile;
    }

    public FileConfiguration getShopConfig() {
        return shopConfig;
    }

    public StatsManager getStatsManager() {
        return this.statsManager;
    }

    public SuperBootManager getSuperBootManager() {
        return superBootManager;
    }
}
