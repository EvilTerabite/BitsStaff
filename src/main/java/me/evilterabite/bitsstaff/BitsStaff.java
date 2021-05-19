package me.evilterabite.bitsstaff;

import me.evilterabite.bitsstaff.commands.FreezeCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.logging.Level;

public final class BitsStaff extends JavaPlugin {

    public static ArrayList<Player> frozenPlayers = new ArrayList<>();

    @Override
    public void onEnable() {
        getCommand("freeze").setExecutor(new FreezeCommand());
        getServer().getPluginManager().registerEvents(new FreezeCommand(), this);
        getLogger().log(Level.INFO, "BitsStaff Started!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
