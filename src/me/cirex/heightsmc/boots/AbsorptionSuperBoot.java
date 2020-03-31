package me.cirex.heightsmc.boots;

import org.bukkit.Color;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class AbsorptionSuperBoot extends SuperBoot {
    public AbsorptionSuperBoot() {
        super("§6Absorption", "absorption", 5000, Color.LIME);
    }

    @Override
    public void onTick(Player player) {
    }

    @Override
    public void onPutOn(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, Integer.MAX_VALUE, 4, true, true));
        super.onPutOn(player);
    }
}
