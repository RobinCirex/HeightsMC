package me.cirex.heightsmc.boots;

import org.bukkit.Color;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class RegenSuperBoot extends SuperBoot {

    public RegenSuperBoot() {
        super("§aRegeneration", "regen", 2000, Color.PURPLE);
    }

    @Override
    public void onTick(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 4, 0, true, true));
    }
}
