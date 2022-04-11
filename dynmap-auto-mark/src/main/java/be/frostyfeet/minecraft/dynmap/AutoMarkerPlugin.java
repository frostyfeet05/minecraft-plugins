package be.frostyfeet.minecraft.dynmap;

import be.frostyfeet.minecraft.dynmap.commands.AddMarkerCommand;
import be.frostyfeet.minecraft.dynmap.listeners.AdvancementEventListener;
import be.frostyfeet.minecraft.dynmap.listeners.DeathEventListener;
import org.bukkit.plugin.java.JavaPlugin;
import org.dynmap.DynmapAPI;

public class AutoMarkerPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();

        DynmapHelper helper = this.instantiateDynmapHelper();

        getServer().getPluginManager().registerEvents(new DeathEventListener(helper), this);
        getServer().getPluginManager().registerEvents(new AdvancementEventListener(getConfig(), helper), this);
        getCommand("ffmap").setExecutor(new AddMarkerCommand(helper));
    }

    private DynmapHelper instantiateDynmapHelper() {
        DynmapAPI plugin = (DynmapAPI) getServer().getPluginManager().getPlugin("dynmap");
        return new DynmapHelper(plugin, getLogger());
    }

}
