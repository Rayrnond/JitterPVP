package com.reflexian.jitterpvp.ClearItems;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import com.gmail.filoghost.holographicdisplays.api.line.TextLine;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.HashMap;
import java.util.List;

public class ClearItems implements Listener {

    public HashMap<Integer, Integer> taskMap = new HashMap<Integer, Integer>();

    public com.reflexian.jitterpvp.JitterPVP main;

    Integer task = 0;

    public ClearItems(com.reflexian.jitterpvp.JitterPVP main) {
        this.main = main;
    }
    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Location EntityArea = event.getEntity().getPlayer().getLocation();

        Hologram hologram = HologramsAPI.createHologram(main, event.getEntity().getLocation().add(0, 1.2, 0));
        hologram.appendTextLine(main.getConfig().getString("Clear-Items.First-Line"));
        final TextLine[] textLine1 = {hologram.appendTextLine(main.getConfig().getString("Clear-Items.Second-Line") + "15")};

        task = task + 1;
        Integer loc = task;
        taskMap.put(task, Bukkit.getScheduler().scheduleSyncRepeatingTask(main, new Runnable() {

            int remainingTimeInSeconds = 15;
            @Override
            public void run() {
                remainingTimeInSeconds--;
                if (remainingTimeInSeconds > 0) {
                    hologram.removeLine(1);
                    textLine1[0] = hologram.appendTextLine(main.getConfig().getString("Clear-Items.Second-Line") + remainingTimeInSeconds);
                } else {
                    hologram.delete();
                    Integer range = main.getConfig().getInt("Clear-Items.Range");
                    List<Entity> nearby = (List<Entity>) EntityArea.getWorld().getNearbyEntities(EntityArea, range, range, range);
                    for (Entity entity : nearby) {
                        if(entity instanceof Item) {
                            entity.remove();
                        }
                    }
                    Bukkit.getScheduler().cancelTask(taskMap.get(loc));
                    return;
                }
            }
        }, 20L, 20L));


    }

}
