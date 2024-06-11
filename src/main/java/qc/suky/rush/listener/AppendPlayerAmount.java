package qc.suky.rush.listener;

import lombok.AllArgsConstructor;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import qc.suky.rush.Rush;

@AllArgsConstructor
public class AppendPlayerAmount implements Listener {
	private final Rush plugin;

	@EventHandler
	public void onWorldChange(PlayerChangedWorldEvent event) {
		World before = event.getFrom();
		World after = event.getPlayer().getWorld();

		if (plugin.getArena().getBukkitWorld() == after) {
			plugin.getArena().getPlayers().add(event.getPlayer());
		} else if (plugin.getArena().getBukkitWorld() == before) {
			plugin.getArena().getPlayers().remove(event.getPlayer());
		}

	}

}
