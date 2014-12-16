package com.gmail.brentplaysmc.KillCount.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

public class RegisterEvents {

    public static void registerEvents(org.bukkit.plugin.Plugin plugin, Listener... listeners) {
        for (Listener listener : listeners) {
            Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
        }
    }

}
