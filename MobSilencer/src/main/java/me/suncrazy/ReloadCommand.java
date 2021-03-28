package me.suncrazy;

import me.suncrazy.configurations.ConfigurationEvents;
import me.suncrazy.configurations.ConfiguratorFile;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class ReloadCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            JavaPlugin.getPlugin(MobSilencer.plugin.getClass()).reloadConfig();
            ConfiguratorFile.init();
            ConfigurationEvents.init();
            player.sendMessage(Component.text("[Mob Silencer]").color(NamedTextColor.AQUA).append(Component.text(" || ").color(NamedTextColor.WHITE).append(Component.text("Config.yml has been reloaded successfully").color(NamedTextColor.YELLOW))));
            return true;
        }
        return false;
    }
}
