package com.minecraftdimensions.bungeesuitehomes.tasks;

import com.minecraftdimensions.bungeesuitehomes.BungeeSuiteHomes;
import com.minecraftdimensions.bungeesuitehomes.utils.OptionalUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class PluginMessageTask extends BukkitRunnable {

    private final ByteArrayOutputStream bytes;

    public PluginMessageTask(ByteArrayOutputStream bytes) {
        this.bytes = bytes;
    }

    public static List<Player> getOnlinePlayers() {
        return new OptionalUtils<>(() -> {
            List<Player> onlinePlayers = new ArrayList<>();
            Bukkit.getWorlds().forEach(world -> onlinePlayers.addAll(world.getPlayers()));
            return onlinePlayers;
        }).getOptional().orElseGet(ArrayList::new);
    }

    public void run() {
        getOnlinePlayers().get(0).sendPluginMessage(
                BungeeSuiteHomes.instance,
                BungeeSuiteHomes.OUTGOING_PLUGIN_CHANNEL,
                bytes.toByteArray());

    }
}