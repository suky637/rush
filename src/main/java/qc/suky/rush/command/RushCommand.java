package qc.suky.rush.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Subcommand;
import lombok.AllArgsConstructor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import qc.suky.rush.Format;
import qc.suky.rush.Rush;
import qc.suky.rush.RushArena;
import qc.suky.rush.RushArenaState;

import java.io.File;
import java.util.List;

@CommandAlias("rush")
@AllArgsConstructor
public class RushCommand extends BaseCommand {
	private final Rush plugin;

	@Default
	@CommandPermission("rush.use")
	public void onDefault(CommandSender player) {
		player.sendMessage(Format.format("<white>Not implemented yet!"));
	}

	@Subcommand("teleport")
	@CommandPermission("rush.use")
	public void onTeleport(Player player, String name) {
		List<RushArena> arenas = plugin.getArenas();
		for (RushArena arena : arenas) {
			if (arena.status == RushArenaState.RUNNING || arena.status == RushArenaState.FINISHED)
				continue;
			if (arena.getBukkitWorld().getName().equalsIgnoreCase(name)) {
				World world = arena.getBukkitWorld();
				Location location = new Location(world, 0, 65, 0);
				player.teleport(location);
				player.sendMessage(Format.format("<green>Teleporting to the arena...</green>"));
				return;
			}
		}

		RushArena ra = new RushArena(new File(plugin.getDataFolder(), "gameMaps"), "rush", true, plugin);
		World world = ra.getBukkitWorld();
		Location location = new Location(world, 0, 65, 0);
		player.teleport(location);
		player.sendMessage(Format.format("<green>Teleporting to the arena...</green>"));
		plugin.getArenas().add(ra);
	}

	@Subcommand("join")
	@CommandPermission("rush.use")
	public void onJoin(Player player)
	{
		List<RushArena> arenas = plugin.getArenas();
		for (RushArena arena : arenas) {
			if (arena.status == RushArenaState.RUNNING || arena.status == RushArenaState.FINISHED)
				continue;
			if (arena.getBukkitWorld().getName().equalsIgnoreCase(name)) {
				World world = arena.getBukkitWorld();
				Location location = new Location(world, 0, 65, 0);
				player.teleport(location);
				player.sendMessage(Format.format("<green>Teleporting to the arena...</green>"));
				return;
			}
		}

		RushArena ra = new RushArena(new File(plugin.getDataFolder(), "gameMaps"), "rush", true, plugin);
		World world = ra.getBukkitWorld();
		Location location = new Location(world, 0, 65, 0);
		player.teleport(location);
		player.sendMessage(Format.format("<green>Teleporting to the arena...</green>"));
		plugin.getArenas().add(ra);
	}
}
