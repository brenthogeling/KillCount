package com.gmail.brentplaysmc.KillCount.Listeners;

import com.gmail.brentplaysmc.KillCount.Utils.PointsHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event){
        Player player = event.getEntity();
        Player killer = event.getEntity().getKiller();
        if(killer != null) {
            PointsHandler.killPoints(event.getEntity().getKiller());
            PointsHandler.deathPoints(event.getEntity());
        }
        if(killer == null){
            PointsHandler.deathPoints(event.getEntity());
        }
    }
}
