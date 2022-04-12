package be.frostyfeet.minecraft.dynmap;

import org.bukkit.Location;
import org.dynmap.DynmapAPI;
import org.dynmap.markers.MarkerAPI;
import org.dynmap.markers.MarkerIcon;
import org.dynmap.markers.MarkerSet;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

public class DynmapHelper {
    private final Logger logger;
    private final MarkerAPI markerAPI;

    private Map<String, MarkerSet> markerSets;

    public DynmapHelper(DynmapAPI dynmapAPI, Logger logger) {
        this.logger = logger;
        this.markerAPI = dynmapAPI.getMarkerAPI();

        this.initialize();
    }

    public void createMarker(MarkerType type, String label, Location location) {
        String world = location.getWorld().getName();
        double x = location.getX();
        double y = location.getY();
        double z = location.getZ();
        MarkerIcon icon = markerAPI.getMarkerIcon(type.getIcon());

        this.markerSets.get(type.getId()).createMarker(randomId(), label, world, x, y, z, icon, true);
        logger.info(String.format("Added marker to %s with label %s in location %s", type.getLabel(), label, location));
    }

    private void initialize() {
        this.markerSets = new HashMap<>();
        Arrays.stream(MarkerType.values()).forEach(m -> {
            MarkerSet set = createOrGetMarkerSet("frostyfeet." + m.getId(), m.getLabel());
            this.markerSets.put(m.getId(), set);
        });
    }

    private MarkerSet createOrGetMarkerSet(String name, String label) {
        MarkerSet foundSet = markerAPI.getMarkerSet(name);
        if (foundSet != null) {
            return foundSet;
        }
        return markerAPI.createMarkerSet(name, label, null, true);
    }

    private static String randomId() {
        return UUID.randomUUID().toString();
    }
}
