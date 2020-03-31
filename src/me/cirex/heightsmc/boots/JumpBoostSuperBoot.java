package me.cirex.heightsmc.boots;

import org.bukkit.Color;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class JumpBoostSuperBoot extends SuperBoot {
    public JumpBoostSuperBoot() {
        super("§6Jump Boost", "jumpboost", -1, Color.LIME);
    }

    @Override
    public void onTick(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 4, 0, true, true));
    }
}
