package com.gmail.brentplaysmc.KillCount.Commands;

import com.gmail.brentplaysmc.KillCount.Utils.MinecraftVerifier;
import com.gmail.brentplaysmc.KillCount.Utils.PointsHandler;
import com.gmail.brentplaysmc.KillCount.Utils.GeneralMethods;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

public class PointsCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if (cmd.getName().equalsIgnoreCase("points")) {

            if (!(sender instanceof Player)) {
                return false;
            }

            Player player = (Player) sender;

            if (args.length == 0) {
                player.sendMessage(GeneralMethods.colour("&4[&8KillCount&4]&c Add a statement: Add, Remove or Reset!"));
            }

            if (args.length > 0) {
                if (args[0].equalsIgnoreCase("add") && player.hasPermission("points.use.add")) {
                    if (args.length == 1) {
                        player.sendMessage(GeneralMethods.colour("&4[&8KillCount&4]&c Please add a name!"));
                    }
                    if (args.length == 2) {
                        player.sendMessage(GeneralMethods.colour("&4[&8KillCount&4]&c Please add the amount of points, remember it needs to be a number!"));
                    }
                    if (args.length == 3) {
                        OfflinePlayer player1 = Bukkit.getOfflinePlayer(args[1]);
                        boolean exists = false;
                        try {
                            exists = MinecraftVerifier.checkAccount(args[1]);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Boolean number;
                        int foo = 0;
                        try {
                            foo = Integer.parseInt(args[2]);
                            number = true;
                        } catch (NumberFormatException nfe) {
                            player.sendMessage(GeneralMethods.colour("&4[&8KillCount&4]&c The amount of points needs to be a number!"));
                            number = false;
                        }
                        if (number && exists) {
                            PointsHandler.addPoints(player1, player, foo);
                        }
                        if(number && !exists){
                            player.sendMessage(GeneralMethods.colour("&4[&8KillCount&4]&c The player could not be found!"));
                        }
                    }
                }

                if (args[0].equalsIgnoreCase("add") && !player.hasPermission("points.use.add")) {
                    player.sendMessage(GeneralMethods.colour("&4[&8KillCount&4]&c You need the permission 'points.use.add' to use this command!"));
                }

                if (args[0].equalsIgnoreCase("remove") && player.hasPermission("points.use.remove")) {
                    if (args.length == 1) {
                        player.sendMessage(GeneralMethods.colour("&4[&8KillCount&4]&c Please add a name!"));
                    }
                    if (args.length == 2) {
                        player.sendMessage(GeneralMethods.colour("&4[&8KillCount&4]&c Please add the amount of points, remember it needs to be a number!"));
                    }
                    if (args.length == 3) {
                        OfflinePlayer player1 = Bukkit.getOfflinePlayer(args[1]);
                        Boolean number;
                        boolean exists = false;
                        try {
                            exists = MinecraftVerifier.checkAccount(args[1]);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        int foo = 0;
                        try {
                            foo = Integer.parseInt(args[2]);
                            number = true;
                        } catch (NumberFormatException nfe) {
                            player.sendMessage(GeneralMethods.colour("&4[&8KillCount&4]&c The amount of points needs to be a number!"));
                            number = false;
                        }
                        if(number && exists) {
                            PointsHandler.removePoints(player1, player, foo);
                        }
                        if(number && !exists){
                            player.sendMessage(GeneralMethods.colour("&4[&8KillCount&4]&c The player could not be found!"));
                        }
                    }
                }

                if(args[0].equalsIgnoreCase("remove") && !player.hasPermission("points.use.remove")) {
                    player.sendMessage(GeneralMethods.colour("&4[&8KillCount&4]&c You need the permission 'points.use.remove' to use this command!"));
                }

                if (args[0].equalsIgnoreCase("reset") && player.hasPermission("points.use.reset")) {
                    if (args.length == 1) {
                        player.sendMessage(GeneralMethods.colour("&4[&8KillCount&4]&c Please add a name!"));
                    }
                    if (args.length == 2) {
                        OfflinePlayer player1 = Bukkit.getOfflinePlayer(args[1]);
                        boolean exists = false;
                        try {
                            exists = MinecraftVerifier.checkAccount(args[1]);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if(exists) {
                            PointsHandler.resetPoints(player1, player);
                        }
                        if(!exists){
                            player.sendMessage(GeneralMethods.colour("&4[&8KillCount&4]&c The player could not be found!"));
                        }
                    }
                }

                if(args[0].equalsIgnoreCase("reset") && !player.hasPermission("points.use.reset")){
                    player.sendMessage(GeneralMethods.colour("&4[&8KillCount&4]&c You need the permission 'points.use.reset' to use this command!"));
                }
            }

            if(args.length > 0 && !args[0].equalsIgnoreCase("add") && !args[0].equalsIgnoreCase("remove") && !args[0].equalsIgnoreCase("reset")){
                player.sendMessage(GeneralMethods.colour("&4[&8KillCount&4]&c Add a statement: Add, Remove or Reset!"));
            }
        }
        return false;
    }
}