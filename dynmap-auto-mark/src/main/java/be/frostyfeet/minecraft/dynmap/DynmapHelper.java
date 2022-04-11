package be.frostyfeet.minecraft.dynmap;

import org.bukkit.Location;
import org.dynmap.DynmapAPI;
import org.dynmap.markers.MarkerAPI;
import org.dynmap.markers.MarkerSet;

import java.util.UUID;
import java.util.logging.Logger;

public class DynmapHelper {
    private final Logger logger;
    private final DynmapAPI dynmapAPI;
    private final MarkerAPI markerAPI;

    private MarkerSet deathSet;
    private MarkerSet advancementSet;
    private MarkerSet townSet;
    private MarkerSet portalSet;
    private MarkerSet shipSet;
    private MarkerSet templeSet;
    private MarkerSet towerSet;
    private MarkerSet mineshaftSet;

    public DynmapHelper(DynmapAPI dynmapAPI, Logger logger) {
        this.logger = logger;
        this.dynmapAPI = dynmapAPI;
        this.markerAPI = dynmapAPI.getMarkerAPI();

        this.initialize();
    }

    public void markDeath(String message, Location location) {
        this.createMarker(deathSet, location, message, "skull");
    }

    public void markAdvancement(String message, Location location) {
        this.createMarker(advancementSet, location, message, "goldstar");
    }

    public void markTown(String name, Location location) {
        this.createMarker(townSet, location, name, "church");
    }

    public void markPortal(String name, Location location) {
        this.createMarker(portalSet, location, name, "portal");
    }

    public void markShip(String name, Location location) {
        this.createMarker(shipSet, location, name, "pirateflag");
    }

    public void markTemple(String name, Location location) {
        this.createMarker(templeSet, location, name, "temple");
    }

    public void markTower(String name, Location location) {
        this.createMarker(towerSet, location, name, "tower");
    }

    public void markMineshaft(String name, Location location) {
        this.createMarker(mineshaftSet, location, name, "minecart");
    }

    private void createMarker(final MarkerSet set, final Location location, String message, String icon) {
        String world = location.getWorld().getName();
        double x = location.getX();
        double y = location.getY();
        double z = location.getZ();

        set.createMarker(randomId(), message, world, x, y, z, markerAPI.getMarkerIcon(icon), true);
    }

    private void initialize() {
        this.deathSet = createOrGetMarkerSet("frostyfeet.deaths", "Deaths");
        this.advancementSet = createOrGetMarkerSet("frostyfeet.advancements", "Advancements");
        this.townSet = createOrGetMarkerSet("frostyfeet.towns", "Towns");
        this.portalSet = createOrGetMarkerSet("frostyfeet.portals", "Portals");
        this.shipSet = createOrGetMarkerSet("frostyfeet.ships", "Shipwrecks");
        this.templeSet = createOrGetMarkerSet("frostyfeet.temples", "Temples");
        this.towerSet = createOrGetMarkerSet("frostyfeet.towers", "Towers");
        this.mineshaftSet = createOrGetMarkerSet("frostyfeet.mineshafts", "Mineshafts");
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
