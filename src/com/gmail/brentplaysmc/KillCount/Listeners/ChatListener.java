package com.gmail.brentplaysmc.KillCount.Listeners;

import com.gmail.brentplaysmc.KillCount.KillCount;
import com.gmail.brentplaysmc.KillCount.Utils.PointsHandler;
import com.gmail.brentplaysmc.KillCount.Utils.GeneralMethods;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    @EventHandler
    public void chatEvent(AsyncPlayerChatEvent event){
        String integer = String.valueOf(PointsHandler.getPoints(event.getPlayer()));
        String prefix = GeneralMethods.colour(KillCount.plugin.getConfig().get("Prefix").toString().replace("POINTS", integer));
        event.setFormat(prefix + " " + "%s : %s");
    }

}
