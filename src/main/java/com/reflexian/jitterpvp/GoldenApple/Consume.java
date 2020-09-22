package com.reflexian.jitterpvp.GoldenApple;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Consume implements Listener {


    public com.reflexian.jitterpvp.JitterPVP main;

    public Consume(com.reflexian.jitterpvp.JitterPVP main) {
        this.main = main;
    }

    @EventHandler
    public void onEat(PlayerItemConsumeEvent event) {
        String Effect = main.getConfig().getString("Golden-Apple.effect");
        Integer duration = main.getConfig().getInt("Golden-Apple.length");
        Integer amplifier = main.getConfig().getInt("Golden-Apple.amplifier");
        String Effect2 = main.getConfig().getString("Golden-Apple.effect2");
        Integer duration2 = main.getConfig().getInt("Golden-Apple.length2");
        Integer amplifier2 = main.getConfig().getInt("Golden-Apple.amplifier2");
        if (event.getItem().getType() == Material.GOLDEN_APPLE && event.getItem().getDurability() == 1) {
            event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.getByName(Effect), duration*20, amplifier));
            event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.getByName(Effect2), duration2*20, amplifier2));
            event.getPlayer().sendMessage(main.getConfig().getString("Golden-Apple.message"));
        }
    }

}
