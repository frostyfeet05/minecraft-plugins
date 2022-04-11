package be.frostyfeet.minecraft.dynmap.listeners;

import be.frostyfeet.minecraft.dynmap.DynmapHelper;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;

import java.util.HashMap;
import java.util.Map;

public class AdvancementEventListener implements Listener {
    private final Map<String, String> advancements;

    private final DynmapHelper helper;

    public AdvancementEventListener(FileConfiguration config, DynmapHelper helper) {
        this.helper = helper;
        this.advancements = new HashMap<>();

        config.getConfigurationSection("advancements").getKeys(false).forEach(key -> {
            this.advancements.put(key, config.getString("advancements." + key));
        });
    }

    @EventHandler
    public void onPlayerAdvancementDoneEvent(PlayerAdvancementDoneEvent event) {
        String advancementKey = event.getAdvancement().getKey().getKey();
        if (this.advancements.containsKey(advancementKey)) {
            Player player = event.getPlayer();

            String advancementName = this.advancements.get(advancementKey);
            String message = String.format("%s has made the advancement [%s]", player.getDisplayName(), advancementName);

            this.helper.markAdvancement(message, player.getLocation());
        }
    }
}
