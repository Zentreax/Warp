package dev.zentreax.warp;

import dev.zentreax.warp.commands.Warp;
import dev.zentreax.warp.util.Config;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Config cfg;
    public static Main plugin;

    public static final String PREFIX = "§7[§eWarp§7]§a ";

    public void onEnable() {
        System.out.println("[WARP] Started! (Coded by https://zentreax.dev/)");
        cfg = new Config("warps.yml", getDataFolder());
        getCommand("warp").setExecutor(new Warp());
    }

    @Override
    public void onDisable() { }

    public static Config getCfg() {
        return cfg;
    }
}
