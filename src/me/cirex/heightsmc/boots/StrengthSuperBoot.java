package me.cirex.heightsmc.boots;

import org.bukkit.Color;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class StrengthSuperBoot extends SuperBoot {
    public StrengthSuperBoot() {
        super("§aStrength", "strength", -1, Color.PURPLE);
    }

    @Override
    public void onTick(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 4, 0, true, true));
    }
}
