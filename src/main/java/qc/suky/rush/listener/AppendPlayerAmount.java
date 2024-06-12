package qc.suky.rush.listener;

import lombok.AllArgsConstructor;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import qc.suky.rush.Rush;
import qc.suky.rush.RushArena;
import qc.suky.rush.RushArenaState;

@AllArgsConstructor
public class AppendPlayerAmount implements Listener {
	private final Rush plugin;

	@EventHandler
	public void onWorldChange(PlayerChangedWorldEvent event) {
		World before = event.getFrom();
		World after = event.getPlayer().getWorld();

		for (RushArena arena : plugin.getArenas()) {
			if (arena.getBukkitWorld() == after) {
				arena.getPlayers().add(event.getPlayer());
			} else if (arena.getBukkitWorld() == before) {
				arena.getPlayers().remove(event.getPlayer());
				if (arena.getPlayers().isEmpty() || arena.status == RushArenaState.FINISHED)
				{
					arena.unload();
					plugin.getArenas().remove(arena);
				}
			}
		}

	}

}
