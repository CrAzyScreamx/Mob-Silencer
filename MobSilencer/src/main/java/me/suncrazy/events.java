package me.suncrazy;

import me.suncrazy.configurations.ConfigurationEvents;
import me.suncrazy.configurations.ConfiguratorFile;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.serializer.plain.PlainComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Locale;


public class events implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEntityEvent event) {
        if (ConfigurationEvents.blacklisted_entities.contains(event.getRightClicked().getType())) return;
        if (ConfigurationEvents.blacklisted_players.contains(event.getPlayer())) return;
        ItemStack itemFromHand = null;
        Player p = event.getPlayer();
        Entity e = event.getRightClicked();
        if (event.getHand().equals(EquipmentSlot.OFF_HAND)) {
            itemFromHand = p.getInventory().getItemInOffHand();
        } else if (event.getHand().equals(EquipmentSlot.HAND)) {
            itemFromHand = p.getInventory().getItemInMainHand();
        }
        if (itemFromHand != null) {
            if (itemFromHand.getType().equals(Material.NAME_TAG)) {
                ItemMeta itemMeta = itemFromHand.getItemMeta();
                if (itemMeta != null) {
                    if (itemMeta.hasDisplayName()) {
                        Component displayNameComponent = itemMeta.displayName();
                        String displayName = PlainComponentSerializer.plain().serialize(displayNameComponent);
                        if (displayName.toLowerCase(Locale.ROOT).equals(ConfiguratorFile.silenced_name)) {
                            e.setSilent(true);
                            Bukkit.getScheduler().runTaskLater(MobSilencer.plugin, () -> e.customName(Component.text("**"+ConfiguratorFile.silenced_name.toUpperCase(Locale.ROOT)+"**").color(NamedTextColor.YELLOW)), 1);
                        } else if (displayName.toLowerCase(Locale.ROOT).equals(ConfiguratorFile.unsilenced_name)) {
                            Bukkit.getScheduler().runTaskLater(MobSilencer.plugin, () -> e.customName(Component.text("**"+ConfiguratorFile.unsilenced_name.toUpperCase(Locale.ROOT)+"**").color(NamedTextColor.YELLOW)), 1);
                            e.setSilent(false);
                        } else {
                            if (!ConfiguratorFile.keep_silent_on_change) {
                                e.setSilent(false);
                            }
                        }
                    }
                }
            }
        }
    }
}
