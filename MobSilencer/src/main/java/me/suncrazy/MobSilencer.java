package me.suncrazy;

import me.suncrazy.configurations.ConfigurationEvents;
import me.suncrazy.configurations.ConfiguratorFile;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class MobSilencer extends JavaPlugin {

    public static MobSilencer plugin;

    @Override
    public void onEnable() {
        plugin = this;
        this.saveDefaultConfig();
        ConfiguratorFile.init();
        ConfigurationEvents.init();
        Bukkit.getServer().getPluginManager().registerEvents(new events(), this);
        Objects.requireNonNull(this.getCommand("reload")).setExecutor(new ReloadCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
