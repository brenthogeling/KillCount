package com.gmail.brentplaysmc.KillCount;

import com.gmail.brentplaysmc.KillCount.Commands.PointsCommand;
import com.gmail.brentplaysmc.KillCount.Listeners.ChatListener;
import com.gmail.brentplaysmc.KillCount.Listeners.DeathListener;
import com.gmail.brentplaysmc.KillCount.Listeners.JoinEvent;
import com.gmail.brentplaysmc.KillCount.Listeners.RegisterEvents;
import com.gmail.brentplaysmc.KillCount.Utils.PointsHandler;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class KillCount extends JavaPlugin {

    public static Plugin plugin;

    public void onEnable(){
        plugin = this;
        PointsHandler.folderGeneration();
        getCommand("points").setExecutor(new PointsCommand());
        RegisterEvents.registerEvents(plugin, new DeathListener(), new JoinEvent(), new ChatListener());
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
    }

    public void onDisable(){
        plugin = null;
    }

}
