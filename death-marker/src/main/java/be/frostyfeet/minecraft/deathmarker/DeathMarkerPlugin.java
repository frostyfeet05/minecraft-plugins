package be.frostyfeet.minecraft.deathmarker;

import org.bukkit.plugin.java.JavaPlugin;

public class DeathMarkerPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("DeathMarker is enabled");
        getServer().getPluginManager().registerEvents(new DeathListener(getServer()), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("DeathMarker is disabled");
    }

}
