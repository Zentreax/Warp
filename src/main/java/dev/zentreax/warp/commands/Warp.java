package dev.zentreax.warp.commands;

import dev.zentreax.warp.Main;
import dev.zentreax.warp.util.Config;
import dev.zentreax.warp.util.WarpManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Warp implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 1) {
                if (WarpManager.getWarp(args[0]) != null) {
                    player.teleport(WarpManager.getWarp(args[0]));
                    player.sendMessage(Main.PREFIX + "You've been teleported to §b" + args[0]);
                    return true;
                } else {
                    player.sendMessage(Main.PREFIX + "§cWarp not found.");
                    return false;
                }
            } else if (args.length == 2) {
                if (!player.hasPermission("warp.operate")) {
                    return false;
                }
                if (args[0].equalsIgnoreCase("set") || args[0].equalsIgnoreCase("create")) {
                    if (WarpManager.getWarp(args[1]) == null) {
                        WarpManager.createWarp(args[1], player.getLocation());
                        player.sendMessage(Main.PREFIX + "Warp §b" + args[1] + "§a successfully set!");
                        return true;
                    } else {
                        player.sendMessage(Main.PREFIX + "§cWarp §b" + args[1] +  "§c already exists.");
                        return false;
                    }
                } else if (args[0].equalsIgnoreCase("delete") || args[0].equalsIgnoreCase("del")) {
                    if (WarpManager.getWarp(args[1]) != null) {
                        WarpManager.deleteWarp(args[1]);
                        player.sendMessage(Main.PREFIX + "Warp §b" + args[1] + "§a successfully §cdeleted§a!");
                        return true;
                    } else {
                        player.sendMessage(Main.PREFIX + "§cWarp §b" + args[1] +  "§c not found.");
                        return false;
                    }
                }
            } else {
                player.sendMessage(Main.PREFIX + "List of available Warps: (" + Main.getCfg().getConfiguration().getKeys(false).size() + ")");
                List<String> list = new ArrayList<String>();
                for(String warps : Main.getCfg().getConfiguration().getKeys(false)) {
                    Main.getCfg().getConfiguration().get(warps);
                    list.add(warps);
                }
                String warps = String.valueOf(list).toString();
                player.sendMessage(Main.PREFIX + "§b" + warps.substring(1, warps.length() - 1));
                return false;
            }
        } else {
            System.out.println(Main.PREFIX + "You need to be a Player.");
            return false;
        }
        return false;
    }
}
