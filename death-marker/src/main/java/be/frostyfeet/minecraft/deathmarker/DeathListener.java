package be.frostyfeet.minecraft.deathmarker;

import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.UUID;

public class DeathListener implements Listener {

    private final Server server;

    public DeathListener(final Server server) {
        this.server = server;
    }

    @EventHandler
    public void onEntityDeathEvent(PlayerDeathEvent event) {
        Location placeOfDeath = event.getEntity().getLocation();
        int x = placeOfDeath.getBlockX();
        int y = placeOfDeath.getBlockY();
        int z = placeOfDeath.getBlockZ();
        String worldName = placeOfDeath.getWorld().getName();
        String uuid = UUID.randomUUID().toString();

        String command = String.format("dmarker add id:%s \"%s\" icon:skull set:deaths x:%d y:%d z:%d world:%s", uuid, event.getDeathMessage(), x, y, z, worldName);
        server.dispatchCommand(server.getConsoleSender(), command);
    }

}
