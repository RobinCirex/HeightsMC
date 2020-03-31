package me.cirex.heightsmc.management;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import me.cirex.heightsmc.boots.*;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class SuperBootManager {

    public ArrayList<SuperBoot> boots = Lists.newArrayList();
    public HashMap<Player, SuperBoot> currentBoot = Maps.newHashMap();

    public SuperBootManager() {
        this.boots.add(new RegenSuperBoot());
        this.boots.add(new StrengthSuperBoot());
        this.boots.add(new GlowSuperBoot());
        this.boots.add(new AntiFallDamageSuperBoot());
        this.boots.add(new JumpBoostSuperBoot());
        this.boots.add(new SpeedSuperBoot());
        this.boots.add(new DoubleJumpSuperBoot());
        this.boots.add(new AbsorptionSuperBoot());
    }

    public SuperBoot getBoot(String boot) {
        for (SuperBoot b : boots) {
            if (b.name.equalsIgnoreCase(boot)) {
                return b;
            }
        }
        return null;
    }

}
