package me.suncrazy.configurations;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.*;

public class ConfigurationEvents {

    public static ArrayList<EntityType> blacklisted_entities;
    public static ArrayList<Player> blacklisted_players;

    public static void init() {
        blacklisted_entities = getBlackListedEntities();
        blacklisted_players = getBlackListedPlayers();
    }

    private static ArrayList<EntityType> getBlackListedEntities() {
        if (!ConfiguratorFile.entity_blacklist) return new ArrayList<>();
        ArrayList<EntityType> entities = new ArrayList<>();
        for (int i = 0; i < ConfiguratorFile.entity_blacklist_list.size(); i++) {
            String name = ConfiguratorFile.entity_blacklist_list.get(i).toUpperCase(Locale.ROOT);
            EntityType entityType = EntityType.valueOf(name);
            if (!entityType.equals(EntityType.UNKNOWN)) {
                entities.add(entityType);
            }
        }
        return entities;
    }

    private static ArrayList<Player> getBlackListedPlayers() {
        if (!ConfiguratorFile.player_blacklist) return new ArrayList<>();
        ArrayList<Player> players = new ArrayList<>();
        for (int i = 0; i < ConfiguratorFile.player_blacklist_list.size(); i++) {
            String name = ConfiguratorFile.player_blacklist_list.get(0);
            Player player = Bukkit.getPlayer(name);
            if (player != null) {
                players.add(player);
            }
        }
        return players;
    }

}
