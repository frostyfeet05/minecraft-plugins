package be.frostyfeet.minecraft.dynmap;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum MarkerType {
    DEATH("death", "Deaths", "skull", false),
    ADVANCEMENT("advancement", "Advancements", "goldstar", false),
    TOWN("town", "Towns", "church", true),
    PORTAL("portal", "Portals", "portal", true),
    SHIP("ship", "Shipwrecks", "pirateflag", true),
    TEMPLE("temple", "Temples", "temple", true),
    TOWER("tower", "Towers", "tower", true),
    MINESHAFT("mine", "Mineshafts", "minecart", true);

    private final String id;
    private final String label;
    private final String icon;
    private final boolean command;

    public static MarkerType fromString(String value) {
        return Arrays.stream(MarkerType.values())
                .filter(MarkerType::isCommand)
                .filter(x -> x.getId().equalsIgnoreCase(value))
                .findFirst()
                .orElse(null);
    }
}
