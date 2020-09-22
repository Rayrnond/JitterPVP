package com.reflexian.jitterpvp;

import com.reflexian.jitterpvp.ClearItems.ClearItems;
import com.reflexian.jitterpvp.GoldenApple.Consume;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class JitterPVP extends JavaPlugin {


    @Override
    public void onEnable() {
        FileConfiguration config = getConfig();
        Bukkit.getPluginManager().registerEvents(new ClearItems(this), this);
        Bukkit.getPluginManager().registerEvents(new Consume(this), this);
        File file = new File(getDataFolder()+File.separator+"joins.yml");
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        saveDefaultConfig();

        getLogger().info(" ");
        getLogger().info(" ");
        getLogger().info("░░░░░██╗██╗████████╗████████╗███████╗██████╗░██████╗░██╗░░░██╗██████╗░");
        getLogger().info("░░░░░██║██║╚══██╔══╝╚══██╔══╝██╔════╝██╔══██╗██╔══██╗██║░░░██║██╔══██╗");
        getLogger().info("░░░░░██║██║░░░██║░░░░░░██║░░░█████╗░░██████╔╝██████╔╝╚██╗░██╔╝██████╔╝");
        getLogger().info("██╗░░██║██║░░░██║░░░░░░██║░░░██╔══╝░░██╔══██╗██╔═══╝░░╚████╔╝░██╔═══╝░");
        getLogger().info("╚█████╔╝██║░░░██║░░░░░░██║░░░███████╗██║░░██║██║░░░░░░░╚██╔╝░░██║░░░░░");
        getLogger().info("░╚════╝░╚═╝░░░╚═╝░░░░░░╚═╝░░░╚══════╝╚═╝░░╚═╝╚═╝░░░░░░░░╚═╝░░░╚═╝░░░░░");
        getLogger().info(" ");
        getLogger().info(" ");
        getLogger().info(" Made by Raymond#0001 from Discord. https://www.reflexian.com  ");
        getLogger().info(" ");
        getLogger().info(" ");
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage("" + config.getString("Message-Announcer.message"));
            }
        }, 60L, config.getLong("Message-Announcer.interval")*20);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
