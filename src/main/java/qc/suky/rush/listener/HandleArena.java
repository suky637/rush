package qc.suky.rush.listener;

import lombok.AllArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import qc.suky.rush.Rush;
import qc.suky.rush.event.ArenaUnloadEvent;

import java.util.Objects;

@AllArgsConstructor
public class HandleArena implements Listener {
	private final Rush plugin;

	@EventHandler
	public void disconnectPlayers(ArenaUnloadEvent event) {
		World to = Bukkit.getWorld(Objects.requireNonNull(plugin.getConfig().getString("spawn_world")));
		event.getArena().getPlayers().forEach(player -> player.teleport(to.getSpawnLocation()));
	}

}
