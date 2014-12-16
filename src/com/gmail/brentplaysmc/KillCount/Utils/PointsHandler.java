package com.gmail.brentplaysmc.KillCount.Utils;

import com.gmail.brentplaysmc.KillCount.KillCount;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class PointsHandler {

    public static void folderGeneration() {
        File userfolder = new File(KillCount.plugin.getDataFolder() + File.separator + "UserData");
        if (!userfolder.exists()) {
            userfolder.mkdirs();
        }
    }

    public static void userGeneration(Player player) {
        folderGeneration();
        YamlConfiguration file = null;
        File user = new File(KillCount.plugin.getDataFolder() + File.separator + "UserData", player.getUniqueId() + ".yml");
        if (!user.exists()) {
            try {
                user.createNewFile();
                file = YamlConfiguration.loadConfiguration(user);
                file.set("Kills", 0);
                file.save(user);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void killPoints(Player player){
        File user = new File(KillCount.plugin.getDataFolder() + File.separator + "UserData", player.getUniqueId() + ".yml");
        YamlConfiguration file = YamlConfiguration.loadConfiguration(user);
        file.set("Kills", file.getInt("Kills") + KillCount.plugin.getConfig().getInt("KillPoints"));
        try {
            file.save(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deathPoints(Player player){
        File user = new File(KillCount.plugin.getDataFolder() + File.separator + "UserData", player.getUniqueId() + ".yml");
        YamlConfiguration file = YamlConfiguration.loadConfiguration(user);
        int points = file.getInt("Kills") - KillCount.plugin.getConfig().getInt("DeathPoints");
        if(points <= 0){
            file.set("Kills", 0);
        }
        if(points > 0){
            file.set("Kills", points);
        }
        try {
            file.save(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Integer getPoints(Player player){
        File user = new File(KillCount.plugin.getDataFolder() + File.separator + "UserData", player.getUniqueId() + ".yml");
        YamlConfiguration file = YamlConfiguration.loadConfiguration(user);
        return file.getInt("Kills");
    }

    public static void addPoints(OfflinePlayer player, Player sender, Integer points){
        File user = new File(KillCount.plugin.getDataFolder() + File.separator + "UserData", player.getUniqueId() + ".yml");
        YamlConfiguration file = YamlConfiguration.loadConfiguration(user);
        if(user.exists()) {
            file.set("Kills", file.getInt("Kills") + points);
            sender.sendMessage(GeneralMethods.colour("&4[&8KillCount&4] &c" + player.getName() + " kill count is now " + file.getInt("Kills") + "!"));
            try {
                file.save(user);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(!user.exists()){
            sender.sendMessage(GeneralMethods.colour("&4[&8KillCount&4] &cWe were unable to find the player file, make sure that player has joined before!"));
        }
    }

    public static void removePoints(OfflinePlayer player, Player sender, Integer points){
        File user = new File(KillCount.plugin.getDataFolder() + File.separator + "UserData", player.getUniqueId() + ".yml");
        YamlConfiguration file = YamlConfiguration.loadConfiguration(user);
        if(user.exists()) {
            int finalpoints = file.getInt("Kills") - points;
            if (finalpoints <= 0) {
                file.set("Kills", 0);
                sender.sendMessage(GeneralMethods.colour("&4[&8KillCount&4] &c" + player.getName() + " final kill count is now 0!"));
            }
            if (finalpoints > 0) {
                file.set("Kills", finalpoints);
                sender.sendMessage(GeneralMethods.colour("&4[&8KillCount&4] &c" + player.getName() + " final kill count is now " + finalpoints + "!"));
            }
            try {
                file.save(user);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(!user.exists()){
            sender.sendMessage(GeneralMethods.colour("&4[&8KillCount&4] &cWe were unable to find the player file, make sure that player has joined before!"));
        }
    }

    public static void resetPoints(OfflinePlayer player, Player sender){
        File user = new File(KillCount.plugin.getDataFolder() + File.separator + "UserData", player.getUniqueId() + ".yml");
        YamlConfiguration file = YamlConfiguration.loadConfiguration(user);
        if(user.exists()) {
            file.set("Kills", 0);
            try {
                file.save(user);
            } catch (IOException e) {
                e.printStackTrace();
            }
            sender.sendMessage(GeneralMethods.colour("&4[&8KillCount&4] &c" + player.getName() + " has had his kills reset to 0!"));
        }
        if(!user.exists()){
            sender.sendMessage(GeneralMethods.colour("&4[&8KillCount&4] &cWe were unable to find the player file, make sure that player has joined before!"));
        }
    }
}
