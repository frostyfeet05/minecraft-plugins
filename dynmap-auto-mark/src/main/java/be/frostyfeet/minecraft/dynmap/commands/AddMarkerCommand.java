package be.frostyfeet.minecraft.dynmap.commands;

import be.frostyfeet.minecraft.dynmap.DynmapHelper;
import be.frostyfeet.minecraft.dynmap.MarkerType;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.stream.Collectors;

@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class AddMarkerCommand implements CommandExecutor {

    private final DynmapHelper helper;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player player) {

            if (args.length == 0) {
                sender.sendMessage("Invalid command. Expecting Marker Type and Marker Label.");
                return false;
            } else if (args.length == 1) {
                sender.sendMessage("Invalid command. Expecting Marker Label.");
                return false;
            }

            MarkerType markerType = MarkerType.fromString(args[0]);
            if (markerType == null) {
                sender.sendMessage("Invalid command. Expecting a valid Marker Type.");
                return false;
            }

            String markerLabel = Arrays.stream(args).skip(1).collect(Collectors.joining(" "));
            helper.createMarker(markerType, markerLabel, player.getLocation());
            sender.sendMessage(String.format("Marker added to %s.", markerType.getLabel()));
            return true;
        }

        sender.sendMessage("Only players can use this command.");
        return false;
    }

}
