package be.frostyfeet.minecraft.dynmap.listeners;

import be.frostyfeet.minecraft.dynmap.DynmapHelper;
import be.frostyfeet.minecraft.dynmap.MarkerType;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class DeathEventListener implements Listener {

    private final DynmapHelper helper;

    @EventHandler
    public void onEntityDeathEvent(PlayerDeathEvent event) {
        this.helper.createMarker(MarkerType.DEATH, event.getDeathMessage(), event.getEntity().getLocation());
    }
}
