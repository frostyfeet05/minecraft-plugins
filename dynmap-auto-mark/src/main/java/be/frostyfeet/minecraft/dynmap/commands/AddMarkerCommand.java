package be.frostyfeet.minecraft.dynmap.commands;

import be.frostyfeet.minecraft.dynmap.DynmapHelper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;

@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class AddMarkerCommand implements CommandExecutor {

    private final DynmapHelper helper;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length < 2) {
                sender.sendMessage("Invalid arguments, expecting marker type and marker label, instead got args=" + String.join(",", args));
                return false;
            }

            String markerType = args[0].toLowerCase(Locale.ROOT);
            String markerLabel = Arrays.stream(args).skip(1).collect(Collectors.joining(" "));

            switch (markerType) {
                case "town" -> helper.markTown(markerLabel, player.getLocation());
                case "portal" -> helper.markPortal(markerLabel, player.getLocation());
                case "ship" -> helper.markShip(markerLabel, player.getLocation());
                case "temple" -> helper.markTemple(markerLabel, player.getLocation());
                case "tower" -> helper.markTower(markerLabel, player.getLocation());
                case "mineshaft" -> helper.markMineshaft(markerLabel, player.getLocation());
                default -> {
                    sender.sendMessage("Unknown marker type.");
                    return false;
                }
            }

            return true;
        }

        sender.sendMessage("Only players can use this command.");
        return false;
    }

}
