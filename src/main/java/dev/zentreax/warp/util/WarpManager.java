package dev.zentreax.warp.util;

import dev.zentreax.warp.Main;
import org.bukkit.Location;

public class WarpManager {

    public static Location getWarp(String name) {
        return (Location) Main.getCfg().getConfiguration().get(name);
    }

    public static void createWarp(String name, Location location) {
        Main.getCfg().set(name, location);
        Main.getCfg().save();
    }

    public static void deleteWarp(String name) {
        Main.getCfg().set(name, null);
        Main.getCfg().save();
    }
}
