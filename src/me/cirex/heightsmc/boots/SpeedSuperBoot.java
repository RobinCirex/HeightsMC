package me.cirex.heightsmc.boots;

import org.bukkit.Color;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SpeedSuperBoot extends SuperBoot {

    public SpeedSuperBoot() {
        super("§aSpeed", "speed", 5000, Color.PURPLE);
    }

    @Override
    public void onTick(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 4, 0, true, true));
    }
}
