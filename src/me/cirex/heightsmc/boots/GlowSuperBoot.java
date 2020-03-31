package me.cirex.heightsmc.boots;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.entity.Player;

public class GlowSuperBoot extends SuperBoot {


    public GlowSuperBoot() {
        super("§aGlowing Boots", "glow", 1000, Color.GRAY);
    }

    public void setGlowing(Player glowingPlayer, Player sendPacketPlayer, boolean glow) {

    }

    @Override
    public void onTick(Player player) {
        for(Player online : Bukkit.getOnlinePlayers()) {
            setGlowing(online, player, true);
        }
    }

    @Override
    public void stopTask(Player player) {
        super.stopTask(player);
        for(Player online : Bukkit.getOnlinePlayers()) {
            setGlowing(online, player, false);
        }
    }
}
