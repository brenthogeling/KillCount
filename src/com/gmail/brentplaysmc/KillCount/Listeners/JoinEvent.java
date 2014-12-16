package com.gmail.brentplaysmc.KillCount.Listeners;

import com.gmail.brentplaysmc.KillCount.Utils.PointsHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {

    @EventHandler
    public void onJoinEvent(PlayerJoinEvent event){

        PointsHandler.userGeneration(event.getPlayer());

    }

}
