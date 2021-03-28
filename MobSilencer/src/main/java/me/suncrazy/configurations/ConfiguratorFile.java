package me.suncrazy.configurations;

import me.suncrazy.MobSilencer;

import java.util.List;
import java.util.Objects;

public class ConfiguratorFile {

    public static List<String> player_blacklist_list;
    public static List<String> entity_blacklist_list;
    public static String silenced_name;
    public static String unsilenced_name;
    public static boolean entity_blacklist;
    public static boolean player_blacklist;
    public static boolean keep_silent_on_change;


    public static void init() {
        MobSilencer plugin = MobSilencer.plugin;

        player_blacklist = plugin.getConfig().getBoolean("blacklist-enabled-players");
        player_blacklist_list = plugin.getConfig().getStringList("blacklisted-players");

        if (Objects.equals(plugin.getConfig().getString("Name Tag Custom Name Silenced"), "")) silenced_name = "silenced";
        else silenced_name = plugin.getConfig().getString("Name Tag Custom Name Silenced");
        if (Objects.equals(plugin.getConfig().getString("Name Tag Custom Name Un-Silenced"), "")) unsilenced_name = "unsilenced";
        else unsilenced_name = plugin.getConfig().getString("Name Tag Custom Name Un-Silenced");

        entity_blacklist = plugin.getConfig().getBoolean("blacklisted-entities-enabled");
        entity_blacklist_list = plugin.getConfig().getStringList("blacklisted-entities");

        keep_silent_on_change = plugin.getConfig().getBoolean("Keep sound on name change");
    }
}
